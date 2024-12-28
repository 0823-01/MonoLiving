package com.kh.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class FindIdController
 */
@WebServlet("/IdReset.id")
public class FindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindIdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");

        MemberService memberService = new MemberService();
        String userId = memberService.findId(userName, email);

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        if (userId != null) {
            out.print("{\"status\": \"success\", \"message\": \"아이디는: " + userId + "\"}");
        } else {
            response.sendRedirect(request.getContextPath() + "/views/member/idReset.jsp");
        }
        
        out.flush();
        out.close();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 인코딩 설정
        doGet(request, response);
    }
}
