package com.kh.common.model.vo;

public class PageInfo {
	
	private int productCount;  // 현재 총 상품 갯수
	private int currentPage;  // 현재 요청한 페이지
	private int pageLimit;  // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
	private int productLimit;  // 한 페이지에 보여질 상품의 최대 갯수 (몇개 단위씩)
	private int maxPage;  // 가장 마지막 페이지가 몇번째 페이지인지 (총 페이지 수)
	private int startPage; // 페이지 하단에 보여질 페이징바의 시작수
	private int endPage; // 페이지 하단에 보여질 페이징바의 끝 수
	
	public PageInfo() {}

	public PageInfo(int productCount, 
					int currentPage, 
					int pageLimit, 
					int productLimit, 
					int maxPage, 
					int startPage,
					int endPage) {
		super();
		this.productCount = productCount;
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.productLimit = productLimit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getProductLimit() {
		return productLimit;
	}

	public void setProductLimit(int productLimit) {
		this.productLimit = productLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageInfo [productCount=" + productCount + ", currentPage=" + currentPage + ", pageLimit=" + pageLimit
				+ ", productLimit=" + productLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + "]";
	}
	
	
	
	

}
