package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JqAjaxController1
 */
@WebServlet("/jqAjax1.do")
public class JqAjaxController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		System.out.println("잘 호출 됨?");
		
		
//		요청 시 전달값 뽑기
		
//		request.getParameter("키값") : String(벨류값)
//		request.getParameterValues("키값") : String[](밸류값)
//		입력값: input
		String str = request.getParameter("input");
//		System.out.println("요청시 전달 값 : " + str);
//		원래의 흐름 : Service - >Dao -> DB
//		
		String responseData = "입력된값 : " + str + ",길이:"+str.length();
		
//		응답데이터 보내기
//		기존의 동기식 요청에서는 응답페이즈를 통쨰로 넘겼었음
//		비동기식 요청에서는 응답데이터만 넘기는 것 
//		1.혹시라도 응답데이터에 한글이 있을 경우에 대비해서
		
		response.setContentType("text/html; charset=UTF-8");
		
//		2.요청을 했었던 jsp와의 출력스트림 통로 열기
		PrintWriter out = response.getWriter();
		
//		3.출력 스트림 통로로 응답데이터 보내기
		out.print(responseData);
//		자바 코드안에 html 코드를 넣었을 때 썼었던 구문임 
		//		응답데이터에 대한 데이터 형식과 인코딩 방법을 지정 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
