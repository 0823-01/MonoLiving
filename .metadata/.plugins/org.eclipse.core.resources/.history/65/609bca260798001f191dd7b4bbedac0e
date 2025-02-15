<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
  * {
      padding: 0;
      box-sizing: border-box;
  }

  body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
  }

  .container {
      width: 1000px;
      margin: 50px auto;
      display: flex;
  }

  .sidebar {
      width: 20%;
      /* background-color: #f9f9f9; */
      padding: 20px;
  }

  .sidebar h2 {
      margin-bottom: 10px;
      font-size: 24px;
  }

  .sidebar ul {
      list-style-type: none;
      padding-left: 0;
  }

  .sidebar ul li {
      margin-bottom: 10px;
      text-align: left;
  }

  .sidebar ul li.section-title {
      font-size: 18px; /* 일반 메뉴보다 큰 폰트 크기 */
      color: #000; /* 검정색 */
      font-weight: bold;
      margin-top: 30px; /* 아래 여백 추가 */
  }

  .sidebar ul li a {
      text-decoration: none;
      color: #999; /* 기본 회색 */
      font-weight: normal;
      font-size: 14px; /* 일반 메뉴는 작은 폰트 */
      font-weight: bold;
  }

  .sidebar ul li a:hover {
      color: #000; /* hover 시 검정 */
  }

  .sidebar ul li.selected a {
      font-size: 14px;
      font-weight: bold; /* 선택된 메뉴는 bold */
      color: #000; /* 선택된 메뉴는 검정 */
  }

  .content {
      width: 80%;
      padding: 20px;
  }

  .content h2 {
      border-bottom: 2px solid #000;
      padding-bottom: 10px;
      margin-bottom: 20px;
      font-size: 22px;
  }

  .info-group {
      margin-bottom: 30px;
  }

  .info-group h3 {
      font-size: 18px;
      font-weight: bold;
      padding: 20px 0 10px 0;
  }

  .info-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 10px 0;
      border-bottom: 1px solid #eaeaea; /* 밑줄 스타일 */
  }

  .info-item label {
      font-size: 14px;
      color: #888; /* 회색 텍스트 */
      font-weight: normal;
  }

  .info-item input {
      font-size: 16px;
      color: #000;
      border : 0;
      display: flex;
  }

  .info-item button {
      background-color: #fff;
      border: 1px solid #ddd;
      padding: 5px 15px;
      border-radius: 15px; /* 둥근 버튼 */
      font-size: 14px;
      cursor: pointer;
      color: #555; /* 버튼 텍스트 색상 */
  }

  .info-item button:hover {
      border-color: #aaa; /* hover 시 테두리 색상 변경 */
  }


  button {
      background-color: #fff;
      border: 1px solid #ddd;
      padding: 5px 10px;
      cursor: pointer;
  }

  input[type="checkbox"] {
      width: 20px;
      height: 20px;
      align-items: center;
  }

  /* 생년월일 상세 스타일 */
  .form-group {
      display: flex;
      justify-content: flex-start;
      align-items: center;
      margin-bottom: 20px;
    }

    .form-group label {
      flex: 0 0 120px;
      font-size: 1em;
      font-weight: bold;
    }

    .form-group select {
      flex: 1;
      padding: 10px;
      font-size: 1em;
      border: 1px solid #ccc;
      border-radius: 5px;
      margin-right: 10px;
    }

    .form-group select:last-child {
      margin-right: 0;
    }
    
    /* 버튼 상세 스타일 */
    button {
        background-color: #000;
        color: #fff;
        padding: 10px 50px;
        border: none;
        border-radius: 60px;
        cursor: pointer;
        font-size: 14px;
        margin-top: 50px;
   }
   button:hover {
       background-color: #444;
   }

   button[type="button"] {
        background-color: rgba(115, 115, 115, 0.12);
        color : black;
   }

   button[type="button"]:hover {
        background-color: rgba(115, 115, 115, 0.2);
        color : black;
   }

   .button-group {
       display: flex;
       justify-content: center;
       gap : 20px;
   }
    

</style>
</head>
<body>
	
	<%@ include file="../common/header.jsp" %>

	<%
		// 이미 로그인된 loginUser 변수로 출력
		/*
		String userId = loginUser.getUserId();
		String userPwd = loginUser.getUserPwd();
		String userName = loginUser.getUserName();
		String gender = loginUser.getGender();
		String userBirth = loginUser.getUserBirth();
		String email = loginUser.getEmail();
		String phone = loginUser.getPhone();
		String address = loginUser.getAddress();
		*/
	%>
	<div class="container">
		<div class="sidebar">
	        <h2>마이 페이지</h2>
	        
	        <ul>
	            <li class="section-title">쇼핑 정보</li>
	            <ul>
	                <li><a href="#">구매 내역</a></li>
	                <li><a href="#">환불 내역</a></li>
	            </ul>
	            <li class="section-title">내 정보</li>
	            <ul>
	                <li class="selected"><a href="#">로그인 정보</a></li>
	                <li><a href="#">프로필 관리</a></li>
	                <li><a href="#">주소록</a></li>
	                <li><a href="#">결제 정보</a></li>
	            </ul>
        	</ul>
     	 </div>

	<form class="content"
		  action="<%= contextPath %>/update.me"
		  method="post">
	    <h2>로그인 정보</h2>

	    <div class="info-group">
	        <h3>내 계정</h3>
	        <div class="info-item">
	            <label>아이디 *</label>
	            <input type="text"
	            	   name="userId"
	            	   maxlength="12"
	            	   value="userId"
	            	   readonly></input>
	        </div>
	        <div class="info-item">
	            <label>비밀번호 *</label>
	            <input type="password"
                       name="userPwd"
                       minlength="8"
                       maxlength="15"
                       value="*********"
                       required></input>
	        </div>
            <div class="info-item">
	            <label>비밀번호 확인 *</label>
	            <input type="password"
                       minlength="8"
                       maxlength="15"
                       value="*********"
                       required></input>
	        </div>
	    </div>
	
	    <div class="info-group">
	        <h3>개인 정보</h3>
	        <div class="info-item">
	            <label>이름 *</label>
	            <input type="text"
                       name="userName"
                       maxlength="6"
                       value="윤예원"
                       required></input>
	        </div>
            <div class="info-item">
	            <label>성별</label>
                <input type="text"
                name="gender"
                value="여">
                <!-- 
                    <select>
                        <option value="선택안함"></option>
                        <option value="M">남</option>
                        <option value="F">여</option>
                    </select>
                
                -->
	        </div>
            <div class="info-item">
                <label for="birth">생년월일</label>
                <input type="text"
                       name="birth"
                       value="970501">
            </div>

            <div class="info-item">
                <label>이메일</label>
                <input type="email"
                       name="email"
                       value="xxxx@xxxx...com"></input>
            </div>

	        <div class="info-item">
	            <label>전화번호</label>
	            <input type="text"
                       name="phone"
                       value="010-0000-0000"></input>
	        </div>
            
	    </div>
	
	    <div class="info-group">
	        <h3>광고성 정보 수신</h3>
	        <div class="info-item">
	            <label>개인 정보 수집 및 이용 동의</label>
	            <button>내용보기</button>
	        </div>
	        <div class="info-item">
	            <label>문자 메시지</label>
	            <input type="checkbox">
	        </div>
	        <div class="info-item">
	            <label>이메일</label>
	            <input type="checkbox">
	        </div>
	    </div>
	    
	    <div class="button-group">
                <button type="submit">정보수정완료</button>
                <button type="button">회원 탈퇴</button>
        </div>
	    
	</form>
</div>

<%@ include file="../common/footer.jsp" %>

</body>
</html>