package com.kh.product.model.vo;

public class ProductImage {
	
	private int imageNo;				//	IMAGE_NO
	private int refPno;
	private String imgOriginalFile;		//	IMG_ORIGINAL_FILE
	private String imgSaveFile;			//	IMG_SAVE_FILE
	private String imageUrl;			//	IMAGE_URL
	private String imageDescription;	//	IMAGE_DESCRIPTION
	private String thumbnail;			//	THUMBNAIL (y:썸네일o/ n:썸네일x)
	private String deleteYN; 			//	DELETE_YN
	
	public ProductImage() {}

	public ProductImage(int imageNo, int refPno, String imgOriginalFile, String imgSaveFile, String imageUrl,
			String imageDescription, String thumbnail, String deleteYN) {
		super();
		this.imageNo = imageNo;
		this.refPno = refPno;
		this.imgOriginalFile = imgOriginalFile;
		this.imgSaveFile = imgSaveFile;
		this.imageUrl = imageUrl;
		this.imageDescription = imageDescription;
		this.thumbnail = thumbnail;
		this.deleteYN = deleteYN;
	}

	public int getImageNo() {
		return imageNo;
	}

	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
	}
	

	public int getRefPno() {
		return refPno;
	}

	public void setRefPno(int refPno) {
		this.refPno = refPno;
	}

	public String getImgOriginalFile() {
		return imgOriginalFile;
	}

	public void setImgOriginalFile(String imgOriginalFile) {
		this.imgOriginalFile = imgOriginalFile;
	}

	public String getImgSaveFile() {
		return imgSaveFile;
	}

	public void setImgSaveFile(String imgSaveFile) {
		this.imgSaveFile = imgSaveFile;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageDescription() {
		return imageDescription;
	}

	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}

	@Override
	public String toString() {
		return "ProductImage [imageNo=" + imageNo + ", refPno=" + refPno + ", imgOriginalFile=" + imgOriginalFile
				+ ", imgSaveFile=" + imgSaveFile + ", imageUrl=" + imageUrl + ", imageDescription=" + imageDescription
				+ ", thumbnail=" + thumbnail + ", deleteYN=" + deleteYN + "]";
	}


	
	

}
