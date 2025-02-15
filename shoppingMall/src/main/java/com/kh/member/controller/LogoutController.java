package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout.me")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		로그아웃 요청 처리
//		session에 담긴 로그인 한 회원의 정보를 날려버리기
		
		HttpSession session = request.getSession();
//		로그아웃 구현 방법 2가지 
//		1.seesion을 만료시키는 방법
//		session을 무효화 시키기
//		session.invalidate();
		
		
//		2.seesion에 담겨있는 loginUsert에 대한 
//		키+벨류 세트만 제거하는 방법
//		(removeAtribute 메소드 활용)
		session.removeAttribute("loginUser");
		
//		단 session에 loginUser 이외의 다른 데이터도 담겨있다면 첫번쨰 방법은 사용 불가 
//		session에 반드신 로그인한 회원의 정보만 담으라는 법은 없기에 
//		1회성 alert문구 또한 sessio에 담아볼 것 
		
		
		session.setAttribute("alertMsg", "성공적으로 로그아웃 되었다.");
		
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
