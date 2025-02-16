package com.kh.cart.model.vo;

public class Cart {

	// 필드부
	private int cartNo;
	private int productQuantity;
	private int userNo;
	private int productNo;
	private String productName;
    private int originalPrice;         // 원래 가격
    private int discountedPrice;       // 할인된 가격
    private String titleImg;
	
	// 생성자부
	public Cart() {}

	public Cart(int cartNo, int productQuantity, int userNo, int productNo, String productName, int originalPrice,
			int discountedPrice, String titleImg) {
		super();
		this.cartNo = cartNo;
		this.productQuantity = productQuantity;
		this.userNo = userNo;
		this.productNo = productNo;
		this.productName = productName;
		this.originalPrice = originalPrice;
		this.discountedPrice = discountedPrice;
		this.titleImg = titleImg;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
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

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", productQuantity=" + productQuantity + ", userNo=" + userNo + ", productNo="
				+ productNo + ", productName=" + productName + ", originalPrice=" + originalPrice + ", discountedPrice="
				+ discountedPrice + ", titleImg=" + titleImg + "]";
	}
	
}
