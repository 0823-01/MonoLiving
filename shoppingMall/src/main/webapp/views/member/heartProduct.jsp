<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList
					, com.kh.member.model.vo.HeartProduct" %>
<%
	ArrayList<HeartProduct> list = (ArrayList<HeartProduct>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* 기본 스타일 */
/* styles.css */
body {
    font-family: Arial, sans-serif;
    padding: 20px;
}

.wishlist {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.filters {
    display: flex;
    gap: 10px;
    margin-bottom: 20px;
    margin-top: 100px;
}

.filters button,
.filters select {
    padding: 8px 12px;
    border: 1px solid #ccc;
    background-color: #f9f9f9;
    cursor: pointer;
}

.filters input[type="checkbox"] {
    margin-right: 5px;
}

.product-list {
    display: grid;
    grid-template-columns: repeat(5, 1fr); /* 한 줄에 4개씩 배치 */
    gap: 30px; /* 이미지 간 간격 */
    justify-items: center; /* 가운데 정렬 */
}

.product-item {
    width: 200px; /* 상품 카드 크기 조정 */
    padding: 15px;
    border: 1px solid #eee;
    text-align: center;
    border-radius: 5px;
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
}

.product-item img {
    width: 100%;
    height: auto;
    margin-bottom: 10px;
}

.product-item p {
    font-size: 14px;
    margin: 5px 0;
}

.product-item span {
    color: #ff5e57;
    font-weight: bold;
    font-size: 14px;
}

.favorite {
    color: #ff5e57;
    font-size: 16px;
    margin-top: 5px;
}



</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	
    <div class="wishlist">
        <div class="filters">
            <button>전체</button>
            <button>침대</button>
            <button>패브릭</button>
            <button>가구</button>
            <button>수납/정리</button>
            <button>텍스타일</button>
            <button>선반/랙</button>
            
            <select>
                <option>담은 순</option>
                <option>낮은 가격순</option>
                <option>높은 가격순</option>
            </select>
            
        </div>
    
       
        
        
        
        <div class="product-list">
		    <% if (list != null && !list.isEmpty()) { 
		            for (HeartProduct h : list) { %>
		        <div class="product-item">
		            <!-- 상품 이미지 -->
		            <img src="/admin/<%= h.getTitleImg() %>">
		            
		            <!-- 상품 이름 -->
		            <p>
		            	<%= h.getProductName().length() <= 10 ?
			                h.getProductName() :
			                h.getProductName().substring(0, 9) + "..." %>

					</p>
		            
		            <!-- 상품 가격 (예: 10,000원 형식으로 출력) -->
		            <span><%= String.format("%,d", h.getPrice()) %>원</span>
		        </div>
		    <% }
		        } else { 
		    %>
		        <!-- 찜한 상품이 없을 때 -->
		        <p>찜한 상품이 없습니다.</p>
		    <% 
		        } 
		    %>
		</div>
        
        
        
    </div>
    <%@ include file="../common/footer.jsp" %>
</body>
</html>