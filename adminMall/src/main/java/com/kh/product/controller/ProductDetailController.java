package com.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.product.model.service.ProductInfoService;
import com.kh.product.model.vo.ProductImage;
import com.kh.product.model.vo.ProductInfo;

/**
 * Servlet implementation class ProductDetailController
 */
@WebServlet("/product-detail.pr")
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 제품 상세보기 요청 처리
		
		// GET 방식으로 pno 키값으로 공지사항 게시글 번호 넘겨줬음
		// > 클릭했을 때의 공지사항 글 번호를 일단 뽑아내야 함
		int productNo = Integer.parseInt(request.getParameter("pno"));
		
		ProductInfo p = new ProductInfoService().selectProductInfo(productNo);
		ArrayList<ProductImage> pImg = new ProductInfoService().selectProductImage(productNo);
		
//		 // pImg 리스트 내용 출력 (디버깅)
//	    System.out.println("pImg 리스트 크기: " + pImg.size());
//	    for (ProductImage img : pImg) {
//	        System.out.println("이미지 경로: " + img.getImageUrl());
//	        System.out.println("저장 파일명: " + img.getImgSaveFile());
//	        System.out.println("이미지 설명: " + img.getImageDescription()); // 이미지에 설명 필드가 있다고 가정
//	    }
		
		
		request.setAttribute("p", p);
		request.setAttribute("pImg", pImg);
		
		request.getRequestDispatcher("/views/product/AdProductDetail.jsp").forward(request, response);
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
