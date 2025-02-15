package com.kh.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.model.service.CartService;
import com.kh.cart.model.vo.Cart;
import com.kh.member.model.vo.Member;


/**
 * Servlet implementation class CartUpdateQuantityController
 */
@WebServlet("/uquantity.ct")
public class CartUpdateQuantityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartUpdateQuantityController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		 int cartNo = Integer.parseInt(request.getParameter("cartNo"));
//		    int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
//		    
//		    Cart cart = new Cart();
//		    cart.setCartNo(cartNo);
//		    cart.setProductQuantity(productQuantity);
//
//		    
//		    response.setContentType("text/plain");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
