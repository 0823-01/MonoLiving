package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.util.Calendar;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 회원가입 요청 처리(POST방식)
        // 1) POST 방식이므로 인코딩 설정 
        request.setCharacterEncoding("UTF-8");
        
        // 2) 요청 시 전달값을 뽑아서 변수 및 객체에 담기
     // 요청 시 전달값을 뽑아서 변수 및 객체에 담기
        String userId = request.getParameter("userId");
        String userPwd = request.getParameter("userPwd");
        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender"); 
        String userBirth = request.getParameter("userBirth"); 
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        
        
        // Member객체로 담기 
        Member m = new Member(userId, userPwd, userName, gender, userBirth, email, phone, address);

        
        int result = new MemberService().insertMember(m);
        
        if(result > 0) {
            // 1회성 alert문구를 담아서 메인페이지로 url 재요청 
            HttpSession session = request.getSession();
            session.setAttribute("alertMsg", "회원가입 성공");
            response.sendRedirect(request.getContextPath());
        } else {
            // 에러 문구를 담아서 에러페이지 포워딩
            request.setAttribute("errorMsg", "회원 가입 실패");
            RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
            view.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
