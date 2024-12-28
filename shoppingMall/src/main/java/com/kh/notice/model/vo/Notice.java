package com.kh.notice.model.vo;

public class Notice {

	// 필드부
	private int noticeNo;			//	NOTICE_NO	NUMBER
	private String noticeTitle;		//	NOTICE_TITLE	VARCHAR2(500 BYTE)
	private String noticeContent;	//	NOTICE_CONTENT	VARCHAR2(4000 BYTE)
	private int noticeViewCount;	//	NOTICE_VIEW_COUNT	NUMBER
	private String noticeCreateAt;	//	NOTICE_CREATE_AT	DATE
	private String noticeFileName;	//	NOTICE_FILE_NAME	VARCHAR2(255 BYTE)
	private String noticeUpdateFile;//	NOTICE_UPDATE_FILE	VARCHAR2(255 BYTE)
	private int noticeFileSize; 	// NOTICE_FILE_SIZE	NUMBER
	private String noticeFilePath;	//	NOTICE_FILE_PATH	VARCHAR2(255 BYTE)
	private String noticeDeleteYn;	//	NOTICE_DELETE_YN	CHAR(1 BYTE)
	
	public String getFormattedFileSize() {
        return String.format("%.2f KB", this.noticeFileSize / 1024.0);
    }
	
	public Notice() {};
	
	// 생성자부
	public Notice(int noticeNo, String noticeTitle, String noticeContent, int noticeViewCount, String noticeCreateAt,
			String noticeFileName, String noticeUpdateFile, int noticeFileSize, String noticeFilePath,
			String noticeDeleteYn) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeViewCount = noticeViewCount;
		this.noticeCreateAt = noticeCreateAt;
		this.noticeFileName = noticeFileName;
		this.noticeUpdateFile = noticeUpdateFile;
		this.noticeFileSize = noticeFileSize;
		this.noticeFilePath = noticeFilePath;
		this.noticeDeleteYn = noticeDeleteYn;
	}

	// 공지사항 목록 조회용 생성자
	public Notice(int noticeNo, String noticeTitle, String noticeCreateAt) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeCreateAt = noticeCreateAt;
	}
	
	// 공지사항 상세조회용 생성자
	public Notice(int noticeNo, String noticeTitle, String noticeContent, int noticeViewCount, String noticeCreateAt,
			String noticeFileName, String noticeUpdateFile, int noticeFileSize, String noticeFilePath) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeViewCount = noticeViewCount;
		this.noticeCreateAt = noticeCreateAt;
		this.noticeFileName = noticeFileName;
		this.noticeUpdateFile = noticeUpdateFile;
		this.noticeFileSize = noticeFileSize;
		this.noticeFilePath = noticeFilePath;
	}
	
	// 메소드부
	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public int getNoticeFileSize() {
		return noticeFileSize;
	}

	public void setNoticeFileSize(int noticeFileSize) {
		this.noticeFileSize = noticeFileSize;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}


	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}


	public String getNoticeContent() {
		return noticeContent;
	}


	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}


	public int getNoticeViewCount() {
		return noticeViewCount;
	}


	public void setNoticeViewCount(int noticeViewCount) {
		this.noticeViewCount = noticeViewCount;
	}


	public String getNoticeCreateAt() {
		return noticeCreateAt;
	}


	public void setNoticeCreateAt(String noticeCreateAt) {
		this.noticeCreateAt = noticeCreateAt;
	}


	public String getNoticeFileName() {
		return noticeFileName;
	}


	public void setNoticeFileName(String noticeFileName) {
		this.noticeFileName = noticeFileName;
	}


	public String getNoticeUpdateFile() {
		return noticeUpdateFile;
	}


	public void setNoticeUpdateFile(String noticeUpdateFile) {
		this.noticeUpdateFile = noticeUpdateFile;
	}


	public String getNoticeFilePath() {
		return noticeFilePath;
	}


	public void setNoticeFilePath(String noticeFilePath) {
		this.noticeFilePath = noticeFilePath;
	}


	public String getNoticeDeleteYn() {
		return noticeDeleteYn;
	}


	public void setNoticeDeleteYn(String noticeDeleteYn) {
		this.noticeDeleteYn = noticeDeleteYn;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeViewCount=" + noticeViewCount + ", noticeCreateAt=" + noticeCreateAt + ", noticeFileName="
				+ noticeFileName + ", noticeUpdateFile=" + noticeUpdateFile + ", noticeFileSize=" + noticeFileSize
				+ ", noticeFilePath=" + noticeFilePath + ", noticeDeleteYn=" + noticeDeleteYn + "]";
	}

}
