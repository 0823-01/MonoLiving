<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*, java.net.HttpURLConnection, java.net.URL" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.kh.common.JDBCTemplate" %>
<%@ page import="com.kh.member.model.vo.Member" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.UUID" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>카카오페이 결제 완료</title>
</head>
<body>

<%
    // 세션 가져오기
    HttpSession userSession = request.getSession(); 
    String apiUrl = "https://kapi.kakao.com/v1/payment/approve";
    String adminKey = "3cf90ec67e8e09bae787a5b2e11a21f0"; // 카카오 관리자 키
    String tid = (String) userSession.getAttribute("tid"); // 세션에서 TID 가져오기
    String pg_token = request.getParameter("pg_token"); // 결제 성공 후 받는 pg_token

    // 세션에서 로그인한 사용자 정보 가져오기
    Member loginUser = (Member) userSession.getAttribute("loginUser");
    String userId = (loginUser != null) ? loginUser.getUserId() : null; // 사용자 ID 가져오기
    String phone = (loginUser != null) ? loginUser.getPhone() : null; // 사용자 전화번호 가져오기
    String address = (loginUser != null) ? loginUser.getAddress() : null; // 사용자 주소 가져오기
    String totalPriceStr = request.getParameter("totalPrice");
    int totalPrice = 0;
	
    String productNo = (String) userSession.getAttribute("productNo");
    if (productNo == null) {
        productNo = "알 수 없음"; // null일 경우 기본값 설정
    }
    response.getWriter().println("상품 번호: " + productNo); // 추가

    // 배송 요청 사항 가져오기
    String deliveryRequest = (String) userSession.getAttribute("deliveryRequest");
    response.getWriter().println("전송받은  배송 요청 사항: " + deliveryRequest); // 추가
	
    // 총 금액 형식 변환
    if (totalPriceStr != null) {
        try {
            totalPrice = Integer.parseInt(totalPriceStr);
        } catch (NumberFormatException e) {
            response.getWriter().println("총 금액 형식이 잘못되었습니다.");
            return;
        }
    }

    // TID, PG Token, USER ID 출력
    response.getWriter().println("TID: " + tid);
    response.getWriter().println("PG Token: " + pg_token);
    response.getWriter().println("USER ID: " + userId);
    response.getWriter().println("PHONE: " + phone);
    response.getWriter().println("ADDRESS: " + address); // 주소 출력

    if (tid != null && pg_token != null && userId != null && phone != null) {
        HttpURLConnection conn = null;
        Connection dbConn = null;

        try {
            // 카카오 결제 승인 요청
            URL url = new URL(apiUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "KakaoAK " + adminKey);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            String params = "cid=TC0ONETIME" +
                    "&tid=" + tid +
                    "&partner_order_id=order_id_1234" + // 실제 주문 ID로 교체 필요
                    "&partner_user_id=" + userId + // 사용자 ID를 사용
                    "&pg_token=" + pg_token;

            conn.setDoOutput(true);
            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(params);
                dos.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();

                JSONParser parser = new JSONParser();
                JSONObject responseJson = (JSONObject) parser.parse(sb.toString());

                // 결제 성공 메시지 출력
                response.getWriter().println("결제가 성공적으로 완료되었습니다.");

                // 결제 금액 가져오기
                JSONObject amountJson = (JSONObject) responseJson.get("amount");
                if (amountJson != null) {
                    Long totalAmount = (Long) amountJson.get("total");

                    // 데이터베이스에 결제 정보 저장
                    dbConn = JDBCTemplate.getConnection();
                    try (PreparedStatement pstmt = dbConn.prepareStatement(
                            "INSERT INTO PAYMENT_INFO (ID, tid, pg_token, user_id, payment_status, amount) " +
                            "VALUES (payment_info_seq.NEXTVAL, ?, ?, ?, ?, ?)")) {
                        pstmt.setString(1, tid);
                        pstmt.setString(2, pg_token);
                        pstmt.setString(3, userId);
                        pstmt.setString(4, "SUCCESS"); 
                        pstmt.setLong(5, totalAmount); 

                        int rowsAffected = pstmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JDBCTemplate.commit(dbConn); // 커밋
                            response.getWriter().println("결제 정보가 데이터베이스에 저장되었습니다.");

                            // 주문 정보를 가져오기 위한 쿼리
                            String recipient = ""; // 수령인 이름 초기화

                            // 사용자 이름 조회
                            try (PreparedStatement userStmt = dbConn.prepareStatement(
                                    "SELECT USER_NAME FROM MEMBER WHERE USER_ID = ?")) {
                                userStmt.setString(1, userId);
                                ResultSet rs = userStmt.executeQuery();
                                if (rs.next()) {
                                    recipient = rs.getString("USER_NAME"); // 사용자 이름을 수령인으로 설정
                                }
                            } catch (SQLException e) {
                                e.printStackTrace(response.getWriter());
                            }

                            // paycode 생성
                            String paycode = UUID.randomUUID().toString(); // UUID로 paycode 생성

                            // orders 테이블에 주문 정보 저장
                            try (PreparedStatement orderStmt = dbConn.prepareStatement(
                                "INSERT INTO ORDERS (ORDER_NO, USER_ID, TOTAL_PRICE, RECIPIENT, ADDRESS, PHONE, STATUS, PAY_CODE, ORDER_ENROLL, RES_REQUIREMENT, PRODUCT_NO) " +
                                "VALUES (orders_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?)")) {
    
                                orderStmt.setString(1, userId);
                                orderStmt.setLong(2, totalPrice);
                                orderStmt.setString(3, recipient);
                                orderStmt.setString(4, address);
                                orderStmt.setString(5, phone);
                                orderStmt.setString(6, "1");  // 상태 코드
                                orderStmt.setString(7, paycode);
                                orderStmt.setString(8, deliveryRequest);
                                orderStmt.setString(9, productNo); // productNo 추가
    
                                int orderRowsAffected = orderStmt.executeUpdate();
                                if (orderRowsAffected > 0) {
                                    JDBCTemplate.commit(dbConn);
                                    response.getWriter().println("주문 정보가 orders 테이블에 저장되었습니다.");
                                } else {
                                    JDBCTemplate.rollback(dbConn);
                                    response.getWriter().println("주문 정보 저장 중 오류가 발생했습니다.");
                                }
                            } catch (SQLException e) {
                                e.printStackTrace(response.getWriter());
                                JDBCTemplate.rollback(dbConn);
                                response.getWriter().println("SQLException: " + e.getMessage());
                            }

                        } else {
                            JDBCTemplate.rollback(dbConn); // 롤백
                            response.getWriter().println("결제 정보 저장 중 오류가 발생했습니다.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace(response.getWriter());
                        JDBCTemplate.rollback(dbConn); // 롤백
                        response.getWriter().println("SQLException: " + e.getMessage());
                    }
                } else {
                    response.getWriter().println("결제 금액을 가져오는 데 실패했습니다. 응답: " + responseJson.toString());
                }
            } else {
                // 오류 응답 처리
                InputStream errStream = conn.getErrorStream();
                BufferedReader errBr = new BufferedReader(new InputStreamReader(errStream, "UTF-8"));
                StringBuilder errSb = new StringBuilder();
                String errLine;
                while ((errLine = errBr.readLine()) != null) {
                    errSb.append(errLine);
                }
                errBr.close();
                response.getWriter().println("결제 승인 요청 중 오류가 발생했습니다. 응답 코드: " + responseCode);
                response.getWriter().println("오류 메시지: " + errSb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        } finally {
            if (conn != null) conn.disconnect();
            if (dbConn != null) JDBCTemplate.close(dbConn);
        }
    } else {
        response.getWriter().println("결제를 완료하는 데 필요한 정보가 부족합니다.");
    }
%>

</body>
</html>
