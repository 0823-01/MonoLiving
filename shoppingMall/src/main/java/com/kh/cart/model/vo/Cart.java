package com.kh.cart.model.vo;

public class Cart {

	// 필드부
	private int cartNo;
    private int productQuantity;
    private int totalPrice;
    private int userNo;
    private int productNo;
    private String productName;
    private int price;
    private int discount;
    private int discountPrice;
    private int totalDiscountPrice;
    private String titleImg;
	
	// 생성자부
	public Cart() {}

	public Cart(int cartNo, int productQuantity, int totalPrice, int userNo, int productNo, String productName,
			int price, int discount, int discountPrice, int totalDiscountPrice, String titleImg) {
		super();
		this.cartNo = cartNo;
		this.productQuantity = productQuantity;
		this.totalPrice = totalPrice;
		this.userNo = userNo;
		this.productNo = productNo;
		this.productName = productName;
		this.price = price;
		this.discount = discount;
		this.discountPrice = discountPrice;
		this.totalDiscountPrice = totalDiscountPrice;
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

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
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

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getTotalDiscountPrice() {
		return totalDiscountPrice;
	}

	public void setTotalDiscountPrice(int totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", productQuantity=" + productQuantity + ", totalPrice=" + totalPrice
				+ ", userNo=" + userNo + ", productNo=" + productNo + ", productName=" + productName + ", price="
				+ price + ", discount=" + discount + ", discountPrice=" + discountPrice + ", totalDiscountPrice="
				+ totalDiscountPrice + ", titleImg=" + titleImg + "]";
	}

}
