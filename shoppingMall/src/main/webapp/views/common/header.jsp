<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member" %>
    


<%
			// session으로부터 응답데이터인 로그인한 회원의 정보 (loginUser)
			Member loginUser = (Member)session.getAttribute("loginUser");

			// request.getContextPath()를 통해 context path 값 알아내기
			String contextPath = request.getContextPath();

			// session으로부터 alertMsg 값을 뽑아내기
			String alertMsg = (String)session.getAttribute("alertMsg");

			// 쿠키 불러오기
			Cookie[] cookies = request.getCookies();
			String saveId = ""; // 아이디값을 저장할 변수

			if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("saveId")) {
			saveId = cookies[i].getValue();
			break;
			   }
			}
		}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="resources/css/main.css">
    
      <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

        <!-- 간단한 동작을 정의해둔  js 파일 연동 -->
        <!-- jQuery 온라인 방식 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
       
        <!-- Popper JS -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    
</head>
<body>
		
		<script>
		$(function() {
			// 1회성 alert 문구 띄우기
			let msg = "<%= alertMsg %>";
			if(msg != "null") {
			alert(msg);
			<% session.removeAttribute("alertMsg"); %>
			}
			});
		
		function handleWishlistClick() {
			<% if(loginUser == null) { %>
			openLoginPanel();
			<% } else { %>
			document.getElementById("wishlistForm").submit();
			<% } %>
			}
			
		
		function handleCartClick() {
        	<% if (loginUser == null) { %>
	            openLoginPanel();
	        <% } else { %>
	            location.href = '<%= contextPath %>/list.ct';
	        <% } %>
        }
	</script>
		
				<button class="chatbot-button" onclick="openChat()">
						상담하기
						</button>
						<div class="menu-panel">
						<span class="close-menu-btn">×</span>
							<h2>Mono'Shop</h2>
						<ul>
							<li><a href="#" onclick="location.href = '<%= contextPath %>/product.pr'; return false;">전체 상품 목록</a></li>
							<li><a href="#" data-target="submenu">카테고리별 상품 목록</a></li>
							<li><a href="#" onclick="location.href = '<%= contextPath %>/list.no'; return false;">공지사항</a></li>
						</ul>
					<div class="submenu-panel" id="submenu">
						<div>
							<li><a href="#" onclick="location.href = '<%= contextPath %>/bedList.pr'; return false;">침대</a></li>
							<li><a href="#" onclick="location.href = '<%= contextPath %>/tableList.pr'; return false;">테이블·식탁·책상</a></li>
							<li><a href="#" onclick="location.href =
							'<%= contextPath %>/cabinetList.pr'; return false;">서랍·수납장</a></li>
							<li><a href="#" onclick="location.href = '<%= contextPath %>/showcaseList.pr'; return false;">진열장·책장</a></li>
							<li><a href="#" onclick="location.href = '<%= contextPath %>/chairList.pr'; return false;">의자</a></li>
							<li><a href="#" onclick="location.href = '<%= contextPath %>/closetList.pr'; return false;">행거·옷장</a></li>
						</div>
					</div>
				</div>

			<form id="search-form" action="<%= contextPath %>/search-list.pr" method="get">
					<div class="search-panel">
					<input type="text" class="search-input" name="keyword" placeholder="'당신만의 공간을 위한' 가구를 검색하세요.">
					<input type="submit" class="search-button" value="검색">
					<span class="close-search-btn" style="">×</span>
					</div>
			</form>
		<div class="overlay"></div>
		
	    <!-- 로그인 관련 영역 -->
    	<div class="login-area">
		<% if(loginUser == null) { %>
			
          <div class="login-panel">

            <span class="close-btn" style="margin-left: 600px;">&times;</span>
            <h2 style="margin-right: 450px; margin-bottom: 50px;">로그인</h2>
            <p class="welcome-message" style="margin-right: 390px;">안녕하세요 고객님</p>
            <div class="or-divider"></div>

    		<form id="login-form" 
        	  action="<%= contextPath %>/login.me" method="post">
                <p style="margin-left: 350px;">*는 필수입력 사항입니다.</p>
                <p style="margin-right: 530px; margin-top: 40px;">ID*</p>
                
              <input type="text" name="userId" required>
			<a href="<%= contextPath %>/IdReset.id"
			class="password-reset">아이디 잊어버리셨습니까?</a>

                <p style="margin-right: 460px; margin-top: 25px;">PASSWORD*</p>
                <input type="password" name="userPwd" required>

				<a href="<%= contextPath %>/pwReset.pw" class="password-reset">비밀번호를 잊어버리셨습니까?</a>
                <button type="submit" class="login-button">로그인</button>
                 <button type="button" onclick="enrollPage();" class="login-button">
                        	회원가입
                        </button>
            </form>
         </div>
	
	
	
        <script>
        	function enrollPage() {
        		
        		// 회원가입페이지로 이동
        		// location.href = "<%= contextPath %>/views/member/memberEnrollForm.jsp";
        		// > 웹 애플리케이션 프로젝트의 디렉토리 구조가
        		//   url 주소 상에 대놓고 노출되면 보안에 취약해짐!!
        		
        		location.href = "<%= contextPath %>/enrollForm.me";
        		// > 단순히 정적으로 페이지를 보여지게끔 하는 요청이더라도
        		//   반드시 서블릿을 거쳐가게끔 해줄 것!!
        	}
        </script>
        
	<% } else { %>

        <!-- case2. 로그인 후에 보여질 화면 -->
        

            <div class="login-panel">

                <span class="close-btn" style="margin-left: 600px;">&times;</span>
                
                  <div align="left">
                  <h2>마이페이지</h2>
                <h2 style="margin-right: 310px; margin-bottom: 50px;"><%= loginUser.getUserName() %>님</b> 환영합니다.</h2>
                
                <div class="mapage-panel">

              
                    
					<div class="button-group">
						<button   class="login-button" onclick=" location.href='<%= contextPath %>/orders.me'">주문 내역</button>
						<button   class="login-button"  onclick=" location.href='<%= contextPath %>/updateForm.me'">회원 정보 수정</button>
					</div>
					<button   class="login-button" onclick=" location.href='<%= contextPath %>/logout.me'">로그아웃</button>

              

            </div>
			</div>
        </div>
    
	<% } %>

    <!-- 메뉴바 영역 -->
    <div class="wrap">
            <!-- Header -->
            <div id="header">
                <div id="header1">
                    <img src="resources/images/main/menu.png" alt="Menu">
                </div>
                  <div id="header2">
                	<a  href="<%= contextPath %>">MONO</a>
                </div>
                <div id="header3">
                    <img src="resources/images/main/search.png" alt="Search" onclick="openSearchPanel()">
                    <img src="resources/images/main/heart.png" alt="Wishlist" onclick="handleWishlistClick()">
					<form id="wishlistForm" action="<%= contextPath %>/heartProduct.hp" method="post" style="display:none;">
					<!-- hidden input으로 로그인한 회원의 userNo 전달 -->
					<input type="hidden" name="userNo" value="<%= loginUser != null ? loginUser.getUserNo() : "" %>">
					</form>
                    <img src="resources/images/main/icon.png" alt="Profile" onclick="openLoginPanel()">
					<img src="resources/images/main/bag.png" onclick="handleCartClick();">               
					 </div>
            </div>
             <script src="resources/js/login.js"></script>

    </div>
    		
    		
    		
       
</body>
</html>