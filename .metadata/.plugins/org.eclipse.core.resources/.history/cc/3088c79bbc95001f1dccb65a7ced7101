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
@WebServlet("/detail-product.pr")
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
		
		// 상품번호 뽑기
		int productNo = Integer.parseInt(request.getParameter("pno"));
		
		ProductInfoService pService = new ProductInfoService();
		
		ProductInfo p = pService.selectProduct(productNo);
		
		if (p == null) {
		    System.out.println("No product found for productNo: " + productNo);
		    request.setAttribute("errorMsg", "해당 상품 정보를 찾을 수 없습니다.");
		} else {
		    request.setAttribute("p", p);
		}
		
		ArrayList<ProductImage> list = pService.selectProductImg(productNo);
		
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		
		 // list 안의 데이터를 모두 출력
	    for (ProductImage img : list) {
	        System.out.println("Image URL: " + img.getImageUrl());
	        System.out.println("Image File: " + img.getImgSaveFile());
	    }
	
		
		request.getRequestDispatcher("/views/product/productDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
