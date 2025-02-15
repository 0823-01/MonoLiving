package com.kh.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.model.vo.Member;

/**
 * Servlet implementation class JqAjaxController4
 */
@WebServlet("/jqAjax4.do")
public class JqAjaxController4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 전체조회 요청을 처리했다라는 가정 하에 응답할 데이터 
		//회ㅜ언들의 정보가 담겨있는 ArrayList
		
		ArrayList<Member> list = new ArrayList<>();
		
		list.add(new Member(1,"고길동",50,"남"));
		list.add(new Member(2,"김똘똘",50,"남"));
		list.add(new Member(3,"김똘추",50,"남"));
		list.add(new Member(4,"김멍똥",50,"남"));
		
		response.setContentType("application/json; charset=UTF-8");
		
		/*
		 * 		
		 * // 원래의 흐름
		JSONArray jArr = new JSONArray();
		
		for(Member m : list) {
			
			JSONObject jObj = new JSONObject();
			jObj.put("userNo", m.getUserNo());
			jObj.put("userName", m.getUserName());
			jObj.put("age", m.getAge());
			jObj.put("gender", m.getGender());
			
			jArr.add(jObj);
		}
		 * 
		 */
		
		//GSON을 이용하면 더 간단해진다.
		//Gson gson = new Gson();
		//gson.toJson(list, response.getWriter());
		
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
