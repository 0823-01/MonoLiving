package com.kh.product.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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

    public ProductInsertController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (ServletFileUpload.isMultipartContent(request)) {
            int maxSize = 10 * 1024 * 1024;

            // 실제로 저장할 서버의 물리적인 경로
            String savePath = request.getServletContext().getRealPath("/resources/product_upfiles/");

            // MultipartRequest 객체 생성하기
            MultipartRequest multiRequest = new MultipartRequest(request,
                    savePath,
                    maxSize,
                    "UTF-8",
                    new MyFileRenamePolicy());

            // 요청 시 전달값
            String productName = multiRequest.getParameter("productName");
            String productDescription = multiRequest.getParameter("productDescription");
            int categoryNo = Integer.parseInt(multiRequest.getParameter("categoryNo"));
            int price = Integer.parseInt(multiRequest.getParameter("price"));
            int productQuantity = Integer.parseInt(multiRequest.getParameter("productQuantity"));
            String productSize = multiRequest.getParameter("productSize");
            String material = multiRequest.getParameter("material");
            String color = multiRequest.getParameter("color");
            String assemblyYN = multiRequest.getParameter("assemblyYN");
            int discount = Integer.parseInt(multiRequest.getParameter("discount"));
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

            for (int i = 1; i <= 6; i++) {
                String key = "detailImage" + i;
                if (multiRequest.getOriginalFileName(key) != null) {
                    // ProductImage 객체 생성
                    ProductImage pi = new ProductImage();

                    String imgOriginalFile = multiRequest.getOriginalFileName(key);
                    String imgSaveFile = multiRequest.getFilesystemName(key);
                    String imageUrl = "resources/product_upfiles/";

                    String thumbnail = (i == 1) ? "Y" : "N";

                    pi.setImgOriginalFile(imgOriginalFile);
                    pi.setImgSaveFile(imgSaveFile);
                    pi.setImageUrl(imageUrl);
                    pi.setThumbnail(thumbnail);
                    list.add(pi);

                    // 파일 복사 로직
                    File sourceFile = new File(savePath + imgSaveFile);
                    // 복사할 경로 설정
                    String targetPath = "C:/00_semiProject/shoppingMall/src/main/webapp/resources/product_upfiles/";

                    File destinationFile = new File(targetPath + imgSaveFile);

                    // 복사 경로 출력
                    System.out.println("소스 경로: " + sourceFile.getAbsolutePath());
                    System.out.println("대상 경로: " + destinationFile.getAbsolutePath());

                    try {
                        // 파일 복사
                        Path sourcePath = sourceFile.toPath();
                        Path destPath = destinationFile.toPath();
                        Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("파일 복사 성공: " + imgSaveFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                        // 에러 처리 로직 추가 가능
                    }
                }
            }

            int result = new ProductInfoService().productInsert(p, list);

            if (result > 0) {
                request.getSession().setAttribute("alertMsg", "상품등록에 성공했습니다.");
                response.sendRedirect(request.getContextPath() + "/productList.pr");
            } else {
                request.setAttribute("errorMsg", "상품등록에 실패했습니다.");
                request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
