package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.kh.model.vo.Member;

/**
 * Servlet implementation class JqAjaxController3
 */
@WebServlet("/jqAjax3.do")
public class JqAjaxController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//회원 상세조회 요청 시 보낸 회원번호 뽑기(userNo)
		int userNo  = Integer.parseInt(request.getParameter("userNo"));
		
		//DB로부터 회원 한명의 정보를 조회해왔다라는 가정 하에 
		
		Member m = new Member(userNo,"고길동",50,"남");
		
		//response.setContentType("text/html; charset=UTF-8");
		//response.getWriter().print(m);
		//그냥 출력 통로로 Member 객체인 m을 응답하게 되면
		//알게 모르게 toString 메소드가 호출되면서 각 필드값이 하나의 문자열로 
		//연이어져서 응답되게 됨.
		
		//JSON 을 활용해서 응답해보기
		//JSONObject 를 활용해서 응답해볼 것 
		//{속성명 : 속성값, 속성며이 속성값}
		//속성명으로 "필드명"을 속성값으로 "필드값"
		
		JSONObject jObj = new JSONObject();
		jObj.put("userNo", m.getUserNo());
		jObj.put("userName", m.getUserName());
		jObj.put("age", m.getAge());
		jObj.put("gender", m.getGender());
		
		//response.setContentType("application/json; charset=UTF-8");
		//response.getWriter().print(jObj);
		//>vo객체 단위를 응답데이터로 넘겨야 할 경우에는
		//JSONObject 타입으로 옮겨 담은 후 응답한다
		
		// > VO 필드 갯수만큼 일일이 다 JSON 으로 옮겨담는게 정석이나 코드가 
		// 불필요허게 길어지고 실수할 가능성도 높다.
		
		//GSON(Google JSON)
		//VO나 ArrayList 와 같은 자바 타입을 
		//JSON타입으로 손쉽게 자동으로 타입을 옮겨주는 라이브러리
		
		//아무리 gson을 쓰더라도 
		response.setContentType("application/json; charset=UTF-8");
		Gson gson = new Gson();
		//
		//Gson 객체에서 제공하는 toJson 메소드
		//[표현법]
		// gson.toJson(응답데이터, 응답할 출력스트림객체);
		gson.toJson(m, response.getWriter());
		
		
		//GSON을 이용하면 vo의 경우 자동으로 jsonobject로 가공된다 
		//이 떄 , 속성명이 해당 vo의 필드명으로 잡히게 된다.
		
		//ArrayList의 경우 자동으로 JSONArray로 가공된다.
		//이 때 인덱스 수가 유지된다.
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
