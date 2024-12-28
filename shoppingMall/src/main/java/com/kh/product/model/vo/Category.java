package com.kh.product.model.vo;

public class Category {
	
	private int category;		 //	CATEGORY_NO
	private String categoryName; //	CATEGORY_NAME
	
	public Category() {}

	public Category(int category, 
				    String categoryName) {
		super();
		this.category = category;
		this.categoryName = categoryName;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [category=" + category + ", categoryName=" + categoryName + "]";
	}
	
	

}
