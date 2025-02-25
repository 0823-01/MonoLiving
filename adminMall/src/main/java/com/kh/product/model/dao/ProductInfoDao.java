package com.kh.product.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;

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
	 * 상품 목록 제품 조회 쿼리문용 실행 메소드
	 * @param conn => DB 접속용 객체
	 * @return => 총 제품 갯수
	 */
		/*
		public ArrayList<ProductInfo> selectProduct(Connection conn) {
		    ArrayList<ProductInfo> list = new ArrayList<>();
		    PreparedStatement pstmt = null;
		    ResultSet rset = null;

		    // Connection 객체가 null인지 확인
		    if (conn == null) {
		        System.out.println("DB 연결 객체(conn)가 null입니다.");
		        return list;
		    }

		    // SQL 쿼리 확인
		    String sql = prop.getProperty("selectProduct");
		    if (sql == null) {
		        System.out.println("SQL 쿼리(selectProduct)가 null입니다. properties 파일을 확인하세요.");
		        return list;
		    }

		    try {
		        pstmt = conn.prepareStatement(sql);
		        rset = pstmt.executeQuery();

		        while (rset.next()) {
		            ProductInfo p = new ProductInfo();
		            p.setProductNo(rset.getInt("PRODUCT_NO"));
		            p.setCategoryName(rset.getString("CATEGORY_NAME"));
		            p.setProductName(rset.getString("PRODUCT_NAME"));
		            p.setPrice(rset.getInt("PRICE"));
		            p.setProductQuantity(rset.getInt("PRODUCT_QUANTITY"));

		            list.add(p);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        close(rset);
		        close(pstmt);
		    }
		    return list;
		}  // selectProduct 메소드 영역 끝
		*/

		
	/**
	 * 상품 상세 조회를 위한 쿼리문 실행 메소드 (상품 정보)
	 * @param conn => DB 접속용 객체
	 * @param productNo => 조회할 상품 번호
	 * @return => 반환할 상품 한 개의 정보 내용물
	 */
	public ProductInfo selectProductInfo(Connection conn, int productNo) {
		
		ProductInfo p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectProductInfo");
		
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
				p.setProductQuantity(rset.getInt("PRODUCT_QUANTITY"));
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
		
		
	}  // selectProductInfo 메소드 영역 끝
	
	
	
	

	/**
	 * 상품 상세 조회를 위한 쿼리문 실행 메소드 (상품 이미지들)
	 * @param conn => DB 접속용 객체
	 * @param productNo => 조회할 상품 번호
	 * @return => 조회된 상품 이미지들 반환
	 */
	public ArrayList<ProductImage> selectProductImage(Connection conn, int productNo) {
		
		ArrayList<ProductImage> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectProductImage");
		
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
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
		
		
	}  // selectProductImage 메소드 영역 끝


	/**
	 * 상품 등록 쿼리문 실행 메소드
	 * @param conn => DB 접속용 객체
	 * @param p => 등록하고자하는 상품 정보
	 * @return  => 처리된 행의 갯수
	 */
	public int productInsert(Connection conn, ProductInfo p) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertProductInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getProductName());
			pstmt.setString(2, p.getProductDescription());
			pstmt.setInt(3, p.getCategoryNo());
			pstmt.setInt(4, p.getPrice());
			pstmt.setInt(5, p.getProductQuantity());
			pstmt.setString(6, p.getProductSize());
			pstmt.setString(7, p.getMaterial());
			pstmt.setString(8, p.getColor());
			pstmt.setString(9, p.getAssemblyYN());
			pstmt.setInt(10, p.getDiscount());
			pstmt.setString(11, p.getCountry());
			
			result = pstmt.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}


	/**
	 * 상품 등록의 첨부파일들을 등록하는 쿼리문 실행 메소드
	 * @param conn => DB 접속용 객체
	 * @param list => 등록하고자하는 첨부파일들
	 * @return => 처리된 행의 갯수
	 */
	public int productInsert(Connection conn, ArrayList<ProductImage> list) {
		
		int result = 1;
		// insert를 반복 진행 후
		// result에 처리된 행의 갯수를 계속 누적해서
		// 곱할 것임
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertProductImage");
		
		try {
			
			for(ProductImage pi : list) {
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, pi.getImgOriginalFile());
				pstmt.setString(2, pi.getImgSaveFile());
				pstmt.setString(3, pi.getImageUrl());
				pstmt.setString(4, pi.getThumbnail());
				
				result *= pstmt.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}  // productInsert 메소드 영역 끝


	/**
	 * 상품 목록 조회용 쿼리문 실행 메소드
	 * @param conn => DB 접속용 객체
	 * @param pi => 구간별로 끊을 때 필요한 변수
	 * @return => 조회된 일반 상품 목록들
	 */
	public ArrayList<ProductInfo> selectList(Connection conn, PageInfo pi) {
		
		ArrayList<ProductInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getProductLimit() + 1;
			int endRow = startRow + pi.getProductLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductInfo p = new ProductInfo(rset.getInt("PRODUCT_NO"),
											   (rset.getString("CATEGORY_NAME")),
											   (rset.getString("PRODUCT_NAME")),
											   (rset.getInt("PRICE")),
											   (rset.getInt("PRODUCT_QUANTITY")));
				list.add(p);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}  // selectList 메소드 영역 끝


	
	
	/**
	 * 총 상품 갯수를 구하는 쿼리문용 실행 메소드 
	 * @param conn => DB 접속용 객체
	 * @return => 총 상품 갯수
	 */
	public int selectListCount(Connection conn) {
		
		// SELECT문 => ResultSet (단일행)
		
		// 변수 셋팅
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
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}


	/**
	 * 상품 수정 쿼리문용 실행 메소드
	 * @param conn
	 * @param p
	 * @return
	 */
	public int updateProductInfo(Connection conn, ProductInfo p) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateProductInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getProductName());
			pstmt.setInt(2, p.getCategoryNo());
			pstmt.setInt(3, p.getPrice());
			pstmt.setInt(4, p.getProductQuantity());
			pstmt.setString(5, p.getProductDescription());
			pstmt.setString(6, p.getProductSize());
			pstmt.setString(7, p.getMaterial());
			pstmt.setString(8, p.getColor());
			pstmt.setString(9, p.getAssemblyYN());
			pstmt.setInt(10, p.getDiscount());
			pstmt.setString(11, p.getCountry());
			pstmt.setInt(12, p.getProductNo());
			
			
	   System.out.println("SQL Query: " + sql);
        System.out.println("Parameters - ProductName: " + p.getProductName() + ", CategoryNo: " + p.getCategoryNo() +
                           ", Price: " + p.getPrice() + ", ProductQuantity: " + p.getProductQuantity() +
                           ", ProductDescription: " + p.getProductDescription() + ", ProductSize: " + p.getProductSize() +
                           ", Material: " + p.getMaterial() + ", Color: " + p.getColor() +
                           ", AssemblyYN: " + p.getAssemblyYN() + ", Discount: " + p.getDiscount() +
                           ", Country: " + p.getCountry() + ", ProductNo: " + p.getProductNo());

			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}  // updateProductInfo 메소드 영역 끝

	/*
	/**
	 * 첨부파일 수정용 쿼리문 - 기존에 파일이 있었을 경우
	 * @param conn
	 * @param pImg
	 * @return
	 
	public int updateProductImage(Connection conn, ArrayList<ProductImage> pImg) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateProductImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// pImgList에 있는 각 ProductImage 객체에 대해 쿼리 실행
	        for (ProductImage p : pImg) {
	            pstmt.setString(1, p.getImgOriginalFile());
	            pstmt.setString(2, p.getImgSaveFile());
	            pstmt.setInt(3, p.getImageNo());

	            result += pstmt.executeUpdate();  // 성공한 레코드 수 누적
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
		
	}  // updateProductImage 메소드 영여 끝


	/**
	 * 첨부파일 수정용 쿼리문 - 기존에 파일이 없었을 경우
	 * @param conn
	 * @param pImg
	 * @return
	 
	public int insertNewProductImage(Connection conn, ArrayList<ProductImage> pImg) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNewProductImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			 // pImgList에 있는 각 ProductImage 객체에 대해 쿼리 실행
	        for (ProductImage p : pImg) {
	            pstmt.setInt(1, p.getRefPno());
	            pstmt.setString(2, p.getImgOriginalFile());
	            pstmt.setString(3, p.getImgSaveFile());
	            pstmt.setString(4, p.getImageUrl());

	            result += pstmt.executeUpdate();  // 성공한 레코드 수 누적
	        }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}  // insertNewProductImage 메소드 영역 끝
	*/
	
	public int updateProductImage(Connection conn, ProductImage p) {
	    int result = 0;
	    PreparedStatement pstmt = null;
	    String sql = prop.getProperty("updateProductImage");

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, p.getImgOriginalFile());
	        pstmt.setString(2, p.getImgSaveFile());
	        pstmt.setInt(3, p.getImageNo());
	        
	        result = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(pstmt);
	    }
	    return result;
	}


	public int insertNewProductImage(Connection conn, ProductImage p) {
	    int result = 0;
	    PreparedStatement pstmt = null;
	    String sql = prop.getProperty("insertNewProductImage");

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, p.getRefPno());
	        pstmt.setString(2, p.getImgOriginalFile());
	        pstmt.setString(3, p.getImgSaveFile());
	        pstmt.setString(4, p.getImageUrl());
	        
	        result = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(pstmt);
	    }
	    return result;
	}


	/**
	 * 상품 삭제용 쿼리문 실행 메소드
	 * @param conn
	 * @param productNo
	 * @return
	 */
	public int deleteProduct(Connection conn, int productNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, productNo);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}  // deleteProduct 메소드 영역 끝

	
	
	
	
	


}
