<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MONO Living</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link  rel="preconnect"
       href="https://fonts.gstatic.com"
       crossorigin="crossorigin">
<link  href="https://fonts.googleapis.com/css2?family=Zen+Antique+Soft&family=
				Zen+Kaku+Gothic+New:wght@300;400;500;700;900&display=swap"
       rel="stylesheet">
<title>MONO 회원 정보 수정</title>
<style>

	* {
        margin : 10px;
        padding : 0;
        box-sizing : border-box;
	}

   .mypage {
        width: 600px;
        padding: 20px;
        background-color: white;
        border-radius: 8px;
        margin: 100px auto; /* 화면 중앙에 배치 */
   }

   h3 {
		font-weight: bold;
		margin-bottom: 5px;
		padding: 20px 0;
		border-bottom: 1px solid #ddd;
   }
   p {
   		margin-bottom: 30px;
   		font-size: 10px;
   }

   form {
       display: flex;
       flex-direction: column;
   }

   label {
       margin: 10px 0px;
       font-weight: bold;
   }

   input, select, button {
       margin-bottom: 15px;
       padding: 10px;
       font-size: 16px;
       border: 1px solid #ddd;
       border-radius: 4px;
   }

   button {
        background-color: #000;
        color: #fff;
        padding: 10px 50px;
        border: none;
        border-radius: 60px;
        cursor: pointer;
        font-size: 14px;
   }
   button:hover {
       background-color: #444;
   }

   button[id="deleteBtn"] {
        background-color: rgba(115, 115, 115, 0.12);
        color : black;
        border-radius: 60px;
   }
   button[type="submit"] {
        border-radius: 60px;
   }
   button[id="deleteBtn"]:hover {
        background-color: rgba(115, 115, 115, 0.074);
        color : black;
   }
   .button-group[type="button"]:hover {
        background-color: rgba(115, 115, 115, 0.2);
        color : black;
   }
   .button-group {
       display: flex;
       justify-content: center;
       gap : 20px;
       border-radius: 60px;
       margin-top: 20px;
   }
   #year, #month, #day {
       width: 100%;
   }
   #email-form>* {
        width : 600px;
        box-sizing: border-box;
        display: flex;
   }
   #email-form>input {
        width :60%;
   }
   #email-form>select {
        width :40%;
   }
    </style>
</head>

<body>

	<%@ include file="../common/header.jsp" %>

    <%
        String userId = loginUser.getUserId();
        String userName = loginUser.getUserName();
        String gender = loginUser.getGender();
        String userBirth = (loginUser.getUserBirth() == null) ? "" : loginUser.getUserBirth();
        String email = (loginUser.getEmail() == null) ? "" : loginUser.getEmail();
        String phone = (loginUser.getPhone() == null) ? "" : loginUser.getPhone();
        String address = (loginUser.getAddress() == null) ? "" : loginUser.getAddress();
    %>

	<div class="mypage">
        <h3>회원 정보 수정</h3>
        <p style="text-align : right; ">필수입력사항 *</p>
        
        <form id="update-form"
              action="<%= contextPath %>/update.me"
              method="post">
              
            <label for="userName">이름 *</label>
            <input type="text" name="userName"
                   maxlength="12" required
                   value="<%= userName %>">

            <label for="gender">성별 *</label>
			<select id="gender" name="gender">
			    <option value="" disabled <%= (gender == null || gender.isEmpty()) ? "selected" : "" %>>선택</option>
			    <option value="M" <%= "M".equals(gender) ? "selected" : "" %>>남성</option>
			    <option value="F" <%= "F".equals(gender) ? "selected" : "" %>>여성</option>
			</select>

            <label for="userId">아이디 *</label>
            <input type="text" name="userId"
                   placeholder="아이디" readonly
                   value="<%= userId %>">

            <label for="userBirth">생년월일</label>
            <input type="text" name="userBirth"
                   readonly
                   value="<%= userBirth %>">


			<label for="email">이메일</label>
            <input type="email" name="email"
            	   placeholder="이메일 형식으로 입력해주세요." 
                   value="<%= email %>">

            <label for="phone">전화번호 *</label>
            <input type="tel" name="phone"
                   placeholder="(-)를 포함해 입력해주세요." 
                   value="<%= phone %>">

            <label for="address">주소</label>
            <input type="text" name="address" value="<%= address %>" readonly>

            <div class="button-group">
                <button type="submit">정보수정완료</button>
                <button type="button"
                		id="deleteBtn"
                		data-toggle="modal"
                		data-target="#deleteForm">회원 탈퇴</button>
            </div>
        </form>
    </div>
    
    <!-- 회원탈퇴용 모달창 -->
	<!-- The Modal -->
	<div class="modal" id="deleteForm">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">회원 탈퇴</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body" align="center">
	      	
	      	<b>
	      		탈퇴 후 복구가 불가능합니다. <br>
	      		정말로 탈퇴하시겠습니까? <br><br>
	      	</b>
	      	
	      	<!-- 
	      		회원 탈퇴 요청 시
	      		http://localhost:8888/jsp/delete.me
	      		로 요청 보내기
	      	-->
	      	<form action="<%= contextPath %>/delete.me" 
	      		  method="post">
	      		
	      		<table>
	      			<tr>
	      				<td>비밀번호</td>
	      				<td>
	      					<input type="password"
	      						   name="userPwd" required>
	      				</td>
	      			</tr>
	      		</table>
	      		
	      		<br>
	      		
	      		<button type="submit"
	      			    class="btn btn-sm btn-danger">
	      			탈퇴하기
	      		</button>
	      		
	      		<br>
	      		
	      	</form>
	      
	      </div>
	      
	    </div>
	  </div>
	</div>
    
    
    <%@ include file="../common/footer.jsp" %>

</body>
</html>