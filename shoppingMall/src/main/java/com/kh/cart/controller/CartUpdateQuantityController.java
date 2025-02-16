package com.kh.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kh.cart.model.service.CartService;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class CartUpdateQuantityController
 */
@WebServlet("/updateQuantity.ct")
public class CartUpdateQuantityController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CartUpdateQuantityController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartNo = Integer.parseInt(request.getParameter("cartNo")); // 장바구니 번호
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity")); // 변경된 수량

        // 장바구니 수량 업데이트 서비스 호출
        CartService cartService = new CartService();
        int result = cartService.updateCartQuantity(cartNo, productQuantity);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON 응답 생성
        JSONObject jsonResponse = new JSONObject();
        if (result > 0) {
            int updatedTotalPrice = cartService.getCartTotalPrice(cartNo);
            jsonResponse.put("status", "success");
            jsonResponse.put("updatedQuantity", productQuantity);
            jsonResponse.put("updatedTotalPrice", updatedTotalPrice);
        } else {
            jsonResponse.put("status", "fail");
        }

        response.getWriter().print(jsonResponse.toJSONString());
    }
}
