<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    // URL 파라미터에서 도로명 주소와 우편번호를 가져옴
    String address = request.getParameter("address");
    String postcode = request.getParameter("postcode");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer {
		width : 1000px;
		margin : auto;
		margin-top : 50px;
	}

	#enroll-form table {
		margin-left :280px;
        text-align: left; /* 테이블 내부는 왼쪽 정렬 */
		
	}


	
	#enroll-form input {
	margin-top: 10px;
    width: 400px; /* 원하는 너비 값으로 조정 */
    height: 40px;  /* 원하는 높이 값으로 조정 */
    font-size: 20px; /* 텍스트 크기 조정 */
}
	
	.backimg {
    background-image: url('resources/images/main/nature.jpg'); /* 여기에 이미지 경로를 입력하세요 */
    background-size: cover; /* 배경 이미지가 전체 화면을 덮도록 조정 */
    background-position: center; /* 배경 이미지 중앙 정렬 */
    padding: 20px; /* 본문 영역의 여백 */
    position: relative; /* 자식 요소를 상대적으로 배치할 수 있도록 설정 */
            opacity: 0.8; /* 본문 영역의 투명도 설정 (0~1 사이의 값) */
}
	
	

.outer {
    width: 1000px;
    margin: auto;
    margin-top: 50px;
    background-color: rgba(255, 255, 255, 0.9); /* 흰색 배경에 90% 불투명도 설정 */
    padding: 20px; /* 여백을 추가하여 콘텐츠와 경계 사이의 공간 확보 */
    border-radius: 10px; /* 모서리 둥글게 */
}

</style>

<script src="https://openapi.kakao.com/v2/maps/sdk.js?appkey=YOUR_APP_KEY&libraries=services"></script>

</head>
<body>

	<%@ include file="../common/header.jsp" %>
	<%-- ../ : 현재 이 폴더로부터 한겹 빠져나가겠다. --%>
	
	
	<div class="backimg">
	<div class="outer">
		
		<br>
		<br>

		<!-- 
			회원 정보를 다 입력 후 회원가입 버튼 클릭 시
			http://localhost:8888/jsp/insert.me
			로 요청 보낼 것 
		-->
	      <form id="enroll-form" action="<%= contextPath %>/insert.me" method="post">

			<!--
				* 회원가입 시 입력받아야 하는 정보들
				아이디, 비밀번호, 이름, 전화번호, 이메일, 주소, 취미  
			-->

			<table>	
				<tr>
					<td>ID * </td>
				</tr>
					<td>
						<input type="text" name="userId" maxlength="12" required>
					</td>
					<td>
					<img src="resources/images/main/checkId.png" alt="중복확인" style="width: 30px; height: 20px; margin-left: -40px; cursor: pointer;" onclick="idCheck(); ">
						
					</td>	
				
				<tr>
					<td>PASSWORD *</td>
				</tr>
									                    <tr>
									                    <td><span id="pwStrength"></span>
					
									                    </td></tr>
					<td>
                        <input type="password" name="userPwd"  id="pw1"  oninput="pwCheck()" maxlength="15" required>
					</td>
					<td></td>
			
				<tr>
					<td>CONFIRM PASSWORD *</td>
				</tr>
				
					<td>
                   <input type="password" name="userPwdCheck"  id="pw2"  oninput="pwCheck()" maxlength="15" required>
                   
					</td>
						<tr>
						
								<td><span id="pwConfirm">비밀번호를 입력하세요</span></td>
						</tr>
				
					
						
					<td></td>
				
				<tr>
					<td>NAME *</td>
				</tr>
					<td>
						<input type="text" name="userName" maxlength="6" required>
					</td>
					<td></td>
					<tr>
						<td>GENDER</td>
					</tr>
					<tr>
						<td colspan="2">
							<label style="vertical-align: middle; margin-right: 50px;">
								<input type="radio" name="gender" value="M" style="width:10px; height: 10px;"> M
							</label>
							<label style="vertical-align: middle;">
								<input type="radio" name="gender" value="F" style="width: 10px; height: 10px; margin-left: -20px;"> W
							</label>
						</td>
					</tr>
					
				<tr>
					<td>BIRTH</td>
				</tr>
					<td>
						<input type="text" name="userBirth">
					</td>
					<td></td>
				
				<tr>
					<td>E-MAIL *</td>
				</tr>
					<td>
						<input type="email" name="email">
					</td>
					<td></td>
			
				<tr>
					<td>PHONE *</td>
					</tr>
					
					<td>
						<input type="text" name="phone" placeholder="- 제외하고 입력">
					</td>
					
				
				<tr>
					<td>ADDRESS</td>
				</tr>
					<td>
				
                
			<input type="text" id="address" name="address" value="<%= address != null ? address : "" %>">	
   			 <button type="button" id="search-address" onclick="openAddressPopup();">주소 검색</button>
					</td>

             
					</td>
				
			</table>
			
			<div align="center">
				
				<button type="submit" id="bottom-btn" style="width: 400px; height: 40px; background-color: black; color: white; margin-top: 40px;"  >
					계정생성
				</button>
				<br>
				<br>
				<button type="reset" id="bottom-btn" style="width: 400px; height: 40px; background-color: white;">
					취소
				</button>
	

			<br><br>

		</form>
		<div style="font-size: 200px;">MONO</div>
	</div>
	
	<script>
		function idCheck() {
			
			// 아이디를 입력받는 input 요소 객체를 변수에 담기
			let $userId = $("#enroll-form input[name=userId]");
			// > input[name=userId] 로만 선택하면
			//   상단에 include 했었던 menubar.jsp 의 로그인용 
			//   아이디 입력창 또한 같이 선택되게 됨!!
			// > 내가 원하는 요소만 정확하게 고르려면
			//   확실하게 어디에 속해있는 요소인지 잘 구분하여 선택해야함!!
			
			// 아이디 중복 체크 요청 시
			// http://localhost:8888/jsp/idCheck.me?checkId=xxx
			// 로 비동기식 요청
			$.ajax({
				url : "<%= contextPath %>/idCheck.me",
				type : "get",
				data : {checkId : $userId.val()},
				success : function(result) {
					
					// console.log(result);
					
					if(result == "NNNNN") {
						// 사용 불가한 아이디일 경우
						
						alert("이미 사용중이거나 탈퇴한 회원의 아이디 입니다.");
						
						// 아이디 재입력 유도
						$userId.focus();
						
					} else {
						// 사용 가능한 아이디일 경우
						
						if(confirm("사용 가능한 아이디 입니다. 사용하시겠습니까?")) {
							// 사용하겠다라고 의사를 밝힌 경우 (확인 버튼 클릭 시)
							
							// 아이디값 확정 (다시는 수정 못하게)
							$userId.attr("readonly", true);
							
							// 회원가입버튼 활성화
							$("#enroll-form button[type=submit]").removeAttr("disabled");
							
						} else {
							// 사용하지 않겠다라고 의사를 밝힌 경우 (취소 버튼 클릭 시)
							
							// 아이디 재입력 유도
							$userId.focus();
						}
					}
				}, 
				error : function() {
					
					console.log("아이디 중복체크용 ajax 통신 실패!");
				}
			});
		}
		
		function pwCheck() {
		    const pw1 = $('#pw1').val();
		    const pw2 = $('#pw2').val();
		    const pwConfirm = $('#pwConfirm');
		    const pwStrength = $('#pwStrength');

		    // 비밀번호 일치 확인
		    if (pw1 === pw2) {
		        pwConfirm.text('비밀번호 일치').css('color', 'green');
		    } else {
		        pwConfirm.text('비밀번호 불일치').css('color', 'red');
		    }

		    // 비밀번호 유효성 검사
		    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
		    if (pw1.length > 0) {
		        if (!passwordPattern.test(pw1)) {
		            pwStrength.text('최소 8자, 대문자, 소문자, 숫자 및 특수문자를 포함.').css('color', 'red');
		        } else {
		            pwStrength.text('비밀번호 강함').css('color', 'green');
		        }
		    } else {
		        // 비밀번호가 비어있을 때에도 강도 메시지를 보여줌
		        pwStrength.text('최소 8자, 대문자, 소문자, 숫자 및 특수문자를 포함.').css('color', 'red');
		    }
		}
		function openAddressPopup() {
		    window.open('views/api/kakaomap_api.html', '주소 검색', 'width=900,height=800');
		}

	</script>
	</div>

</body>
</html>





