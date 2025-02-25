package com.kh.order.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate; // JDBC 템플릿 예시
import com.kh.order.model.vo.Order;

public class OrderService {
    // 모든 주문 정보를 가져오는 메서드
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT o.ORDER_NO, o.TOTAL_PRICE, o.RECIPIENT, o.ADDRESS, o.PHONE, o.STATUS, " +
                       "o.RES_REQUIREMENT, o.PAY_CODE, o.ORDER_ENROLL, o.REFUND_DATE, o.USER_ID, " + 
                       "p.PRODUCT_NAME " + 
                       "FROM ORDERS o " +
                       "JOIN PRODUCT_INFO p ON o.PRODUCT_NO = p.PRODUCT_NO";

        try {
            conn = JDBCTemplate.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderNo(rs.getInt("ORDER_NO"));
                order.setTotalPrice(rs.getInt("TOTAL_PRICE"));
                order.setRecipient(rs.getString("RECIPIENT"));
                order.setAddress(rs.getString("ADDRESS"));
                order.setPhone(rs.getString("PHONE"));
                order.setStatus(rs.getInt("STATUS"));
                order.setResRequirement(rs.getString("RES_REQUIREMENT"));
                order.setPayCode(rs.getString("PAY_CODE"));
                order.setOrderEnroll(rs.getDate("ORDER_ENROLL"));
                order.setRefundDate(rs.getDate("REFUND_DATE"));
                order.setUserId(rs.getString("USER_ID"));
                order.setProductName(rs.getString("PRODUCT_NAME"));

                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(pstmt);
            JDBCTemplate.close(conn);
        }
        return orderList;
    }

    // 주문 상태를 업데이트하는 메서드
    public boolean updateOrderStatus(String orderId, String status) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean isUpdated = false;

        try {
            conn = JDBCTemplate.getConnection(); // 연결 가져오기
            String sql = "UPDATE ORDERS SET STATUS = ? WHERE ORDER_NO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(status)); // 상태를 정수로 변환
            pstmt.setInt(2, Integer.parseInt(orderId)); // 주문 ID를 정수로 변환
            
            isUpdated = pstmt.executeUpdate() > 0; // 업데이트 성공 여부 확인
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(pstmt);
            JDBCTemplate.close(conn);
        }
        return isUpdated; // 업데이트 결과 반환
    }
}
