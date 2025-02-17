package com.kh.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.model.service.CartService;

/**
 * Servlet implementation class DelectCheckController
 */
@WebServlet("/deleteCheck.ct")
public class DeleteCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cartNosStr = request.getParameter("cartNos"); // "7,12,15" 형식
        if (cartNosStr == null || cartNosStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/list.ct");
            return;
        }

        String[] cartNosArray = cartNosStr.split(","); // ["7", "12", "15"]
        int result = new CartService().deleteCheckItems(cartNosArray);

        response.sendRedirect(request.getContextPath() + "/list.ct"); // 삭제 후 장바구니 목록으로 이동
    }

}
