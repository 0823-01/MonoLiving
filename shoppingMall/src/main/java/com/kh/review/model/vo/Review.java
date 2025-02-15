package com.kh.review.model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class Review {
	
	// 필드부
	private int reviewNo;			//	REVIEW_NO	NUMBER
	private int productNo;			//	PRODUCT_NO	NUMBER
	private String reviewComment;	//	REVIEW_COMMENT	VARCHAR2(500 BYTE)
	private int reviewStar;			//	REVIEW_STAR	NUMBER(1,0)
	private Date reviewDate;		//	REVIEW_DATE	DATE
	private String reviewDeleteYN;	//	REVIEW_DELETE_YN	CHAR(1 BYTE)
	private String reviewWriter;		//	REVIEW_WRITER	NUMBER
	// private String thumbnail;			//	REVIEW_IMG_PATH || REVIEW_CHANGE_NAME AS "THUNBNAIL"
	private ArrayList<String> thumbnail;
	// private ArrayList<String> listImg;
	
	// 생성자부
	public Review() {}

	public Review(int reviewNo, int productNo, String reviewComment, int reviewStar, Date reviewDate,
			String reviewDeleteYN, String reviewWriter, ArrayList<String> thumbnail) {
		super();
		this.reviewNo = reviewNo;
		this.productNo = productNo;
		this.reviewComment = reviewComment;
		this.reviewStar = reviewStar;
		this.reviewDate = reviewDate;
		this.reviewDeleteYN = reviewDeleteYN;
		this.reviewWriter = reviewWriter;
		this.thumbnail = thumbnail;
	}
	
	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public int getReviewStar() {
		return reviewStar;
	}

	public void setReviewStar(int reviewStar) {
		this.reviewStar = reviewStar;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewDeleteYN() {
		return reviewDeleteYN;
	}

	public void setReviewDeleteYN(String reviewDeleteYN) {
		this.reviewDeleteYN = reviewDeleteYN;
	}

	public String getReviewWriter() {
		return reviewWriter;
	}

	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}
	
	public ArrayList<String> getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(ArrayList<String> thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", productNo=" + productNo + ", reviewComment=" + reviewComment
				+ ", reviewStar=" + reviewStar + ", reviewDate=" + reviewDate + ", reviewDeleteYN=" + reviewDeleteYN
				+ ", reviewWriter=" + reviewWriter + ", thumbnail=" + thumbnail + "]";
	}
	
}