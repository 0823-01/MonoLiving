package com.kh.cart.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cart.model.dao.CartDao;
import com.kh.cart.model.vo.Cart;

public class CartService {
	
	/**
	 * 장바구니 상품 총 개수 조회 서비스
	 * @return 장바구니 내 상품 개수
	 */
	public int selectListCount() {
		Connection conn = getConnection();
		int listCount = new CartDao().selectListCount(conn);
		close(conn);
		return listCount;
	}

	/**
	 * 장바구니 목록 조회 서비스
	 * @param userNo 사용자 번호
	 * @return 장바구니 상품 목록
	 */
	public ArrayList<Cart> getCartList(int userNo) {
		Connection conn = getConnection();
		ArrayList<Cart> list = new CartDao().getCartList(conn, userNo);
		close(conn);
		return list;
	}

	/**
	 * 장바구니 상품 삭제 서비스
	 * @param cartNo 장바구니 번호
	 * @return 삭제 성공 여부
	 */
	public int deleteCartItem(int cartNo) {
		Connection conn = getConnection();
		int result = new CartDao().deleteCartItem(conn, cartNo);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deleteCheckItems(String[] cartNos) {
	    Connection conn = getConnection();
	    int result = new CartDao().deleteCheckItems(conn, cartNos);

	    if (result > 0) {
	        commit(conn);
	    } else {
	        rollback(conn);
	    }

	    close(conn);
	    return result;
	}


	/**
	 * 장바구니에 상품 추가 서비스
	 * @param userNo 사용자 번호
	 * @param productNo 상품 번호
	 * @param quantity 상품 수량
	 * @return 추가 성공 여부
	 */
	public int addToCart(int userNo, int productNo, int quantity) {
		Connection conn = getConnection();
		int result = new CartDao().addToCart(conn, userNo, productNo, quantity);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	/**
	 * 장바구니 상품 수량 업데이트 서비스
	 * @param cartNo 장바구니 번호
	 * @param productQuantity 변경된 수량
	 * @return 업데이트 성공 여부
	 */
	public int updateCartQuantity(int cartNo, int productQuantity) {
		Connection conn = getConnection();
		int result = new CartDao().updateCartQuantity(conn, cartNo, productQuantity);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	/**
	 * 장바구니 상품 총 가격 조회 서비스
	 * @param cartNo 장바구니 번호
	 * @return 총 가격
	 */
	public int getCartTotalPrice(int cartNo) {
		Connection conn = getConnection();
		int totalPrice = new CartDao().getCartTotalPrice(conn, cartNo);
		close(conn);
		return totalPrice;
	}
}
