package com.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.product.model.service.ProductInfoService;
import com.kh.product.model.vo.ProductInfo;

/**
 * Servlet implementation class PriceListDescController
 */
@WebServlet("/priceListDesc.pr")
public class PriceListDescController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PriceListDescController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ----- 페이징 처리 ------
		// 필요한 변수
		// 기본적으로 구할 수 있는 4개의 변수
		// + 그 4개의 변수를 통해 계산해서 도출해야하는 3개의 변수
		// > 총 7개의 변수가 필요함
		int listCount;  // 현재 총 게시글 갯수
		
		int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수 (몇개 단위씩 볼거냐)
		
		int maxPage;  // 가장 마지막 페이지가 몇번페이지인지 알려주는 변수 (즉, 총 페이지 수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝수
		
		// * listCount : 총 게시글 갯수
		// COUNT 함수로 BOARD 테이블의 유효한 데이터의 갯수를 긁어오기!
		listCount = new ProductInfoService().selectListCount();
		
		
		
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
		
		// * boardLimit : 한 페이지에 보여질 게시글의 최대 갯수(게시글 몇개 단위씩 보여질건지)
		// 한 페이지 당 몇개씩 보여질건지 임의의 값으로 지정해주면 됨
		boardLimit = 12;
		// > pageLimit 와 boardLimit는 계산하기 쉽게
		//   10으로 고정 후 계산할 것임
		

		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		

		
		startPage = (currentPage -1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		

		// 위의 7개의 변수를 각 필드로 가지고 있는 vo클래스를 하나
		// > PagreInfo 클래스로 만들어서 객체로 가공
		//   한번 만들어 두면 공지사항 목록조회, 일반게시판 목록조회, 사진게시판 목록조회등..
		//   페이징 처리가 들어가는 부분에서는 다 재사용 가능!
		PageInfo pi = new PageInfo(listCount, currentPage,
						     	   pageLimit, boardLimit,
						     	   maxPage, startPage, endPage);
		
		// pi를 전달하면서 Service로 요청 후 결과 받기
		ArrayList<ProductInfo> list = new ProductInfoService().selectDescList(pi);
		// > list에는 항상 구간별로 최대 10개의 목록만 조회될 것
		
	
	
		
		// 응답데이터로 보내기
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		
		
		request.getRequestDispatcher("/views/product/prcieDescList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
