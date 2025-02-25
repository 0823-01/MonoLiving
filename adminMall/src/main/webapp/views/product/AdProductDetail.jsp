<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.product.model.vo.*, java.util.ArrayList" %>
<%
	ProductInfo p = (ProductInfo)request.getAttribute("p");
	ArrayList<ProductImage> pImg = (ArrayList<ProductImage>)request.getAttribute("pImg");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 20px;

    
}

.container {
    max-width: 600px;
    margin: auto;
    background: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    margin-top: 80px;
 
}

h1 {
    text-align: center;
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 80px;
}

th, td {
    padding: 10px;
    text-align: left;
    border-bottom: 1px solid #ccc;
}

th {
    background-color: #f2f2f2;
}

.detail-images {
    margin: 20px 0;
}

.detail-images h4 {
    font-size: 24px;
    margin-bottom: 10px;
}

.image-gallery {
    display: flex; /* 플렉스 박스를 사용 */
    flex-direction: column; /* 세로 방향으로 나열 */
    gap: 15px; /* 이미지 간 간격 */
}

.image-gallery img {
    width: 100%; /* 전체 폭 사용 */
    height: auto; /* 비율 유지 */
    object-fit: cover; /* 잘림 방식 설정 */
    border-radius: 8px; /* 모서리 둥글게 */
    border: 1px solid #ccc; /* 이미지 칸 경계선 */
    padding: 5px; /* 내부 여백 */
    transition: transform 0.3s; /* 마우스 오버시 변환 효과 */
}

.image-gallery img:hover {
    transform: scale(1.05); /* 마우스 오버 시 확대 효과 */
}



</style>
</head>
<body>

	<%@ include file="../common/nav.jsp" %>
	
	<div id="layoutSidenav_content">
       <main>
	
			<div class="container">
			<!-- <br><br><br> -->
				<h1>상품 상세 정보</h1>
				
				
				<!-- 썸네일 이미지 -->
				<% if (pImg != null && !pImg.isEmpty()) { %>
				    <!-- 썸네일 이미지 -->
				    <div class="thumbnail-image">
				    	<h4>대표이미지</h4>
				        <img src="<%= contextPath %>/<%= pImg.get(0).getImageUrl() + pImg.get(0).getImgSaveFile() %>" 
				             style="width: 250px; height: 250px; object-fit: cover;">
				    </div>
				<% } else { %>
				    <div class="thumbnail-placeholder" style="width: 150px; height: 150px; border: 1px solid #ccc; background-color: #f0f0f0;"></div>
				<% } %>

		
				
			
				
				<table>
					<tr>
						<th>상품번호</th>
						<td><%= p.getProductNo() %></td>
					</tr>
					<tr>
						<th>카테고리번호</th>
						<td><%= p.getCategoryName() %></td>
					</tr>
					<tr>
						<th>상품명</th>
						<td><%= p.getProductName() %></td>
					</tr>
					<tr>
						<th>상품가격</th>
						<td><%= p.getPrice() %></td>
					</tr>
					<tr>
						<th>수량</th>
						<td><%= p.getProductQuantity() %></td>
					</tr>
					<tr>
						<th>상품설명</th>
						<td><%= p.getProductDescription() %></td>
					</tr>
					<tr>
						<th>상품사이즈</th>
						<td><%= p.getProductSize() %></td>
					</tr>
					<tr>
						<th>소재</th>
						<td><%= p.getMaterial() %></td>
					</tr>
					<tr>
						<th>색상</th>
						<td><%= p.getColor() %></td>
					</tr>
					<tr>
						<th>조립여부</th>
						<td><%= p.getAssemblyYN() %></td>
					</tr>
					<tr>
						<th>할인율</th>
						<td><%= p.getDiscount() %>%</td>
					</tr>
					<tr>
						<th>제조국</th>
						<td><%= p.getCountry() %></td>
					</tr>
					<tr>
						<th>상품등록일</th>
						<td><%= p.getProCreateAt() %></td>
					</tr>

				</table>

				<!-- 상세 이미지들 -->
				<div class="detail-images">
					<h4>상세 이미지</h4>
					<div class="image-gallery">
					<%for(int i=1; i<pImg.size(); i++) { %>
						<img src="<%= contextPath %>/<%= pImg.get(i).getImageUrl() + pImg.get(i).getImgSaveFile() %>"
								style="width: 300px; height: 300px; object-fit: cover;">
					<%} %>
					</div>
				</div>
			</div>
		<!-- 버튼 섹션 -->
		<div class="button-section">
			<a href="<%= request.getContextPath() %>/productList.pr" class="btn btn-sm btn-warning">목록으로</a>
			<a href="<%= request.getContextPath() %>/updateForm.pr?pno=<%= p.getProductNo() %>" 
				class="btn btn-sm btn-warning">수정하기</a>
			<a href="<%= request.getContextPath() %>/delete-product.pr?pno=<%= p.getProductNo() %>" 
				class="btn btn-sm btn-warning">삭제하기</a>
		</div>
		</main>
	</div>
	<br><br><br><br><br><br>
	

	
</body>

</html>