package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MemberUpdateController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	request.setCharacterEncoding("UTF-8");
        
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender");
        String userBirth = request.getParameter("userBirth");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // Member 객체 생성
        Member m = new Member(userId, userName, gender, userBirth, email, phone, address);
        
        // 회원 정보 업데이트
        Member updateMem = new MemberService().updateMember(m);

        if (updateMem == null) {
        	
            request.setAttribute("errorMsg", "회원 정보 수정에 실패하였습니다.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
        } else {
        	
            HttpSession session = request.getSession();
            
            session.setAttribute("alert", "성공적으로 회원 정보를 수정하였습니다.");
            session.setAttribute("loginUser", updateMem);

            response.sendRedirect(request.getContextPath() + "/updateForm.me"); // 페이지 리다이렉트 경로 설정
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
