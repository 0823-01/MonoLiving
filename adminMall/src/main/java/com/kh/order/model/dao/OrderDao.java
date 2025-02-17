package com.kh.order.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate; // JDBC 템플릿 예시
import com.kh.order.model.vo.Order;

public class OrderDao {
    
    public ArrayList<Order> getAllOrders(Connection conn) {
        ArrayList<Order> orderList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT o.ORDER_NO, o.TOTAL_PRICE, o.RECIPIENT, o.ADDRESS, o.PHONE, o.STATUS, " +
                       "o.RES_REQUIREMENT, o.PAY_CODE, o.ORDER_ENROLL, o.REFUND_DATE, o.USER_ID, " + 
                       "p.PRODUCT_NAME " + 
                       "FROM ORDERS o " +
                       "JOIN PRODUCT_INFO p ON o.PRODUCT_NO = p.PRODUCT_NO";

        try {
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
        }
        return orderList;
    }

    public boolean updateOrderStatus(Connection conn, int orderId, int status) {
        PreparedStatement pstmt = null;
        int result = 0;

        String query = "UPDATE ORDERS SET STATUS = ? WHERE ORDER_NO = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, status);
            pstmt.setInt(2, orderId);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(pstmt);
        }

        return result > 0; // 업데이트가 성공했으면 true 반환
    }
}
