package com.kh.product.controller;

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
 * Servlet implementation class ProductInsertController
 */
@WebServlet("/insert.pr")
public class ProductInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 제품 등록 요청 처리는 POST 방식임
		// POST 방식이므로 인코딩 설정 먼저
		request.setCharacterEncoding("UTF-8");
		
		// multipart/form-data 형식의 요청인지 먼저 검사하고 로직 들어가기
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 1.1 전송 용량 제한
			int maxSize = 10*1024*1024;
			
			// 1.2 실제로 저장할 서버의 물리적인 경로
			String savePath = request.getServletContext().getRealPath("/resources/product_upfiles/");
			
			// 2. MultipartRequest 객체 생성하기
			// > 넘어온 파일명 수정, 그 파일이 서버로 업로드, MultipartRequest 객체 생성
			MultipartRequest multiRequest = new MultipartRequest(request,
																 savePath,
																 maxSize,
																 "UTF-8",
																 new MyFileRenamePolicy());
			
			// 3. MultipartReques 객체로부터 요청 시 전달값 뽑기
			// 요청 시 전달값
			
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
			
			// 제품 이미지에 대한 정보 (썸네일 + 상세 이미지 = 최대 6개)
			ArrayList<ProductImage> list = new ArrayList<>();
			
			for(int i=1; i<=6; i++) {
				String key = "detailImage" + i;
				// 해당 키값으로 넘어온 첨부파일이 있는지를 검사
				if(multiRequest.getOriginalFileName(key) != null) {
					
					// > 넘어온 첨부파일이 있을 경우
					
					// ProductImage 객체 생성
					ProductImage pi = new ProductImage();
					
					// Attachment 테이블 Insert 구문에서 필요한 데이터들을
					// at 객체의 필드에 담아둘 것
					// 원본명, 수정명, 저장경로, 파일레벨
					String imgOriginalFile 
							= multiRequest.getOriginalFileName(key);
					
					String imgSaveFile 
							= multiRequest.getFilesystemName(key);
					
					String imageUrl = "resources/product_upfiles/";
					
					String thumbnail = "Y";
					if(i == 1) {
						thumbnail = "Y";	
					} else {
						thumbnail = "N";	
					}
					
					pi.setImgOriginalFile(imgOriginalFile);
					pi.setImgSaveFile(imgSaveFile);
					pi.setImageUrl(imageUrl);
					pi.setThumbnail(thumbnail);
					
					list.add(pi);
					
				}
			}
			
			// 이 시점 기준으로 p, list가 셋팅되어있을 것 !!
			// > 모두 하나의 트랜잭션으로 묶어서 처리해야 함!
			
			int result = new ProductInfoService().productInsert(p, list);
			
			// 처리된 결과를 가지고 사용자가 보게될 응답페이지를 지정
			if(result > 0) {
			
				// 일회성 알람 문구를 담아서
				// 사진게시글 목록 조회 화면으로 url 재요청
				request.getSession().setAttribute("alertMsg", "상품등록에 성공했습니다.");
				response.sendRedirect(request.getContextPath() + "/productList.pr");
				
			}else {
				
				// 에러 문구를 담아서 에러페이지로 포워딩
				request.setAttribute("errorMsg", "상품등록에 실패했습니다.");
				
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
