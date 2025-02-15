package com.kh.review.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.review.model.vo.Review;
import com.kh.review.model.vo.ReviewImage;

public class ReviewDao {
	
	private Properties prop = new Properties();
	
	public ReviewDao() {
		
		String fileName = ReviewDao.class.getResource("/sql/review/review-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 리뷰 등록 쿼리문 실행 메소드
	 * @param conn
	 * @param r
	 * @return
	 */
	public int insertReview(Connection conn, Review r) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReview");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getReviewComment());
			pstmt.setInt(2, r.getReviewStar());
			pstmt.setInt(3, Integer.parseInt(r.getReviewWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}

	/**
	 * 리뷰 첨부파일 등록용 쿼리문 실행용 메소드
	 * @param conn
	 * @param list
	 * @return
	 */
	public int insertReviewImage(Connection conn, ArrayList<ReviewImage> listImg) {
		
		int result = 1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReviewImage");
		
		
		try {
			
			for(ReviewImage ri : listImg) {
				
				pstmt = conn.prepareStatement(sql);
				
			    pstmt.setString(1, ri.getReviewOriginName());
			    pstmt.setString(2, ri.getReviewChangeName());
			    pstmt.setString(3, ri.getReviewImgPath());
				
				result *= pstmt.executeUpdate();
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 리뷰 목록 조회 쿼리문 실행용 메소드
	 * @param conn
	 * @return
	 */
	public ArrayList<Review> selectReviewList(Connection conn, int productNo) {

		ArrayList<Review> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReviewList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, productNo);
			
			rset = pstmt.executeQuery();
			
			int temp = 0; // 이전 반복 회차의 REVIEW_NO 을 담아둘 변수
			
			while(rset.next()) {

				ArrayList<String> thumbList = new ArrayList<>();
				String thumbnail = rset.getString("THUMBNAIL");
				
				thumbList.add(thumbnail);

				Review r = new Review();
				
				if(temp != rset.getInt("REVIEW_NO")) {
					
					r.setReviewNo(rset.getInt("REVIEW_NO"));
					r.setReviewComment(rset.getString("REVIEW_COMMENT"));
					r.setReviewStar(rset.getInt("REVIEW_STAR"));
					r.setReviewDate(rset.getDate("REVIEW_DATE"));
					r.setReviewWriter(rset.getString("USER_ID"));
					r.setThumbnail(thumbList);
					
					list.add(r);

				} else {
					
					list.get(list.size() - 1).getThumbnail().add(thumbnail);
				}
				
				temp = rset.getInt("REVIEW_NO");
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
	 * 리뷰 삭제 쿼리문 실행용 메소드
	 * @param conn
	 * @param reviewNo
	 * @return
	 */
	public int deleteReview(Connection conn, int reviewNo) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteReview");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reviewNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


}
