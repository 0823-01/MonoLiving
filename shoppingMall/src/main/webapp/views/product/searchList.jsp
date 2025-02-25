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

	/* 기본 스타일 */
	body {
	    font-family: 'Zen Kaku Gothic New', sans-serif;
	    margin: 0;
	    padding: 0;
	}
	
	header {
	    background-color: white;
	    border-bottom: 2px solid #c3c3c3;
	    padding: 10px 20px;
	}
	
		/* 상품 목록 */
	.product-list {
	    background-color: #ffffff;
	    padding: 40px 20px;
	    margin-top: 100px;
	}
	
	.product-list h2 {
	    font-size: 24px;
	    margin-bottom: 20px;
	}
	
	.products-grid {
	    display: flex;
	    flex-wrap: wrap;
	    gap: 20px;
	    justify-content: space-between;
	}
	
	.product-card {
	    width: 23%;
	    background-color: #f9f9f9;
	    padding: 10px;
	    text-align: center;
	    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	    position: relative;
	}
	
	.product-card img {
	    width: 100%;
	    height: 200px;
	    object-fit: cover;
	}
	
	.heart {
	    position: absolute;
	    top: 10px;
	    right: 10px;
	    font-size: 20px;
	    color: red;
	    cursor: pointer;
	}
	
	   #box {
        width: 1200px;
        height: 100px;
        background-color: #E5D2B8;           /* 상자 내부 배경색 */
        /*border: 2px solid blue;               내부 상자 테두리 */
        box-sizing: border-box;
        display: flex;                       /* Flexbox 사용 */
        justify-content: space-between;      /* 양 끝 정렬 */
        align-items: center;                 /* 수직 중앙 정렬 */
        padding: 10px;                       /* 여백 */
    }
    

    #bar {
        display: flex;                       /* Flexbox 사용 */
        justify-content: center;             /* 수평 중앙 정렬 */
        align-items: center;                 /* 수직 중앙 정렬 */
        padding: 10px;                       /* 여백 */
    }

    #item-count, #sorting-options {
        list-style: none;                    /* 기본 목록 스타일 제거 */
        margin: 0;                           /* 기본 여백 제거 */
        padding: 0;                          /* 기본 패딩 제거 */
    }

    #item-count {
        text-align: left;                   /* 왼쪽 정렬 */
        font-size: 20px;
        font-weight: 700;
        margin-left: 30px;
    }

    #sorting-options {
        display: flex;                      /* Flexbox 사용 */
        gap: 10px;                         /* 항목 사이 여백 추가 */
        text-align: right;  
        font-size: 15px;             
        margin-top: 50px;
        color: gray;
    }
  
    #sorting-options li {
        position: relative; /* 상대 위치 설정 */
    }

    #sorting-options li::after {
        content: '|';           /* '|' 기호 추가 */
        margin: 0 10px;        /* 좌우 여백 설정 */
    }

    #sorting-options li:last-child::after {
        content: '';           /* 마지막 항목 뒤에는 기호를 제거 */
    }

    #productList {
        width: 100%;
        display: flex;
        justify-content: center;
        text-align: center; /* 자식 요소의 텍스트를 가운데 정렬 */
   		flex-wrap: wrap; /* 여러 줄로 이미지 배치 */
   		gap: 20px; /* 이미지들 간의 간격 */
    }

    ul.product-grid {
        display: grid;
        grid-template-columns: repeat(3, 1fr); /* 한 줄에 3개의 이미지 */
        grid-gap: 0px; /* 이미지 사이 간격 */
        list-style: none; /* 리스트 스타일 제거 */
        padding: 0; /* 기본 패딩 제거 */
        margin: 0; /* 기본 마진 제거 */
        width: 80%; /* 그리드 전체의 너비 */
        margin: 0 auto; /* 가운데 정렬 */
    }

    .product-item {
        display: flex;
        flex-direction: column; /* 이미지를 위, 텍스트를 아래 배치 */
        margin-top : 50px;
        ustify-content: center; 
        align-items: center;
    	margin-bottom: 100px; /* 각 아이템의 아래쪽 간격 */
    }

    .product-item img {
        width: 200x;  /* 정사각형 크기 */
        height: 200px; /* 정사각형 크기 */
        object-fit: cover; /* 이미지 비율 유지하며 정사각형으로 자름 */
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); /* 약간의 그림자 */
    }

    .product {
        display: flex;
        flex-direction: column; /* 이미지와 텍스트를 수직으로 정렬 */
        align-items: flex-start; /* 텍스트를 왼쪽으로 정렬 */
    }

    .product-item {
        display: flex;
        flex-direction: column; /* 이미지와 텍스트를 수직으로 정렬 */
        align-items: center; /* 이미지와 텍스트를 가운데 정렬 */
    }

    .product-item p {
        margin-top: 10px; /* 이미지와 텍스트 사이 간격 */
        font-size: 14px; /* 텍스트 크기 */
        color: #333; /* 텍스트 색상 */
        text-align: left; /* 텍스트 왼쪽 정렬 */
        width: 150px; /* 이미지와 동일한 너비로 설정 */
    }
    
   	.product-item:hover {
		cursor: pointer;
		opacity: 0.75;;
	}
	
	.search-result {
		margin-left: 150px;
		color: darkgray;
	}
		
</style>
</head>
<body>
		<%@ include file="../common/header.jsp" %>
	
		
		
 		<section class="product-list">
 		<h3 class="search-result">"<%= request.getAttribute("keyword") %>"에 대한 검색결과</h3>
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