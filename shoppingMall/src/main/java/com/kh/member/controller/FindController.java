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
 * Servlet implementation class FindController
 */
@WebServlet("/pwReset.pw")
public class FindController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FindController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String userId = request.getParameter("userId");

        MemberService memberService = new MemberService();
        String userPwd = memberService.findPassword(userName, email, userId);

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        if (userPwd != null) {
            out.print("{\"status\": \"success\", \"message\": \"비밀번호는: " + userPwd + "\"}");
        } else {
            response.sendRedirect(request.getContextPath() + "/views/member/pwReset.jsp");
        }
        
        out.flush();
        out.close();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 인코딩 설정
        doGet(request, response);
    }
}
