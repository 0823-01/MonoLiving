package com.kh.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;


/**
 * Servlet implementation class MemberManagmentController
 */
@WebServlet("/member-managment.me")
public class MemberManagmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberManagmentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 전체 상품 목록에서 보여질 페이징바
		// > 총 7개의 변수가 필요함
		int listCount;  // 현재 총 상품 갯수
		//int currentPage; // 현재 사용자가 보고자하는 페이지 (즉, 사용자가 요청한 페이지)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		int productLimit; // 한 페이지에 보여질 상품의 최대 갯수 (몇개 단위씩 볼거냐)
		
		int maxPage;  // 가장 마지막 페이지가 몇번페이지인지 알려주는 변수 (즉, 총 페이지 수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝수
		

		 listCount = new MemberService().selectMemberCount();
		
		// * currentPage : 현재 사용자가 요청한 페이지
		// 요청 시 쿼리스트링을 통해
		// currentPage 라는 키값으로 전달값을 넘겨줬었음 
		 String currentPageStr = request.getParameter("currentPage");
		    int currentPage = 1; // 기본값 설정

		    // currentPage가 null이 아닐 경우에만 파싱
		    if (currentPageStr != null) {
		        try {
		            currentPage = Integer.parseInt(currentPageStr);
		        } catch (NumberFormatException e) {
		            currentPage = 1; // 예외 발생 시 기본값 설정
		        }
		    }
		
		// * pageLimit : 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		// 한 페이지 당 페이지 목록들을 몇개 단위씩 보여질건지
		// 임의의 값으로 지정해주면됨
		pageLimit = 10;
		
		// * productLimit : 한 페이지에 보여질 게시글의 최대 갯수(게시글 몇개 단위씩 보여질건지)
		// 한 페이지 당 몇개씩 보여질건지 임의의 값으로 지정해주면 됨
		productLimit = 10;
		// > pageLimit 와 boardLimit는 계산하기 쉽게
		//   10으로 고정 후 계산할 것임

		maxPage = (int)Math.ceil((double)listCount / productLimit);
		
		// * startPage : 페이지 하단에 보여질 페이징바의 시작수	
		startPage = (currentPage -1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage,
		     	   pageLimit, productLimit,
		     	   maxPage, startPage, endPage);
		
		// pi를 전달하면서 Service로 요청 후 결과 받기
		ArrayList<Member> list = null ;
		list = new MemberService().selectMemberList(pi);
		// > list에는 항상 구간별로 최대 10개의 목록만 조회될 것
	

		// 상품 목록 페이지에서 필요한 데이터 조회해오기
		//list = new ProductInfoService().selectProduct();
		// 위에서 언급됐으므로 잠깐 주석
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("/views/member/memberList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
