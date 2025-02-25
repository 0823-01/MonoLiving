package com.kh.member.model.vo;

import java.sql.Date;

public class Member {
    private int userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String gender;
    private String userBirth;
    private String email;
    private String phone;
    private String address;
    private Date enrollDate;
    private String status;

    // 기본 생성자
    public Member() {
    }

    // 모든 필드를 포함하는 생성자
    public Member(int userNo, String userId, String userPwd, String userName, String gender,
                  String userBirth, String email, String phone, String address, Date enrollDate, String status) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.gender = gender;
        this.userBirth = userBirth;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.enrollDate = enrollDate;
        this.status = status;
    }

    // 새로운 생성자 (상태는 기본값으로 "활성")
    public Member(String userId, String userPwd, String userName, String gender,
                  String userBirth, String email, String phone, String address) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.gender = gender;
        this.userBirth = userBirth;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = "활성"; // 기본값으로 설정
    }

    // 회원 정보 수정용 생성자
    public Member(String userId, String userName, String gender, String userBirth, String email, String phone,
    		String address) {
    	super();
    	this.userId = userId;
    	this.userName = userName;
    	this.gender = gender;
    	this.userBirth = userBirth;
    	this.email = email;
    	this.phone = phone;
    	this.address = address;
    }
    

    // 게터와 세터 메소드
    public int getUserNo() {
        return userNo;
    }


	public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", gender=" + gender + ", userBirth=" + userBirth + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", enrollDate=" + enrollDate + ", status=" + status + "]";
	}

    
}
