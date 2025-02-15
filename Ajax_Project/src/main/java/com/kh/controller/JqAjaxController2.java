package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class JqAjaxController2
 */
@WebServlet("/jqAjax2.do")
public class JqAjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST방식의 ajax 요청 받기
		//> 기존의 동기식 요청에서 POST 요청일 경우에는 먼저 요처시 전달값을 뽑기 전에 " 인코딩 설정" 먼저 해줘야함
		//	비동기식 요청에서는 POST 요청일 경우에도 
		//  인코딩 설정을 하지 않아도 된다.
		
		// 요청 시 전달값 뽑기
		// - 이름 : name
		String name = request.getParameter("name");
//			 나이 : age
		int age = Integer.parseInt(request.getParameter("age"));
		
//		System.out.println(name);
//		System.out.println(age);
		
		
//		버전1) 응답데이터를(여러개)를 하나의 문자열로 연이어서 한번에 보내기 
		
		
//		DB까지 다녀왔다라는 가정 하에 응답데이터 
//		String responseData = "이름 "+ name +",나이 :" +age;
		
//		1.응답데이터형식 미 인코딩 설정
//		response.setContentType("text/html; charset=UTF-8");
		
//		2.요청한 jsp와의 출력스트림 생성
//		PrintWriter out = response.getWriter();
		
//		3.응답데이터 내보내기
//		out.print(responseData);
		
//		response.getWriter().print(responseData);

		
//		버전2) 응답데이터(여러개)를 각각 보내기
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
//		out.print(name);
//		out.print(age);
//		스트림의 선입선출 특징 떄문에 응답데이터를 여러개 내보내도 
//		내보내고 도착한 순서대로 응답데이터가 결국 하나의 문자열로 연이어져서 응답됨 
		
//		응답하고자 하는 데이터가 여러개일 경우 처리 방법 
//		JSON( Java Script Object Notation)
//		자바스크립트 객체 표기법
//		ajax 응답 시 데이터 전송에 사용되는 포맷 형식 중 하나 
		
//		JSON을 이용하려면 라이브러리 jar를 연동해야함
//		기본적으로 자바에서 제공안됨 
//		json-simple-1.1.1jar 파일을 lib 폴더에 연도앟ㅁ 
		
//		json 처리 시 사용되는 클래스 종류 
		
//		응답할 데이터가 여러개일 경우
//		1.JSONArray
//		:[value, value, value]
//		=> 즉 자바스크립트의 배열 형식으로 묶어서 한번에 보낼수 있다.
//		2.JSONObject
//		:{ket : value, key: value, key: value,....}
//		즉 자바스크립트의 객체 형식으로 묶어서 한번에 보낼 수 있다. 
		
//		JASON Array 이용 
		JSONArray jArr = new JSONArray();
		jArr.add(name); //["박말똥"]
		jArr.add(age);	//["박말똥",35]
//		JASONArray 는 자바의 ArrayList와 같은 구조 
//		자바스크립트의 배열과 같은 구조 
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jArr);
//		문자열로 응답된거임 (배열인척)
//		응답할 데이터의 컨텐트 타입을 지정 하지 않았기 때문 
//		(text/html;)
		
		JSONObject jObj = new JSONObject();
		jObj.put("name", name); 
		jObj.put("age", age);
		// > JSONObject는 자바의 HashMap과 비슷한 구조 자바스크립트의 객체와 비슷한 구조
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jObj);
//		
//		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
