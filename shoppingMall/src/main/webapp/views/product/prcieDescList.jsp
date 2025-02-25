<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.product.model.vo.ProductInfo
					,com.kh.common.model.vo.PageInfo" %>
<%
	ArrayList<ProductInfo> list = (ArrayList<ProductInfo>)request.getAttribute("list");

	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	// 페이징바 관련된 변수만 따로 셋팅하기
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/productList.css">
	    <!-- 간단한 동작을 정의해둔  js 파일 연동 -->
        <!-- jQuery 온라인 방식 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
       
        <!-- Popper JS -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
	<div class="outer">
		 <!-- 배너 영역 -->
	    <section class="main-banner">
	        <div class="banner-content">
	            <img src="resources/images/products/banner.jpg" alt="이달의 상품" class="main-image">
	            <div class="product-highlight">
	                <p class="new-label">NEW</p>
	                <h2 class="product-title">이 달의 상품</h2>
	                <div class="product-grid">
	                    <div class="main-product">
	                        <img src="resources/images/products/newProduct01.jpg" alt="주요 상품">
	                        <p>라운드 좌식 접이식테이블</p>
	                        <p class="price">14,900원</p>
	                    </div>
	                    <div class="side-products">
						    <div class="small-product">
						        <img src="resources/images/products/newProduct02.jpg" alt="상품1">
						        <div class="text-container">
						           <p>베가 폴라 투명 접이식의자</p>
						            <p class="price">22,900원</p>
						        </div>
						    </div>
						    <div class="small-product">
						        <img src="resources/images/products/newProduct03.jpg" alt="상품2">
						        <div class="text-container">
						            <p>특허받은 고정식 튼튼한행거 2단 M</p>
						            <p class="price">38,900원</p>
						        </div>
						    </div>
						</div>
	
	                </div>
	            </div>
	        </div>
	    </section>
	
	    <!-- 상품 목록 -->
	    <section class="product-list">
	
	        <div id="content_3">
			  <div id="bar">
			    <div id="box">
			      <ul id="item-count">
			        <li>총 <%= pi.getListCount() %>개</li>
			      </ul>
			      <ul id="sorting-options">
					 <li><a href="javascript:void(0)" id="sort-recommend">추천순</a></li>
					    <li><a href="<%= contextPath %>/priceListAsc.pr">낮은가격순</a></li>
					    <li><a href="<%= contextPath %>/priceListDesc.pr">높은가격순</a></li>
			      </ul>
			    </div>
			  </div>

			  <div id="productList">
			    <ul class="product-grid">
			    
					<% for(ProductInfo p : list) { %>
					    <li class="product-item" data-pno="<%= p.getProductNo() %>">
					        <img src="/admin/<%= p.getTitleImg() %>" >
					        <p>
					            <%= p.getProductName().length() <= 10 ?
					                p.getProductName() :
					                p.getProductName().substring(0, 9) + "..." %> <br>
					            <%= p.getPrice() %>
					        </p>
					    </li>
					<% } %>
				</ul>
			    
			</div>
	         <!-- 페이징바 -->
				 <div align="center" class="paging-area">
					
					<!-- 
						1번 페이지일 경우에는 이전 버튼을 아예 안보이게
						즉, 1번 페이지가 아닐 경우에만 이전 버튼이 보여지게끔!!
					 -->
					<% if(currentPage != 1){ %>
						<button onclick="location.href='<%= contextPath %>/product.pr?currentPage=<%= currentPage-1 %>';">
							&lt;
						</button>
					<%} %>
					
					<% for(int p=startPage; p<=endPage; p++) { %>
					<!--
						현재 출력해야하는 p번 페이지가 currentPage와 일치하지 않는 경우
						onclick 속성이 제대로 동작하게끔 !
					 -->
					 	<% if(p != currentPage) {%>
							<button onclick="location.href = '<%= contextPath %>/product.pr?currentPage=<%= p %>';">
								<%= p %>
							</button>
						 <%} else {%>
						 	<button disabled>
						 		<%= p %>
						 	</button>
						 <%} %>
					<%} %>
					
					
					<!-- 
						마지막 페이지가 아닐 경우에만 다음 버튼이 보여지게끔!
					 -->
					<% if(currentPage != maxPage) { %>
						<button onclick="location.href='<%= contextPath %>/product.pr?currentPage=<%=  currentPage+1%>';">
							&gt;
						</button>
					<%} %>
				 </div>
	    </section>
	    </div>
	    
		<script>
			$(function() {
			    $(".product-item").click(function() {
			        // data-pno 속성에서 상품 번호 가져오기
			        let pno = $(this).data("pno");
			        console.log("상품 번호:", pno); // 상품 번호 확인
			        
			        // 상세 페이지로 이동 
			        location.href = "<%= contextPath %>/detail-product.pr?pno=" + pno;
			    });
			});
		
			function priceListDesc() {
			$.ajax({	
	    			url : "<%= contextPath %>/priceDesc.pr",
	    			type : "get",
	    			data : {},
	    			success : function(response) {
	    				updateProductList(response); // 상품 목록 업데이트 함수 호출
	    			},
	    			error : function() {
	    				console.log("상품 리스트를 가져오는 데 실패했습니다.");
	    			}
	    		});
			};
			
			
		
		</script>
	
		

	
	
	
	<%@ include file="../common/footer.jsp" %>
	
</body>
</html>