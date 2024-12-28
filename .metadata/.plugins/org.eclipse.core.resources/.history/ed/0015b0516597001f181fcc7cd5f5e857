package com.kh.order.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static com.kh.common.JDBCTemplate.*; // JDBCTemplate에서 제공하는 메서드들을 import

public class OrderDao {
    
    // 배송 상태 업데이트 메서드
    public boolean updateOrderStatus(Connection conn, int orderId, int status) {
        String query = "UPDATE orders SET status = ? WHERE order_no = ?";
        PreparedStatement pstmt = null;
        boolean isUpdated = false;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, status);
            pstmt.setInt(2, orderId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                isUpdated = true; // 업데이트 성공
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt); // PreparedStatement 자원 해제
        }
        
        return isUpdated;
    }
}
