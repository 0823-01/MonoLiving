<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.product.model.vo.ProductInfo" %>
<%
	ArrayList<ProductInfo> list = (ArrayList<ProductInfo>)request.getAttribute("list");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/productList.css">
<style>
	.category {
		color: darkgray;
		margin-left:150px;
	}
</style>
</head>
<body>
		<%@ include file="../common/header.jsp" %>

		
 		<section class="product-list">
		<h6 class="category">카테고리별 상품 ▶ 테이블·식탁·책상</h6>
	        <div id="content_3">
			  <div id="bar">
			    <div id="box">
			      <ul id="item-count">
			        <li>총 <%= list.size() %>개</li>
			      </ul>
			      <ul id="sorting-options">
			        <li>추천순</li>
			        <li>낮은가격순</li>
			        <li>높은가격순</li>
			      </ul>
			    </div>
			  </div>
			  
		
		<div id="productList">
			    <ul class="product-grid">
			    
					<% for(ProductInfo p : list) { %>
					    <li class="product-item" data-pno="<%= p.getProductNo() %>">
					        <img src="/admin/<%= p.getTitleImg() %>">
					        <p>
					            <%= p.getProductName().length() <= 10 ?
					                p.getProductName() :
					                p.getProductName().substring(0, 9) + "..." %> <br>
					            <%= String.format("%,d", p.getPrice()) %>원
					        </p>
					    </li>
					<% } %>
				</ul>
			    
			</div>
		
		
			  <div id="productList">
			    <ul class="product-grid">
			    
					<% for(ProductInfo p : list) { %>
					    <li class="product-item" data-pno="<%= p.getProductNo() %>">
					        <img src="/admin/<%= p.getTitleImg() %>">
					        <p>
					            <%= p.getProductName().length() <= 10 ?
					                p.getProductName() :
					                p.getProductName().substring(0, 9) + "..." %> <br>
					            <%= String.format("%,d", p.getPrice()) %>원
					        </p>
					    </li>
					<% } %>
				</ul>
			    
			</div>

	    </section>
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
	    </script>
</body>
</html>