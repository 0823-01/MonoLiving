package com.kh.product.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.model.vo.PageInfo;
import com.kh.product.model.vo.ProductImage;
import com.kh.product.model.vo.ProductInfo;

public class ProductInfoDao {
	
		private Properties prop = new Properties();
		
		public ProductInfoDao() {
			String fileName = ProductInfoDao.class.getClassLoader().getResource("/sql/product/product-mapper.xml").getPath();
		
			try {
				prop.loadFromXML(new FileInputStream(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	/**
	 * 총 상품 갯수를 구하는 쿼리문용 실행 메소드
	 * @param conn => DB 접속용 객체
	 * @return => 총 상품 갯수
	 */
	public int selectListCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
		
	}  // selectListCount 메소드 영역 끝


	
	/**
	 * 상품 목록 조회용 쿼리문 실행 메소드
	 * @param conn => DB 접속용 객체
	 * @param pi => 구간별로 끊을 때 필요한 변수
	 * @return => 조회된 상품 목록들
	 */
	public ArrayList<ProductInfo> selectListCount(Connection conn, PageInfo pi) {
		
		ArrayList<ProductInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ProductInfo p = new ProductInfo();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;

		
	}	// selectListCount 메소드 영역 끝


	/**
	 * 상품 상세조회용 쿼리문 실행 메소드
	 * @param conn => DB 접속용 객체
	 * @param productNo => 상세조회할 상품 번호
	 * @return => 상세조회된 상품 정보
	 */
	public ProductInfo selectProduct(Connection conn, int productNo) {
		
		ProductInfo p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, productNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				p = new ProductInfo();
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setCategoryName(rset.getString("CATEGORY_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDiscountPrice(rset.getInt("DISCOUNT_PRICE"));
				p.setProductDescription(rset.getString("PRODUCT_DESCRIPTION"));
				p.setProductSize(rset.getString("PRODUCT_SIZE"));
				p.setMaterial(rset.getString("MATERIAL"));
				p.setColor(rset.getString("COLOR"));
				p.setAssemblyYN(rset.getString("ASSEMBLY_YN"));
				p.setDiscount(rset.getInt("DISCOUNT"));
				p.setCountry(rset.getString("COUNTRY"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return p;
		
		
	}  // selectProduct 메소드 영역 끝


	/**
	 * 상품 첨부파일 조회용 쿼리문 실행 메소드
	 * @param conn => DB 접속용 객체
	 * @param productNo => 첨부파일을 조회하고자 하는 상품 번호
	 * @return => 조회된 첨부파일 정보
	 */
	public ArrayList<ProductImage> selectProductImg(Connection conn, int productNo) {
		
		ArrayList<ProductImage> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectProductImg");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, productNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductImage pImg = new ProductImage();
				
				pImg.setImageNo(rset.getInt("IMAGE_NO"));
				pImg.setImgOriginalFile(rset.getString("IMG_ORIGINAL_FILE"));
				pImg.setImgSaveFile(rset.getString("IMG_SAVE_FILE"));
				pImg.setImageUrl(rset.getString("IMAGE_URL"));
				
				list.add(pImg);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
		
	}  // selectProductImg 메소드 영역 끝


	/**
	 * 카테고리별 침대 상품 목록 조회용 쿼리문 실행 메소드
	 * @param conn => DB 접속용 객체
	 * @return => 조회된 침대 상품 목록
	 */
	public ArrayList<ProductInfo> selectBedList(Connection conn) {
		// SELECT문 => ResultSet 객체 (여러행)
		// => ArrayList
		ArrayList<ProductInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBedList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// 완성된 sql무이니까 패스
			
			// 4,5) 쿼리문 실행후 결과받깅
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ProductInfo p = new ProductInfo();
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	/**
	 * 테이블 상품 목록 조회용 쿼리문 실행 메소드
	 * @param conn
	 * @return
	 */
	public ArrayList<ProductInfo> selectTableList(Connection conn) {
		// SELECT문 => ResultSet 객체 (여러행)
		// => ArrayList
		ArrayList<ProductInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectTableList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// 완성된 sql무이니까 패스
			
			// 4,5) 쿼리문 실행후 결과받깅
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ProductInfo p = new ProductInfo();
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}  // selectTableList 메소드 영역 끝
	
	


	/**
	 * 카테고리별 수납장 상품 목록 조회용 쿼리문 실행 메소드
	 * @param conn
	 * @return
	 */
	public ArrayList<ProductInfo> selectCabinetList(Connection conn) {
		// SELECT문 => ResultSet 객체 (여러행)
		// => ArrayList
		ArrayList<ProductInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCabinetList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// 완성된 sql무이니까 패스
			
			// 4,5) 쿼리문 실행후 결과받깅
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ProductInfo p = new ProductInfo();
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	/**
	 * 카테고리별 의자 상품 목록 조회용 쿼리문 실행 메소드
	 * @param conn
	 * @return
	 */
	public ArrayList<ProductInfo> selectChairList(Connection conn) {
		ArrayList<ProductInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectChairList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// 완성된 sql무이니까 패스
			
			// 4,5) 쿼리문 실행후 결과받깅
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ProductInfo p = new ProductInfo();
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}  // selectChairList 메소드 영역 끝


	/**
	 * 카테고리별 진열장.책장 상품 목록 조회용 쿼리문 실행 메소드
	 * @param conn
	 * @return
	 */
	public ArrayList<ProductInfo> selectShowcaseList(Connection conn) {
		ArrayList<ProductInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectShowcaseList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// 완성된 sql무이니까 패스
			
			// 4,5) 쿼리문 실행후 결과받깅
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ProductInfo p = new ProductInfo();
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}  // selectShowcaseList 메소드 영역 끝


	/**
	 * 카테고리별 진열장.책장 상품 목록 조회용 쿼리문 실행 메소드
	 * @param conn
	 * @return
	 */
	public ArrayList<ProductInfo> selectClosetList(Connection conn) {
		ArrayList<ProductInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectClosetList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// 완성된 sql무이니까 패스
			
			// 4,5) 쿼리문 실행후 결과받깅
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ProductInfo p = new ProductInfo();
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}  // selectClosetList 메소드 영역 끝


	public ArrayList<ProductInfo> selectPriceListDesc(Connection conn) {
		
		ArrayList<ProductInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectPriceListDesc");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ProductInfo p = new ProductInfo();
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			
		}
		
		return list;
	}


	/**
	 * 키워드로 상품 조회용 쿼리문 실행 메소드
	 * @param conn => DB 접속용 객체
	 * @param keyword => 검색하고자하는 상품 키워드
	 * @return => 조회된 상품 한건
	 */
	public ArrayList<ProductInfo> selectSearchList(Connection conn, String keyword) {
		
		ArrayList<ProductInfo> list = new ArrayList<>();		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSearchList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductInfo p = new ProductInfo();
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
		
	}


	/**
	 * 낮은가격순 상품 조회용 쿼리문 실행 메소드
	 * @param conn
	 * @param pi
	 * @return
	 */
	public ArrayList<ProductInfo> selectAscListCount(Connection conn, PageInfo pi) {
		ArrayList<ProductInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAscListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ProductInfo p = new ProductInfo();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	/**
	 * 높은가격순 상품 조회용 쿼리문 실행 메소드
	 * @param conn
	 * @param pi
	 * @return
	 */
	public ArrayList<ProductInfo> selectDescListCount(Connection conn, PageInfo pi) {
		ArrayList<ProductInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectDescListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ProductInfo p = new ProductInfo();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	
	




}
