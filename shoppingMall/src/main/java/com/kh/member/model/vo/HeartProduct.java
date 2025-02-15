package com.kh.member.model.vo;

public class HeartProduct {
	
	private int userNo;
	private int productNo;
	
	
	// 찜 상품 목록조회할 때 필요한 필드 추가
	private String productName;
	private int price;
	private String titleImg;
		
	
	
	public HeartProduct() {}

	public HeartProduct(int userNo, int productNo) {
		super();
		this.userNo = userNo;
		this.productNo = productNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	@Override
	public String toString() {
		return "heartProduct [userNo=" + userNo + ", productNo=" + productNo + "]";
	}
	
	

}
