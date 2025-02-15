package com.kh.product.model.vo;

import java.sql.Date;

public class ProductInfo {
	
	private String titleImg; 			// 썸네일 이미질르 담을 변수를 필드로추가
	private int discountPrice;			// 할인판매가를 담을 변수를 필드로 추가함
	
	private int productNo; 				//	PRODUCT_NO
	private String productName; 		//	PRODUCT_NAME
	private int price; 					//	PRICE
	private int productQuantity;
	private String productDescription; //	PRODUCT_DESCRIPTION
	private String productSize; 		//	PRODUCT_SIZE
	private String material; 			//	MATERIAL
	private String color; 				//	COLOR
	private String assemblyYN;			//	ASSEMBLY_YN
	private int discount; 				//	DISCOUNT
	private String country;				 //	COUNTRY
	private Date proCreateAt; 			//	PRO_CREATE_AT
	private int categoryNo; 			//	CATEGORY_NO
	
	private String categoryName;
	
	public ProductInfo() {}

	public ProductInfo(int productNo, String productName, int price, int productQuantity, String productDescription, String productSize,
			String material, String color, String assemblyYN, int discount, String country, Date proCreateAt,
			int categoryNo) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.price = price;
		this.productQuantity = productQuantity;
		this.productDescription = productDescription;
		this.productSize = productSize;
		this.material = material;
		this.color = color;
		this.assemblyYN = assemblyYN;
		this.discount = discount;
		this.country = country;
		this.proCreateAt = proCreateAt;
		this.categoryNo = categoryNo;
	}
	
	
	
	
	// 상품 전체 리스트 내역에 보여질 매개변수 생성자
	public ProductInfo(int productNo, 
					   String categoryName,
			 		   String productName, 
			 		   int price, 
			 		   int productQuantity) {
		super();
		this.productNo = productNo;
		this.categoryName = categoryName;
		this.productName = productName;
		this.price = price;
		this.productQuantity = productQuantity;
		
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAssemblyYN() {
		return assemblyYN;
	}

	public void setAssemblyYN(String assemblyYN) {
		this.assemblyYN = assemblyYN;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getProCreateAt() {
		return proCreateAt;
	}

	public void setProCreateAt(Date proCreateAt) {
		this.proCreateAt = proCreateAt;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	

	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	@Override
	public String toString() {
		return "ProductInfo [productNo=" + productNo + ", productName=" + productName + ", price=" + price
				+ ", productQuantity=" + productQuantity + ", productDescription=" + productDescription
				+ ", productSize=" + productSize + ", material=" + material + ", color=" + color + ", assemblyYN="
				+ assemblyYN + ", discount=" + discount + ", country=" + country + ", proCreateAt=" + proCreateAt
				+ ", categoryNo=" + categoryNo + ", categoryName=" + categoryName + "]";
	}

	
	
	

}
