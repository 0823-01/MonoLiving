package com.kh.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.model.service.CartService;

/**
 * Servlet implementation class CartAddController
 */
@WebServlet("/add.ct")
public class CartAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public CartAddController() {
        super();
    }

    /**
     * 장바구니에 상품 추가 요청 처리
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userNo = Integer.parseInt(request.getParameter("userNo")); // 사용자 번호
        int productNo = Integer.parseInt(request.getParameter("productNo")); // 상품 번호
        int quantity = Integer.parseInt(request.getParameter("quantity")); // 상품 수량
        
        CartService cartService = new CartService(); // 서비스 객체 생성
        int result = cartService.addToCart(userNo, productNo, quantity); // 장바구니에 상품 추가
        
        response.setContentType("application/json"); // 응답 형식 설정
        PrintWriter out = response.getWriter();
        
        // 결과에 따라 응답 반환
        if (result > 0) {
            out.print("{\"status\":\"success\"}"); // 성공
        } else {
            out.print("{\"status\":\"fail\"}"); // 실패
        }
        out.flush();
        out.close(); // 스트림 닫기
    }
}
