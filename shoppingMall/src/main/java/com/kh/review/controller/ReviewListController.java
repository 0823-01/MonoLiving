package com.kh.review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.vo.Member;
import com.kh.review.model.service.ReviewService;
import com.kh.review.model.vo.Review;

/**
 * Servlet implementation class ReviewListController
 */
@WebServlet("/rlist.re")
public class ReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
//	/	int productNo = Integer.parseInt(request.getParameter("productNo"));
		int productNo = 2;	// 나중에 번호 받아오깅
		
		ReviewService rService = new ReviewService();
		
		ArrayList<Review> list = new ReviewService().selectReviewList(productNo);
		
		if(list.isEmpty()) {
			
			request.setAttribute("errorPage", "등록된 리뷰가 없습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp");
		} else {
			
			ArrayList<Review> r = rService.selectReviewList(productNo);
			
			request.setAttribute("r", r);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/views/review/reviewListView.jsp").forward(request, response);
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
