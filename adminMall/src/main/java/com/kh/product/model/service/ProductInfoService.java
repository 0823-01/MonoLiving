package com.kh.product.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.PageInfo;
import com.kh.product.model.dao.ProductInfoDao;
import com.kh.product.model.vo.ProductImage;
import com.kh.product.model.vo.ProductInfo;

public class ProductInfoService {

	/**
	 * 관리자 전용 상품 목록을 조회해오는 메소드
	 * @return => 조회된 상품의 총 갯수
	 */
	/*
	public ArrayList<ProductInfo> selectProduct() {
		
		Connection conn = getConnection();
		
		ArrayList<ProductInfo> list = new ProductInfoDao().selectProduct(conn);
		
		close(conn);
		
		return list;
	}  // selectProduct 메소드 영역 끝
	*/

	
	/**
	 * 제품 상세 정보 조회용 서비스 메소드 (상품 정보들)
	 * @param productNo => 상세조회할 제품 번호
	 * @return => 조회된 제품 상세 정보 한 건 중 정보들
	 */
	public ProductInfo selectProductInfo(int productNo) {
		
		Connection conn = getConnection();
		
		ProductInfo p = new ProductInfoDao().selectProductInfo(conn, productNo);
		
		// select문이므로 트랜잭션 처리는 패스
		
		close(conn);
		
		return p;
		
	}  // selectProductInfo 메소드 영역 끝
	
	
	/**
	 * 제품 상세 정보 조회용 서비스 메소드(상품 이미지들)
	 * @param productNo => 상세 조회할 제품 번호
	 * @return => 조회된 상품 상세 정보 한 건 중 이미지들
	 */
	public ArrayList<ProductImage> selectProductImage(int productNo) {
		
		Connection conn = getConnection();
		
		ArrayList<ProductImage> list = new ProductInfoDao().selectProductImage(conn, productNo);
		
		close(conn);
		
		return list;
		
	}  // selectProductImage 메소드 영역 끝



	/**
	 * 상품 등록 서비스 메소드
	 * @param p => 작성할 제품 정보 내용
	 * @param list => 등록하고자하는 제품 이미지들
	 * @return => 처리된 행의 갯수
	 */
	public int productInsert(ProductInfo p, ArrayList<ProductImage> list) {
		
		Connection conn = getConnection();
		
		// ProductInfo 테이블에 insert 요청
		int result1 = new ProductInfoDao().productInsert(conn,p);
		
		// ProductImage 테이블에 insert 요청
		int result2 = new ProductInfoDao().productInsert(conn, list);
		
		
		if(result1>0 && result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1*result2;
		
		
	}  // productInsert 메소드 영역 끝


	
	/**
	 * 총 상품 갯수 조회용 서비스 메소드 
	 * @return => 상품의 총 갯수
	 */
	public int selectProductCount() {
		
		Connection conn = getConnection();
		
		int listCount = new ProductInfoDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}
	

	/**
	 * 상품 목록 조회용 쿼리문 메소드
	 * @param pi => 구간별로 끊어서 조회할 때 필요한 변수
	 * @return  => 조회된 상품들
	 */
	public ArrayList<ProductInfo> selectList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<ProductInfo> list = new ProductInfoDao().selectList(conn, pi);
		
		// select문이므로 트랜잭션 과정을 패스
		
		close(conn);
		
		return list;
		
		
	}  // selectList 메소드 영역 끝


	public int updateProduct(ProductInfo p, ArrayList<ProductImage> pImg) {
		
		Connection conn = getConnection();
	    
	    int result1 = new ProductInfoDao().updateProductInfo(conn, p);
	    System.out.println("ProductInfo update result: " + result1);
	    int result2 = 1;

	    for (ProductImage pi : pImg) {
	        if (pi.getImageNo() != 0) {
	            // Update existing image
	        	System.out.println("기존 첨부파일이 있을때 - update");
	        	System.out.println(pi.getImageNo());
	        	
	            int updateResult = new ProductInfoDao().updateProductImage(conn, pi);
	            System.out.println("Updating existing image. Result: " + updateResult);
	            result2 *= updateResult;
	        } else {
	            // Insert new image
	        	System.out.println("기존 첨부파일이 없을때 - insert");
	        	System.out.println(pi.getImageNo());
	        	
	            int insertResult = new ProductInfoDao().insertNewProductImage(conn, pi);
	            System.out.println("Inserting new image. Result: " + insertResult);
	            result2 *= insertResult;
	        }
	    }

	    System.out.println("Final result1: " + result1 + ", result2: " + result2);
	    if (result1 > 0 && result2 > 0) {
	        commit(conn);
	        System.out.println("Transaction committed.");
	    } else {
	        rollback(conn);
	        System.out.println("Connection closed.");
	    }
	    

	    close(conn);
	    return result1 * result2;
		
	}  // updateProduct 메소드 영역 끝


	/**
	 * 상품 삭제용 서비스 메소드
	 * @param productNo => 삭제할 상품 번호
	 * @return => 
	 */
	public int deleteProduct(int productNo) {
		
		Connection conn = getConnection();
		
		int result = new ProductInfoDao().deleteProduct(conn, productNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
		
	}  // deleteProduct 메소드 영역 끝



	



	

}
