package com.kh.review.model.vo;

public class ReviewImage {

	// 필드부
	private int reviewNo;				//	REVIEW_IMG_NO	NUMBER
	private int reviewRefRno;			//	REVIEW_REF_RNO	NUMBER
	private String reviewOriginName;	//	REVIEW_ORIGIN_NAME	VARCHAR2(255 BYTE)
	private String reviewChangeName;	//	REVIEW_CHANGE_NAME	VARCHAR2(255 BYTE)
	private String reviewImgPath;		//	REVIEW_IMG_PATH	VARCHAR2(255 BYTE)
	private String reviewImgDeleteYN;	//	REVIEW_IMG_DELETE_YN	CHAR(1 BYTE)

	// 생성자부
	public ReviewImage() {}


	public ReviewImage(int reviewNo, int reviewRefRno, String reviewOriginName, String reviewChangeName,
			String reviewImgPath, String reviewImgDeleteYN) {
		super();
		this.reviewNo = reviewNo;
		this.reviewRefRno = reviewRefRno;
		this.reviewOriginName = reviewOriginName;
		this.reviewChangeName = reviewChangeName;
		this.reviewImgPath = reviewImgPath;
		this.reviewImgDeleteYN = reviewImgDeleteYN;
	}


	public int getReviewNo() {
		return reviewNo;
	}


	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}


	public int getReviewRefRno() {
		return reviewRefRno;
	}


	public void setReviewRefRno(int reviewRefRno) {
		this.reviewRefRno = reviewRefRno;
	}


	public String getReviewOriginName() {
		return reviewOriginName;
	}


	public void setReviewOriginName(String reviewOriginName) {
		this.reviewOriginName = reviewOriginName;
	}


	public String getReviewChangeName() {
		return reviewChangeName;
	}


	public void setReviewChangeName(String reviewChangeName) {
		this.reviewChangeName = reviewChangeName;
	}


	public String getReviewImgPath() {
		return reviewImgPath;
	}


	public void setReviewImgPath(String reviewImgPath) {
		this.reviewImgPath = reviewImgPath;
	}


	public String getReviewImgDeleteYN() {
		return reviewImgDeleteYN;
	}


	public void setReviewImgDeleteYN(String reviewImgDeleteYN) {
		this.reviewImgDeleteYN = reviewImgDeleteYN;
	}


	@Override
	public String toString() {
		return "ReviewImage [reviewNo=" + reviewNo + ", reviewRefRno=" + reviewRefRno + ", reviewOriginName="
				+ reviewOriginName + ", reviewChangeName=" + reviewChangeName + ", reviewImgPath=" + reviewImgPath
				+ ", reviewImgDeleteYN=" + reviewImgDeleteYN + "]";
	}

}
