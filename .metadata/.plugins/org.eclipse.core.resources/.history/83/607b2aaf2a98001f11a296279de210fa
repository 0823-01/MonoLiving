package com.kh.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cart.model.service.CartService;
import com.kh.cart.model.vo.Cart;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class CartListController
 */
@WebServlet("/list.ct")
public class CartListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(session.getAttribute("loginUser") == null) { // 로그인이 안 됐다?
			
			session.setAttribute("alertMsg", "로그인 후 이용 가능");
			response.sendRedirect(request.getContextPath());
			
		} else { // 로그인이 됐다?
			
			int userNo = loginUser.getUserNo();
			
			ArrayList<Cart> list = new CartService().getCartList(userNo);
			
			if (list == null) { // 장바구니가 비었다?
				
				list = new ArrayList<>();
			}
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/cart/cartListView.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
