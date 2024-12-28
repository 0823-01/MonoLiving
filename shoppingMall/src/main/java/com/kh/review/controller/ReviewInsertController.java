package com.kh.review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;
import com.kh.review.model.service.ReviewService;
import com.kh.review.model.vo.Review;
import com.kh.review.model.vo.ReviewImage;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ReviewInsertController
 */
@WebServlet("/insert.re")
public class ReviewInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10 * 1024 * 1024;
			
			String savePath = request.getServletContext().getRealPath("/resources/review_upfiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request,
																  savePath,
																  maxSize,
																  "UTF-8",
																  new MyFileRenamePolicy());
			
			String reviewWriter = multiRequest.getParameter("userNo");
			String reviewComment = multiRequest.getParameter("reviewComment");
			String reviewStarStr = multiRequest.getParameter("reviewStar");
	        int reviewStar = 0;

	        // reviewStar가 null이 아닌 경우 정수로 변환
	        if (reviewStarStr != null && !reviewStarStr.isEmpty()) {
	            try {
	                reviewStar = Integer.parseInt(reviewStarStr);
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	            }
	        }

	        // 받은 reviewStar 값을 콘솔에 출력 (또는 DB 저장 등 추가 로직 수행 가능)
	        System.out.println("사용자가 선택한 별점 값: " + reviewStar);
	        
			Review r = new Review();
			r.setReviewWriter(reviewWriter);
			r.setReviewComment(reviewComment);
			r.setReviewStar(reviewStar);
			System.out.println("리뷰 별점: " + r.getReviewStar());
			
			
			ArrayList<ReviewImage> list = new ArrayList<>();
			
			for(int i = 1; i <= 3; i++) {
				
				String key = "file" + i;
				
				if(multiRequest.getOriginalFileName(key) != null) {
					
					ReviewImage ri = new ReviewImage();
					
					String originName = multiRequest.getOriginalFileName(key);
					String changeName = multiRequest.getFilesystemName(key);
					String filePath = "resources/review_upfiles/";
					
					ri.setReviewOriginName(originName);
					ri.setReviewChangeName(changeName);
					ri.setReviewImgPath(filePath);
					
					list.add(ri);
				}
				
			}
			
			int result = new ReviewService().insertReview(r, list);
			
			if(result > 0) {
				
				request.getSession().setAttribute("alerMsg", "성공적으로 리뷰가 등록되었습니다.");
				response.sendRedirect(request.getContextPath() + "/rlist.re");
			} else {
				
				request.setAttribute("errorMsg", "리뷰 등록에 실패했습니다.");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
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
