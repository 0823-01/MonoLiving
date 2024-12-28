<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 찾기 결과</title>
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 400px;
            margin: 100px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        p {
            font-size: 18px;
            margin: 10px 0;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background-color: black;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: white;
            color: black;
            border: 1px solid black;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>비밀번호 찾기 결과</h1>
    <div id="resultMessage">
        <%
            String message = request.getParameter("message"); 
            if (message != null && !message.isEmpty()) { 
        %>
            <p>비밀번호: <%= message %></p>
        <% 
            } else { 
        %>
            <p>비밀번호 찾기 결과가 없습니다.</p>
        <% 
            } 
        %>
    </div>
    <a href="<%= request.getContextPath() %>/main.jsp">로그인 페이지로 돌아가기</a>
</div>
</body>
</html>
