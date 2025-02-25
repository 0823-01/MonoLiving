<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.product.model.vo.ProductInfo, com.kh.common.model.vo.PageInfo" %>    
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
<style>
    body {
        font-family: Arial, sans-serif;
    }

    .container {
        width: 100%;
        margin: 0 auto;
        padding: 20px;
    }

    h2 {
        margin-bottom: 10px;
    }
    
     .search-bar {
        margin: 0 10px; /* 버튼과 검색바 사이 간격 조절 */
        padding: 5px; /* 검색바 패딩 */
        width: 200px; /* 검색바 너비 */
    }

    .btn {
        padding: 5px 10px; /* 버튼 패딩 */
    }

    /* 상품 목록 상단 스타일 */
    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .header p {
        font-size: 18px;
        margin: 0;
    }

    .header button, .header select {
        padding: 5px 10px;
        margin-right: 10px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: center;
    }

    th {
        background-color: #f2f2f2;
        font-weight: bold;
    }

    td {
        white-space: nowrap;
    }
    
    .list-area>tbody>tr:hover {
		background-color: lightgray;
		color: white;
		cursor: pointer;

	}

    .product-code {
        color: red;
        font-weight: bold;
    }

    .action-buttons {
        display: flex;
        gap: 5px;
    }

    .action-buttons button {
        padding: 5px;
        cursor: pointer;
    }

    .category-btn, .delete-btn, .main-display-btn, .bulk-manage-btn {
        background-color: #f2f2f2;
        border: 1px solid #ddd;
    }

    /* 오른쪽 상단 등록 버튼 
    .register-btn {
        background-color: #B3B792;
        color: white;
        padding: 10px 20px;
        font-size: 14px;
        font-weight: 600;
        border: none;
        cursor: pointer;
    }

    .register-btn:hover {
        background-color: #005f73;
    }
   */ 
    .register-btn, .delete-btn {
    	color: black;
    }
    

</style>

</head>
<body>
	

	<%@ include file="../common/nav.jsp" %>


	<div id="layoutSidenav_content">
       <main>
       <br>
      	 	<h2 align="center">상품목록 페이지</h2>
		    <div class="container">
		        <!-- 상단 영역 -->
		        <div class="header">
		            <p>총 <strong><%= pi.getProductCount() %>개</strong></p>
		            <div>
		                <button>판매량</button>
		                
		                <input type="text" id="searchBar" placeholder="상품명을 검색하세요" class="search-bar">
   					    <!-- 검색 버튼 추가 -->
					    <button id="searchButton" class="btn btn-primary">검색</button>
		                
		                <button class="register-btn">
							<a href="<%= request.getContextPath() %>/enroll-product.pr">상품 등록</a>
		                </button>
		                
		            </div>
		            <select>
		                <option>등록일 역순</option>
		                <option>등록일 순</option>
		            </select>
		        </div>
		
		        <!-- 상품 목록 테이블 -->
		        <table class="list-area">
		            <thead>
		                <tr>
		                    <th>상품번호</th>
		                    <th>상품구분</th>
		                    <th>상품명</th>
		                    <th>판매가</th>
		                    <th>재고</th>
		                </tr>
		            </thead>
		          <tbody>
					    <% if (list.isEmpty()) { %>
					        <tr><td colspan="6">조회된 상품이 없습니다.</td></tr>
					    <%} else { %>
					        <% for (ProductInfo p : list) { %>
					            <tr data-pno="<%= p.getProductNo() %>"> 
					                
					                <td><%= p.getProductNo() %></td>
					                <td><%= p.getCategoryName() %></td>
					                <td><%= p.getProductName() %></td>
					                <td><%= p.getPrice() %></td>
					                <td><%= p.getProductQuantity() %></td> <!-- Correct method name -->
					            </tr>
					        <% } %>
					    <% } %>
					</tbody>
				</table>
				</div>
				<!-- 페이징바 -->
				 <div align="center" class="paging-area">
					
					<!-- 
						1번 페이지일 경우에는 이전 버튼을 아예 안보이게
						즉, 1번 페이지가 아닐 경우에만 이전 버튼이 보여지게끔!!
					 -->
					<% if(currentPage != 1){ %>
						<button onclick="location.href='<%= contextPath %>/productList.pr?currentPage=<%= currentPage-1 %>';">
							&lt;
						</button>
					<%} %>
					
					<% for(int p=startPage; p<=endPage; p++) { %>
					<!--
						현재 출력해야하는 p번 페이지가 currentPage와 일치하지 않는 경우
						onclick 속성이 제대로 동작하게끔 !
					 -->
					 	<% if(p != currentPage) {%>
							<button onclick="location.href = '<%= contextPath %>/productList.pr?currentPage=<%= p %>';">
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
						<button onclick="location.href='<%= contextPath %>/productList.pr?currentPage=<%=  currentPage+1%>';">
							&gt;
						</button>
					<%} %>
				 </div>

		    </div>
		 </main>
	</div>
			<br><br><br><br><br><br><br><br><br><br>

		<script>
			$(function() {
				
				$(".list-area>tbody>tr[data-pno]").click(function() {
					
					
					 let pno = $(this).data("pno");
				        console.log("상품 번호:", pno); // 상품 번호 확인
					
					location.href = "<%= contextPath %>/product-detail.pr?pno=" + pno;
					
					
				});
				
			});
		</script>
</body>
</html>