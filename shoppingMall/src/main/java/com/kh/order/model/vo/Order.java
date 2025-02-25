package com.kh.order.model.vo;

import java.util.Date;

public class Order {
    private int orderNo;            // 주문 ID
    private int totalPrice;         // 주문 상품 전체 금액
    private String recipient;        // 수령인
    private String address;          // 주소
    private String phone;            // 전화번호
    private int status;              // 배송 상태 (int로 변경)
    private String resRequirement;   // 요청 사항
    private String payCode;          // 결제 코드
    private Date orderEnroll;        // 주문 날짜
    private Date refundDate;         // 환불 날짜
    private int userId;              // 주문한 회원 번호
    private String customerName;      // 고객 이름 추가
    private String productName;       // 상품 이름 추가
    private int price;                // 가격 추가

    // 기본 생성자
    public Order() {
    }

    // 전체 필드를 사용하는 생성자
    public Order(int orderNo, int totalPrice, String recipient, String address, String phone, int status,
                 String resRequirement, String payCode, Date orderEnroll, Date refundDate, int userId,
                 String customerName, String productName, int price) {
        this.orderNo = orderNo;
        this.totalPrice = totalPrice;
        this.recipient = recipient;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.resRequirement = resRequirement;
        this.payCode = payCode;
        this.orderEnroll = orderEnroll;
        this.refundDate = refundDate;
        this.userId = userId;
        this.customerName = customerName; // 고객 이름 초기화
        this.productName = productName;     // 상품 이름 초기화
        this.price = price;                  // 가격 초기화
    }

    // Getter 및 Setter 메서드
    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResRequirement() {
        return resRequirement;
    }

    public void setResRequirement(String resRequirement) {
        this.resRequirement = resRequirement;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Date getOrderEnroll() {
        return orderEnroll;
    }

    public void setOrderEnroll(Date orderEnroll) {
        this.orderEnroll = orderEnroll;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    @Override
    public String toString() {
        return "Order [orderNo=" + orderNo + ", totalPrice=" + totalPrice + ", recipient=" + recipient +
                ", address=" + address + ", phone=" + phone + ", status=" + status + ", resRequirement=" +
                resRequirement + ", payCode=" + payCode + ", orderEnroll=" + orderEnroll + ", refundDate=" +
                refundDate + ", userId=" + userId + ", customerName=" + customerName + ", productName=" +
                productName + ", price=" + price + "]";
    }
}
