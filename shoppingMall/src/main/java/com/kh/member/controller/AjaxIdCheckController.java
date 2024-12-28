package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class AjaxIdCheckController
 */
@WebServlet("/idCheck.me")
public class AjaxIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxIdCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//아이디 중복 체크 요청 처리 
		
//		요청 시 전달값을 뽑아서 변수 및 객체에 담기
		// - 중복 검사용 아이디: 
		String checkId = request.getParameter("checkId");
		
		int count = new MemberService().idCheck(checkId);
		//count가 1이면 사용불가 / 0이면 사용 가능 
		
		
//		처리 결과에 따라 응답데이터를 넘기기
		
		response.setContentType("text/html; charset=UTF-8");
		if(count>0) { //존재하는 아이디가 이미 있을 경우(사용불가)
	
		//사용 불가일 경우 "NNNNN"을 응답데이터로 넘기기
			response.getWriter().print("NNNNN");
		}else { //존재하는 아이디가 없을 경우 (사용가능)
			response.getWriter().print("NNNNY");
			//사용 가능일 경우 "NNNNY"를 응답데이터로 넘기기
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
