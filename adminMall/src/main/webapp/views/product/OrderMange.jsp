<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kh.order.model.service.OrderService" %>
<%@ page import="com.kh.order.model.vo.Order" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 관리 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }
        /*
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        */
        
         .container {
	        width: 100%;
	        margin: 0 auto;
	        padding: 20px;
	    }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .status-select {
            padding: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>

	<%@ include file="../common/nav.jsp" %>


	<div id="layoutSidenav_content">
       <main>
			        
	            <div class="container">
	                <h1>주문 관리</h1>
	                <table>
	                    <thead>
	                        <tr>
	                            <th width="80">주문 ID</th>
	                            <th width="100">상품명</th>
	                            <th width="90">수령인</th>
	                            <th width="110">주소</th>
	                            <th>전화번호</th>
	                            <th width="100">주문금액</th>
	                            <th>배송 상태</th>
	                            <th width="100">요청사항</th>
	                            <th>결제코드</th>
	                    <!--    <th>환불 날짜</th> -->
	                            <th>회원 ID</th>
	                            <th width="120">결제 날짜</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                        <%
	                            OrderService orderService = new OrderService();
	                            ArrayList<Order> orderList = orderService.getAllOrders();
	
	                            for (Order order : orderList) {
	                        %>
	                        <tr>
	                            <td><%= order.getOrderNo() %></td>
	                            <td><%= order.getProductName().length() <= 5 ?
	                            		order.getProductName() : 
	                            		order.getProductName().substring(0, 4) + "..." %></td>
	                            <td><%= order.getRecipient() %></td>
	                            
	                            
	                            <td><%= order.getAddress().length() <= 6 ?
	                            		order.getAddress() : 
	                            		order.getAddress().substring(0,5) + "..." %></td>
	                            
	                            
	                            
	                            <td><%= order.getPhone() %></td>
	                            <td><%= order.getTotalPrice() %> 원</td>
	                            <td>
	                               <form action="<%= request.getContextPath() %>/updateOrderStatus.me" method="post">
	                                   <input type="hidden" name="orderId" value="<%= order.getOrderNo() %>">
	                                   <select class="status-select" name="status">
	                                       <option value="1" <%= order.getStatus() == 1 ? "selected" : "" %>>배송중</option>
	                                       <option value="2" <%= order.getStatus() == 2 ? "selected" : "" %>>배송완료</option>
	                                   </select>
	                                   <button type="submit">저장</button>
	                               </form>
	                            </td>
                     
	                            <td><%= (order.getResRequirement() != null && order.getResRequirement().length() <= 3) ? 
						            order.getResRequirement() 
						            : (order.getResRequirement() != null ? order.getResRequirement().substring(0, 2) + "..." : "") %></td>
	                            <td><%= order.getPayCode() %></td>
	                         <!-- <td><%= order.getRefundDate() != null ? order.getRefundDate() : "없음" %></td>  --> 
	                            <td><%= order.getUserId() %></td>
	                            <td><%= order.getOrderEnroll() != null ? order.getOrderEnroll() : "없음" %></td>
	                        </tr>
	                        <%
	                            }
	                        %>
	                    </tbody>
	                </table>
	            </div>	        
	    </main>
	  </div>
</body>
</html>
