package com.kh.review.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.review.model.dao.ReviewDao;
import com.kh.review.model.vo.Review;
import com.kh.review.model.vo.ReviewImage;

public class ReviewService {
	
	/**
	 * 리뷰 등록용 서비스 메소드
	 * @param r
	 * @param list
	 * @return
	 */
	public int insertReview(Review r, ArrayList<ReviewImage> listImg) {
		
		Connection conn = getConnection();
		
		int result1 = new ReviewDao().insertReview(conn, r);
		int result2 = new ReviewDao().insertReviewImage(conn, listImg);
		
		if(result1 > 0 && result2 > 0) {
			
			commit(conn);
		} else {
			
			rollback(conn);
		}
		
		return result1 * result2;
	}
	
	/**
	 * 리뷰 목록 조회용 서비스 메소드
	 * @return
	 */
	public ArrayList<Review> selectReviewList(int productNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Review> list = new ReviewDao().selectReviewList(conn, productNo);
		
		close(conn);
		
		return list;
	}

	/**
	 * 리뷰 삭제용 서비스 메소드
	 * @param reviewNo
	 * @return
	 */
	public int deleteReview(int reviewNo) {

		Connection conn = getConnection();

		int result = new ReviewDao().deleteReview(conn, reviewNo);
		
		if(result > 0) {
			
			commit(conn);
		} else {
			
			rollback(conn);
		}
		
		return result;
	}


}
