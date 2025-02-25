package com.kh.order.model.dao;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.order.model.vo.Order;

public class OrderDao {
    private Properties prop = new Properties();

    public OrderDao() {
        String fileName = OrderDao.class.getClassLoader().getResource("/sql/order/order-mapper.xml").getPath();
        
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int insertOrder(Order order) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = prop.getProperty("insertOrder");  // Retrieve the SQL statement

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, order.getTotalPrice());  // total_price
            pstmt.setString(2, order.getRecipient()); // recipient
            pstmt.setString(3, order.getAddress());   // address
            pstmt.setString(4, order.getPhone());      // phone
            pstmt.setInt(5, order.getStatus()); // status
            pstmt.setString(6, order.getResRequirement()); // res_requirement
            pstmt.setString(7, order.getPayCode());    // pay_code
            pstmt.setInt(8, order.getUserId());        // user_id

            result = pstmt.executeUpdate();
            if (result > 0) {
                commit(conn);
            } else {
                rollback(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(conn);  // 오류 발생 시 롤백
        } finally {
            close(pstmt);
            close(conn);
        }
        return result;
    }

    // 모든 주문을 조회하는 메서드 추가
    public ArrayList<Order> selectAllOrders() {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Order> orderList = new ArrayList<>();
        String sql = prop.getProperty("selectAllOrders"); // 주문 조회 SQL 쿼리 가져오기

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderNo(rs.getInt("order_id")); // 주문 ID 수정
                order.setRecipient(rs.getString("recipient")); // 수령인 (칼럼명에 맞게 수정)
                order.setAddress(rs.getString("address")); // 주소 (칼럼명에 맞게 수정)
                order.setPhone(rs.getString("phone")); // 전화번호 (칼럼명에 맞게 수정)
                order.setTotalPrice(rs.getInt("total_price")); // 전체 금액 (칼럼명에 맞게 수정)
                order.setStatus(rs.getInt("status")); // 배송 상태

                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리
        } finally {
            close(rs);
            close(pstmt);
            close(conn);
        }
        return orderList;
    }
}
