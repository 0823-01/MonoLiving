package com.kh.product.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.PageInfo;
import com.kh.product.model.dao.ProductInfoDao;
import com.kh.product.model.vo.ProductImage;
import com.kh.product.model.vo.ProductInfo;

public class ProductInfoService {

	/**
	 * 상품 전체 갯수 조회용 서비스 메소드
	 * @return => 상품 갯수
	 */
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new ProductInfoDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
		
	}  // selectListCount 메소드 영역 끝

	
	
	/**
	 * 상품 목록 조회용 서비스 메소드
	 * @param pi => 구간별로 끊어서 조회할 때 필요한 변수
	 * @return => 조회된 상품들
	 */
	public ArrayList<ProductInfo> selectList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<ProductInfo> list = new ProductInfoDao().selectListCount(conn, pi);
		
		close(conn);
		
		return list;
		
	}  // selectList 메소드 영역 끝



	/**
	 * 상품 상세조회 서비스용
	 * @param productNo => 상세조회할 상품 번호
	 * @return => 상세조회된 상품 한 개의 정보
	 */
	public ProductInfo selectProduct(int productNo) {
		
		Connection conn = getConnection();
		
		ProductInfo p = new ProductInfoDao().selectProduct(conn, productNo);
		
		close(conn);
		
		return p;
		
	}  // selectProduct 메소드 영역 끝


	

	/**
	 * 상품 첨부파일(상품이미지) 조회 서비스용 메소드
	 * @param productNo => 첨부파일을 조회하고자하는 상품 번호
	 * @return => 조회된첨부파일 정보
	 */
	public ArrayList<ProductImage> selectProductImg(int productNo) {
		
		Connection conn = getConnection();
		
		ArrayList<ProductImage> list = new ProductInfoDao().selectProductImg(conn, productNo);
		
		close(conn);
		
		return list;
		
		
	}  // selectProductImg 메소드 영역 끝



	/**
	 * 카테고리별 침대 상품 목록 조회
	 * @return => 조회된 침대 상품 목록
	 */
	public ArrayList<ProductInfo> selectBedList() {
		
		Connection conn = getConnection();
		
		ArrayList<ProductInfo> list = new ProductInfoDao() .selectBedList(conn);
		
		close(conn);
		
		return list;
		
	}  // selectBedList 메소드 영역 끝
	
	
	
	/**
	 * 테이블,책상 상품 목록 조회
	 * @return
	 */
	public ArrayList<ProductInfo> selectTableList() {
		Connection conn = getConnection();
		
		ArrayList<ProductInfo> list = new ProductInfoDao() .selectTableList(conn);
		
		close(conn);
		
		return list;
	}  // selectTableList 메소드 영역 긑



	/**
	 * 카테고리별 수납장 상품 목록 조회
	 * @return => 조회된 수납장 상품 목록
	 */
	public ArrayList<ProductInfo> selectCabinetList() {
		
		Connection conn = getConnection();
		
		ArrayList<ProductInfo> list = new ProductInfoDao() .selectCabinetList(conn);
		
		close(conn);
		
		return list;
	}  // selectCabinetList 메소드 영역 끝





	/**
	 * 카테고리별 의자 상품 목록 조회
	 * @return=> 조회된 의자 상품 목록
	 */
	public ArrayList<ProductInfo> selectChairList() {
		Connection conn = getConnection();
		
		ArrayList<ProductInfo> list = new ProductInfoDao() .selectChairList(conn);
		
		close(conn);
		
		return list;
		
	}  // selectChairList 메소드 영역 끝



	/**
	 * 카테고리별 진열장.책장 상품 목록 조회
	 * @return
	 */
	public ArrayList<ProductInfo> selectShowcaseList() {
		Connection conn = getConnection();
		
		ArrayList<ProductInfo> list = new ProductInfoDao() .selectShowcaseList(conn);
		
		close(conn);
		
		return list;
	}    // selectShowcaseList 메소드 영역 끝



	/**
	 * 카테고리별 옷장.행거 상품 목록 조회
	 * @return
	 */
	public ArrayList<ProductInfo> selectClosetList() {
		Connection conn = getConnection();
		
		ArrayList<ProductInfo> list = new ProductInfoDao() .selectClosetList(conn);
		
		close(conn);
		
		return list;
	}  // selectClosetList 메소드 영역 끝



	/**
	 * 키워드로 상품 검색하기
	 * @param keyword => 입력한 키워드
	 * @return => 키워드로 조회된 상품 정보
	 */
	public ArrayList<ProductInfo> selectSearchList(String keyword) {
		Connection conn = getConnection();
		
		ArrayList<ProductInfo> list = new ProductInfoDao().selectSearchList(conn, keyword);
		
		close(conn);
		
		return list;
	}



	/**
	 * 낮은가격순 상품 정렬 조회용
	 * @param pi
	 * @return
	 */
	public ArrayList<ProductInfo> selectAscList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<ProductInfo> list = new ProductInfoDao().selectAscListCount(conn, pi);
		
		close(conn);
		
		return list;
	}



	/**
	 * 높은가격순 상품 정렬 조회용
	 * @param pi
	 * @return
	 */
	public ArrayList<ProductInfo> selectDescList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<ProductInfo> list = new ProductInfoDao().selectDescListCount(conn, pi);

		close(conn);
		
		return list;
	}













}
