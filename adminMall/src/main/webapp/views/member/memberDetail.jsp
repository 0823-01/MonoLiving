<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member" %>    
<%
	Member m = (Member)request.getAttribute("m");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body {
	    font-family: Arial, sans-serif;
	    background-color: #f9f9f9;
	    margin: 0;
	    padding: 20px;
	}
	
	.container {
	    max-width: 600px;
	    margin: auto;
	    padding: 20px;
	    background: #fff;
	    border-radius: 8px;
	    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	}
	
	h1 {
	    text-align: center;
	    color: #333;
	}
	
	.member-info {
	    margin-top: 20px;
	}
	
	.info-row {
	    display: flex;
	    justify-content: space-between;
	    padding: 10px 0;
	    border-bottom: 1px solid #e0e0e0;
	}
	
	.info-row:last-child {
	    border-bottom: none; /* 마지막 행의 경계선 제거 */
	}
	
	label {
	    font-weight: bold;
	    color: #555;
	}
	
	span {
	    color: #333;
	}
	
	.back-button {
	    display: block;
	    width: 100%;
	    padding: 10px;
	    margin-top: 20px;
	    background-color: #007bff;
	    color: white;
	    border: none;
	    border-radius: 5px;
	    cursor: pointer;
	    font-size: 16px;
	}
	
	.back-button:hover {
	    background-color: #0056b3; /* 호버 시 배경색 변화 */
	}

</style>
</head>
<body>
	<%@ include file="../common/nav.jsp" %>

	<div id="layoutSidenav_content">
       <main>
       <br>
		    <div class="container">
		        <h1>회원 정보 상세보기</h1>
		        <div class="member-info">
		            <div class="info-row">
		                <label>회원 번호:</label>
		                <span><%= m.getUserNo() %></span>
		            </div>
		            <div class="info-row">
		                <label>회원 아이디:</label>
		                <span><%= m.getUserId() %></span>
		            </div>
		            <div class="info-row">
		                <label>회원 이름:</label>
		                <span><%= m.getUserName() %></span>
		            </div>
		            <div class="info-row">
		                <label>성별:</label>
		                <span><%= m.getGender() %></span>
		            </div>
		            <div class="info-row">
		                <label>생년월일:</label>
		                <span><%= m.getUserBirth() %></span>
		            </div>
		            <div class="info-row">
		                <label>이메일:</label>
		                <span><%= m.getEmail() %></span>
		            </div>
		            <div class="info-row">
		                <label>전화번호:</label>
		                <span><%= m.getPhone() %></span>
		            </div>
		            <div class="info-row">
		                <label>주소:</label>
		                <span><%= m.getAddress() %></span>
		            </div>
		            <div class="info-row">
		                <label>회원가입일:</label>
		                <span><%= m.getEnrollDate() %></span>
		            </div>
		        </div>
		        <button class="back-button" onclick="window.history.back();">뒤로 가기</button>
		    </div>
		 </main>
	</div>

</body>
</html>