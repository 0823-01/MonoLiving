<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.kh.member.model.vo.Member" %>
<%
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 찾기</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#findPasswordButton').on('click', function(e) {
                e.preventDefault(); 
                
                var userName = $('#userName').val();
                var email = $('#email').val();
                var userId = $('#userId').val();

                $.ajax({
                    url: '<%= contextPath %>/pwReset.pw',
                    type: 'GET',
                    data: {
                        userName: userName,
                        email: email,
                        userId: userId
                    },
                    success: function(response) {
                        if (response.status === 'success') {
                            // 비밀번호 찾기 결과 화면으로 이동하여 결과 표시
                            window.location.href = '<%= contextPath %>/views/member/findPasswordResult.jsp?message=' + encodeURIComponent(response.message);
                        } else {
                            alert("입력한 정보에 일치하는 비밀번호가 없습니다");
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error('AJAX 요청 실패:', error);
                    }
                });
            });
        });
    </script>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f0f0;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 400px;
            margin: 100px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #444;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-size: 14px;
            color: #555;
        }
        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
            transition: border 0.3s;
        }
        input[type="text"]:focus, input[type="email"]:focus {
            border-color: black;
            outline: none;
        }
        button {
            width: 100%;
            padding: 15px;
            background-color: black;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: white;
            color: black;
            border: 1px solid black;
        }
   
      
    </style>
</head>
<body>
    <div class="container">
        <h1>비밀번호 찾기</h1>
        <form id="findPasswordForm"> 
            <label for="userName">이름:</label>
            <input type="text" id="userName" name="userName" required>
            
            <label for="email">이메일:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="userId">아이디:</label>
            <input type="text" id="userId" name="userId" required>
            
            <button id="findPasswordButton">비밀번호 찾기</button>
        </form>
    </div>
</body>
</html>
