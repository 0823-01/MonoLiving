package com.kh.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.product.model.service.ProductInfoService;
import com.kh.product.model.vo.ProductImage;
import com.kh.product.model.vo.ProductInfo;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ProductUpdateController
 */
@WebServlet("/updateProduct.pr")
public class ProductUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("---- 업데이트요청 ----");
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10*1024*1024;
			
			String savePath = request.getServletContext().getRealPath("/resources/product_upfiles/");
			
			MultipartRequest multiRequest 
				= new MultipartRequest(request,
									   savePath,
									   maxSize,
									   "UTF-8",
									   new MyFileRenamePolicy());
			int productNo = Integer.parseInt(multiRequest.getParameter("pno"));
			
			// 상품명 : productName
			String productName = multiRequest.getParameter("productName");
			
			
			// 상품설명 : productDescription
			String productDescription = multiRequest.getParameter("productDescription");
			
			// 카테고리번호 : categoryNo
			int categoryNo = Integer.parseInt(multiRequest.getParameter("categoryNo"));
			
			// 가격 : price
			int price = Integer.parseInt(multiRequest.getParameter("price"));
			
			// 재고 : productQuantity
			int productQuantity = Integer.parseInt(multiRequest.getParameter("productQuantity"));
			
			// 사이즈 : size
			String productSize = multiRequest.getParameter("productSize");
			
			// 재질 : material
			String material = multiRequest.getParameter("material");
			
			// 색상 : color
			String color = multiRequest.getParameter("color");
			
			// 조립여부 : assemblyYN
			String assemblyYN = multiRequest.getParameter("assemblyYN");
			
			// 할인율 : discount
			int discount = Integer.parseInt(multiRequest.getParameter("discount"));
			
			// 제조국 : country
			String country = multiRequest.getParameter("country");

			ProductInfo p = new ProductInfo();
			
			p.setProductNo(productNo);
			p.setProductName(productName);
			p.setCategoryNo(categoryNo);
			p.setProductDescription(productDescription);
			p.setPrice(price);
			p.setProductQuantity(productQuantity);
			p.setProductSize(productSize);
			p.setMaterial(material);
			p.setColor(color);
			p.setAssemblyYN(assemblyYN);
			p.setDiscount(discount);
			p.setCountry(country);
			
	
		    ArrayList<ProductImage> pImg = new ArrayList<>();

	        // 썸네일 이미지 처리
	        if (multiRequest.getOriginalFileName("thumbnailImage") != null) {
	        	ProductImage thumbnail = new ProductImage();
	            thumbnail.setImgOriginalFile(multiRequest.getOriginalFileName("thumbnailImage"));
	            thumbnail.setImgSaveFile(multiRequest.getFilesystemName("thumbnailImage"));
	            thumbnail.setRefPno(productNo);
	            thumbnail.setImageUrl("resources/product_upfiles/");

	            // 기존 썸네일이 있을 경우
	            if (multiRequest.getParameter("originFileNo") != null) {
	                int fileNo = Integer.parseInt(multiRequest.getParameter("originFileNo"));
	                String originChangeName = multiRequest.getParameter("originChangeName");
	                thumbnail.setImageNo(fileNo);
	                
	                // 기존 파일 삭제
	                new File(savePath + originChangeName).delete();
	            }
	            pImg.add(thumbnail);
	        }

	        // 상세 이미지 처리
	        for (int i = 1; i <= 5; i++) {
	            String detailFileParam = "detailImage" + i;
	            String detailOriginFileNo = "detailOriginFileNo" + i;
	            String detailOriginChangeName = "detailOriginChangeName" + i;

	            if (multiRequest.getOriginalFileName(detailFileParam) != null) {
	                ProductImage detailImage = new ProductImage();
	                detailImage.setImgOriginalFile(multiRequest.getOriginalFileName(detailFileParam));
	                detailImage.setImgSaveFile(multiRequest.getFilesystemName(detailFileParam));
	                detailImage.setRefPno(productNo);
	                detailImage.setImageUrl("resources/product_upfiles/");
	                
	                // 기존 상세 이미지가 있을 경우
	                if (multiRequest.getParameter(detailOriginFileNo) != null) {
	                    int fileNo = Integer.parseInt(multiRequest.getParameter(detailOriginFileNo));
	                    String originChangeName = multiRequest.getParameter(detailOriginChangeName);
	                    detailImage.setImageNo(fileNo);
	                    
	                    // 기존 파일 삭제
	                    new File(savePath + originChangeName).delete();
	                }
	                pImg.add(detailImage);
	            }
	        }

	        // 서비스 호출
	        int result = new ProductInfoService().updateProduct(p, pImg);

	        if(result > 0) {
	            request.getSession().setAttribute("alertMsg", "게시글 수정에 성공했습니다.");
	            response.sendRedirect(request.getContextPath() + "/productList.pr");
	        } else {
	            request.setAttribute("errorMsg", "게시글 수정에 실패했습니다.");
	            request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
	        }
			
					
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
