<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//request로 부터 응답데이터인 에러문구 뽑기
	String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file = "header.jsp" %>
	<br>
	<br>
	
	<h1 align="center" style="color: red;">로그인실패</h1>

</body>
</html>