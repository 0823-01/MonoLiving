<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.product.model.vo.*, java.util.ArrayList" %>    
<%
	ProductInfo p = (ProductInfo)request.getAttribute("p");
	ArrayList<ProductImage> list = (ArrayList<ProductImage>)request.getAttribute("list");
	
	Boolean isWishlisted = (Boolean)request.getAttribute("isWishlisted");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.product-page {
		display: flex;
		justify-content: space-between;
		padding: 20px;
		font-family: 'Arial', sans-serif;
		margin-top: 100px;
		margin-left: 120px;
	
	}
	
	.center-hr {
		margin-left:auto;
		margin-right: auto;
		border: 1px solid #bbb;
		width: 1400px;
	}

	/* Left side: images */
	.product-images {
    flex: 1;
    margin-right: 20px; /* 오른쪽에 여백을 남겨서 레이아웃이 깨지지 않도록 유지 */
}



.thumbnail-container {
    display: flex;
    justify-content: center; /* 이미지를 화면 중앙에 위치시키기 */
    margin-top: 10px;
    margin-left: 150px; /* 기존 왼쪽 여백 제거 */
}

.thumbnail {
    width: 100%; /* 이미지 크기를 키움 */
    height: auto;
    margin: 0 auto; /* 여백을 자동으로 균등 배치하여 중앙에 맞춤 */
}


	/* Right side: product details */
	/* Table styling */
	.price-table {
	    width: 100%;
	    border-spacing: 0 10px; /* Space between rows */

	}

	.price-table th {
	    font-size: 16px;
	    text-align: left;
	}
	
	.price-table td {
	    font-size: 20px;
	    font-weight: normal;
	}
	
	/* Original price (strikethrough and gray color) */
	.original-price {
	    text-decoration: line-through;
	    color: gray;
	    font-size: 18px;
	}
	
	/* Discount rate (red, bold) */
	.discount-rate {
	    color: red;
	    font-weight: bold;
	    font-size: 24px;
	}
	
	/* Discounted price (larger, bold) */
	.discounted-price {
	    font-weight: bold;
	    font-size: 24px;
	    color: black;
	}
	
	/* Button styles */
	.buttons {
	    margin-top: 20px;
	}
	
	.store-button, .wishlist-button, .pay-button {
	    padding: 10px 20px;
	    font-size: 16px;
	    margin-right: 10px;
	    cursor: pointer;
	    align-items: center; /* 세로 중앙 정렬 */
	    justify-content: center; /* 수평 중앙 정렬 */
	    width: 150px; /* 버튼 너비 (필요에 따라 조정) */
	    height: 50px; /* 버튼 높이 (필요에 따라 조정) */
	    position: relative; /* 자식 요소의 절대 위치를 위한 설정 */
	 
	}
	
	.wishlist-button {
		width: 50px;
		height: 50px;
		padding: 0; /* 패딩 제거 */ 
	    align-items: center; /* 세로 중앙 정렬 */
	    justify-content: center; /* 수평 중앙 정렬 */
	}
	
	.wishlist-button img {
	    width: 40px; /* 이미지의 최대 너비를 버튼 너비에 맞춤 */
	    max-height: 100%; /* 이미지의 최대 높이를 버튼 높이에 맞춤 */
	    margin-right: 30px;
	    object-fit: cover; /* 비율 유지하며 버튼을 가득 채우기 */
	   	 margin: 0; /* 여백 제거 */
	}

	/* Additional styling for spacing and layout */
	.product-page {
	    display: flex;
	    justify-content: space-between;
	    align-items: flex-start;
	}
	
	.thumbnail-container {
	    margin-right: 30px;
	}
	
	.product-details {
	    flex-grow: 1;
	    margin-left: 150px;
	}
	
	
	.detail-images {
	    display: block; /* 이미지를 수직으로 배치 */
	    text-align: center; /* 중앙 정렬 */
	    margin-top: 20px;
	}
	
	.detail-thumbnail {
	    width: 400px; /* 이미지 크기를 더 크게 설정 */
	    height: auto;
	    margin-bottom: 20px; /* 이미지 사이의 수직 간격을 설정 */
	}
	
	.heart-button {
	    background: none; /* 배경 없애기 */
	    border: none; /* 테두리 없애기 */
	    font-size: 20px; /* 아이콘 크기 조정 */
	    color: red; /* 하트 색상 */
	    cursor: pointer; /* 커서를 포인터로 변경 */
	    margin-right: 10px; /* 버튼 사이에 여백 추가 */
	}

	

</style>
</head>
<body>

	<%@ include file="../common/header.jsp" %>
	
	


	<div class="product-page">
		<!-- Left side: Main image and small thumbnails -->
		<div class="product-images">
			<div class="thumbnail-container">
				<img src="/admin/<%= list.get(0).getImageUrl() + list.get(0).getImgSaveFile() %>"
				 class="thumbnail">
				 
			</div>
		</div>
	
		<!-- Right side: Product details -->
		 <div class="product-details">
	        <h1><%= p.getProductName() %></h2><br><br>
	        <table class="price-table">
	            <tr>   
	                <td class="original-price"><%= String.format("%,d", p.getPrice()) %>원</td>
	            </tr>
	            <tr>
	                <td class="discount-rate"><%= p.getDiscount() %>%</td>
	            </tr>
	            <tr>
	                <td class="discounted-price">
	                <%= String.format("%,d", p.getDiscountPrice()) %>원
	                </td>
	            </tr>
	            
	  
	        </table>
				<br><br><br><br><br><br>
		        <div class="buttons">
		        	 <button class="wishlist-button" >
		            	  <img src="resources/images/icon/찜해제.png"
		            	  	   id="wishlist-icon"
		            	  	   onclick="wishlistToggle();">   
		            </button>
					<button class="store-button" onclick="addToCart(<%= p.getProductNo() %>, 1)">장바구니 담기</button>
		            <button class="wishlist-button" 
		            onclick="location.href='views/product/orderDetail.jsp?productNo=<%= p.getProductNo() %>'">구매하기</button>
		            
		        </div>
	    	</div>
	</div>
		
	</div>
		
		
		<script>
		function addToCart(productNo, quantity) {
		    var userNo = <%= (loginUser != null) ? loginUser.getUserNo() : "null" %>; // 세션에서 사용자 ID 가져오기
		    
		    $.ajax({
		        type: "POST",
		        url: "<%= contextPath %>/add.ct", // 장바구니 추가 서블릿 URL
		        data: {
		            userNo: userNo,
		            productNo: productNo,
		            quantity: quantity
		        },
		        success: function(response) {
		            if (response.status === "success") {
		                alert("장바구니에 추가되었습니다."); // 성공 메시지
		            } else {
		                alert("장바구니 추가 실패."); // 실패 메시지
		            }
		        },
		        error: function() {
		            console.log("AJAX 요청 실패");
		        }
		    });
		}

</script>
		
		
	<br><br><br><br>
	<hr class="center-hr">

	

			
			<div class="detail-images">
			    <% for (int i = 0; i < list.size(); i++) { %>
			        <img src="/admin/<%= list.get(i).getImageUrl() + list.get(i).getImgSaveFile() %>" class="detail-thumbnail">
			   		<br>
			    <% } %>
			</div>
			<br><br><br>
			<hr class="center-hr">

	            <div class="product-reviews">
                <h2>상품 리뷰</h2>
                <div class="review-list">
                    <div class="review-item">
                        <p><strong>사용자1</strong> - 5★</p>
                        <p>이 제품은 정말 좋습니다!</p>
                        <p class="review-date">2024-11-03</p>
                    </div>
                    <div class="review-item">
                        <p><strong>사용자2</strong> - 4★</p>
                        <p>가성비가 좋네요.</p>
                        <p class="review-date">2024-11-02</p>
                    </div>
                    <div class="review-item">
                        <p><strong>사용자3</strong> - 3★</p>
                        <p>보통입니다.</p>
                        <p class="review-date">2024-11-01</p>
                    </div>
                </div>
                <button class="write-review-button" onclick="location.href='writeReview.html'">리뷰 쓰기</button>
            </div>
	
	<script>

	
	  function wishlistToggle() {
	        
		  var productNo = <%= p.getProductNo() %>;
		  var userNo = <%= (loginUser != null) ? loginUser.getUserNo() : "null" %>; // 세션에서 사용자 ID 가져오기

	        console.log(productNo);
	        console.log(userNo);
	     
	        
	        var isWishlisted = document.getElementById("wishlist-icon").getAttribute("src") === "resources/images/icon/찜.png";

		  
	        $.ajax({
	            type: "POST",
	            url: isWishlisted ? "<%= contextPath %>/wishlist-remove.ad" : "<%= contextPath %>/wishlist-add.ad",
	            data: { productNo: productNo, userNo: userNo },
	            success: function(response) {
	                if (response.status === "success") {
	                    if (isWishlisted) {
	                        console.log("찜상품 삭제 성공");
	                        document.getElementById("wishlist-icon").src = "resources/images/icon/찜해제.png"; // 찜 해제 이미지 경로
	                    } else {
	                        console.log("찜상품 추가 성공");
	                        document.getElementById("wishlist-icon").src = "resources/images/icon/찜.png"; // 찜 이미지 경로
	                    }
	                } else {
	                    console.log("찜상품 처리 실패");
	                }
	            },
	            error: function() {
	                console.log("AJAX 요청 실패");
	            }
	        });
		  
	  }
	        
			  function openLoginPanel() {
				    const loginPanel = document.querySelector('.login-panel');
				    const overlay = document.querySelector('.overlay');
				    loginPanel.classList.add('active');
				    overlay.classList.add('active');
				    overlay.addEventListener('click', closeLoginPanel);
		
				}
	  
	  
		  
	
			function closeLoginPanel() {
			    const loginPanel = document.querySelector('.login-panel');
			    const overlay = document.querySelector('.overlay');
			    loginPanel.classList.remove('active');
			    overlay.classList.remove('active');
			    
			}
	  
	
	</script>
</body>
</html>