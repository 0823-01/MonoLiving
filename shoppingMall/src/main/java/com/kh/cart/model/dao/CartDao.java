package com.kh.cart.model.dao;

import static com.kh.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.cart.model.vo.Cart;

public class CartDao {
	
	private Properties prop = new Properties();
	
	/**
	 * 생성자 - SQL 쿼리 로드
	 */
	public CartDao() {
		String fileName = CartDao.class.getResource("/sql/cart/cart-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 장바구니 상품 총 개수 조회
	 * @param conn
	 * @return 장바구니 상품 개수
	 */
	public int selectListCount(Connection conn) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				listCount = rset.getInt("COUNT");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	/**
	 * 사용자별 장바구니 목록 조회
	 * @param conn
	 * @param userNo 사용자 번호
	 * @return 장바구니 목록
	 */
	public ArrayList<Cart> getCartList(Connection conn, int userNo) {
		
		ArrayList<Cart> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("getCartList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Cart(
					rset.getInt("CART_NO"),
					rset.getInt("PRODUCT_QUANTITY"),
					rset.getInt("USER_NO"),
					rset.getInt("PRODUCT_NO"),
					rset.getString("PRODUCT_NAME"),
					rset.getInt("ORIGINAL_PRICE"),
					rset.getInt("DISCOUNTED_PRICE"),
					rset.getString("TITLEIMG")
				));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	/**
	 * 장바구니 상품 삭제
	 * @param conn
	 * @param cartNo 장바구니 번호
	 * @return 삭제 성공 여부
	 */
	public int deleteCartItem(Connection conn, int cartNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deleteCartItem");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 장바구니에 상품 추가
	 * @param conn
	 * @param userNo 사용자 번호
	 * @param productNo 상품 번호
	 * @param quantity 상품 수량
	 * @return 추가 성공 여부
	 */
	public int addToCart(Connection conn, int userNo, int productNo, int quantity) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("addToCart");

		try {
			pstmt = conn.prepareStatement(sql);
			
			int totalPrice = getPriceFromDatabase(productNo) * quantity; // 총 가격 계산
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, productNo);
			pstmt.setInt(3, quantity);
			pstmt.setInt(4, totalPrice);
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 장바구니 상품 수량 업데이트
	 * @param conn
	 * @param cartNo 장바구니 번호
	 * @param productQuantity 변경된 수량
	 * @return 업데이트 성공 여부
	 */
	public int updateCartQuantity(Connection conn, int cartNo, int productQuantity) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("updateCartQuantity");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productQuantity);
			pstmt.setInt(2, cartNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 장바구니 상품의 총 가격 조회
	 * @param conn
	 * @param cartNo 장바구니 번호
	 * @return 총 가격
	 */
	public int getCartTotalPrice(Connection conn, int cartNo) {
		int totalPrice = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("getCartTotalPrice");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNo);
			pstmt.setInt(2, cartNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				totalPrice = rset.getInt("TOTAL_PRICE");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return totalPrice;
	}

	/**
	 * 상품의 가격 조회 (DB에서 직접 조회)
	 * @param productNo 상품 번호
	 * @return 상품 가격
	 */
	private int getPriceFromDatabase(int productNo) {
		int price = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT PRICE FROM PRODUCT_INFO WHERE PRODUCT_NO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				price = rset.getInt("PRICE");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}

		return price;
	}
}
