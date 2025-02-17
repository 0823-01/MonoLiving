<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.cart.model.vo.Cart, com.kh.product.model.vo.ProductInfo, java.text.NumberFormat" %>
<%ArrayList<Cart> list = (ArrayList<Cart>)request.getAttribute("list");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body {
        font-family: Arial, sans-serif;
    }

    .container {
        width: 1200px; /* 전체 컨테이너 가로 사이즈 */
        display: flex;
        justify-content: space-between;
        margin: 0 auto; /* 화면 중앙에 배치 */
        padding-top: 50px;
    }
    
    .emptyCart {
    	font-size: 18px;
    	text-align: center;
    	padding-top: 50px;
    	color: #999;
    }

    .cart-items {
        width: 65%; /* cart-items의 가로 사이즈를 container의 65%로 설정 */
        padding: 0 20px;
    }

    .cart-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 15px 0;
        border-bottom: 1px solid #ddd;
        margin-bottom: 20px;
    }

    .cart-header button, .delete-btn {
        background-color: #fff;
        border: 1px solid #ccc;
        padding: 5px 10px;
        cursor: pointer;
        border-radius: 30px;
        font-size: 12px;
    }

    .cart-item {
        display: flex;
        flex-direction: column;
        border: 1px solid #ddd;
        border-radius: 20px;
        padding: 20px;
        margin-bottom: 20px;
    }

    .checkbox-delete {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;
    }

    .product-info {
        display: flex;
        align-items: center;
    }

    .product-image {
        width: 100px;
        height: 100px;
        background-color: #ddd;
        margin-right: 20px;
    }

    .product-details h2 {
        font-size: 16px;
        font-weight: 500;
        margin-bottom: 10px;
    }

    .product-details p {
        font-size: 14px;
        color: #666;
    }
    
    
    
	.quantity-control {
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    gap: 3px; /* 버튼과 입력창 간격 */
	    border: 1px solid #ddd;
	    border-radius: 4px;
	    width: fit-content;
	    padding: 3px;
	}
	
	/* 수량 조절 버튼 */
	.quantity-btn {
	    width: 26px;
	    height: 26px;
	    font-size: 15px;
	    font-weight: bold;
	    color: #555;
	    background-color: #f8f8f8;
	    border: none;
	    border-radius: 3px;
	    cursor: pointer;
	    transition: all 0.2s ease-in-out;
	}
	
	.quantity-btn:hover {
	    background-color: #ddd;
	}
	
	.quantity-btn:active {
	    background-color: #ccc;
	}
	
	/* 수량 입력 필드 */
	.product-quantity {
	    width: 32px;
	    height: 26px;
	    text-align: center;
	    font-size: 14px;
	    font-weight: bold;
	    border: none;
	    background-color: #fff;
	    outline: none;
	}



    

    .price-section {
        margin-top: 10px;
    }

    .bottom-box, .delivery-price {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    
    .bottom-box {
    	margin-bottom: 5px;
    }
    
    .delivery-price {
    	font-size: 14px;
    	margin-bottom: 25px;
    }
    
    .top-box p, .bottom-box p, .delivery-price p, .original-payment p, .plus {
	    margin: 0; /* 이거 안하면 마진이 생기더라구 */
	}

    .original-price, .original-payment-price {
        color: gray;
        font-size: 14px;
        text-align: right;
        text-decoration: line-through;
    }
    
    .discounted-payment-price {
        font-weight: 600;
        font-size: 18px;
        margin: 0;
    }

	/* 결제 정보 영역 */
    .payment-summary {
        width: 35%;
        position: relative;
        padding: 10px;
    }

    .summary-box {
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 10px;
        position: fixed;
        width: 25%;
    }

    .discounted-payment, .delivery-payment, .total-payment {
        display: flex;
        justify-content: space-between;
    }
    
    .discounted-payment {
        font-size: 18px;
        margin-bottom: 5px;
    }
    
    .delivery-payment {
    	font-size: 14px;
    }

    .total-payment {
        font-weight: 600;
        font-size: 22px;
    }

	/* 스르륵 효과를 위한 트랜지션 효과 */
    .buy-now, .checkout-btn {
        background-color: #000;
        color: #fff;
        padding: 10px 50px;
        border: 1px solid black;
        border-radius: 60px;
        cursor: pointer;
        font-size: 16px;
        font-weight: 600;
        transition: background-color 0.3s ease, color 0.3s ease;
    }
    
    .checkout-btn {
        width: 100%;
        font-size: 18px;
    }
    
    .buy-now:hover, .checkout-btn:hover {
        background-color: #fff;
        color: #000;
        border: 1px solid black;
    }

</style>

</head>
<body>

	<%@ include file="../common/header.jsp" %>

	<div class="container">
	    <!-- 왼쪽 상품 리스트 영역 -->
	    <div class="cart-items">
	        <div class="cart-header">
	            <label>
	                <input type="checkbox" id="select-all">
	                <span class="select-all">전체선택</span>
	            </label>
	            <form id="checkDeleteForm" action="<%= request.getContextPath() %>/deleteCheck.ct" method="post">
        			<input type="hidden" name="cartNos" id="delCartNos">
	            	<button class="delete-selected" onclick="deleteSelectedItems()">선택 삭제</button>
	            </form>
	        </div>
	
	        <% if (list.isEmpty()) { %>
	            <div class="emptyCart">장바구니에 담긴 상품이 없습니다.</div>
	        <% } else { %>
	
	        <% for (Cart c : list) { %>
		        <div class="cart-item">
		            <div class="checkbox-delete">
		                <input type="checkbox" name="cartItem" class="cart-item-checkbox" value="<%= c.getCartNo() %>">
		                <button class="delete-btn" onclick="deleteCartItem(<%= c.getCartNo() %>)">삭제</button>
		            </div>
		            <div class="product-info">
		                <img src="<%= contextPath %>/<%= c.getTitleImg() %>" alt="상품 이미지" class="product-image">
		                <div class="product-details">
		                    <h2><%= c.getProductName() %></h2>
		                    <div class="quantity-control">
		                        <button class="quantity-btn decrease" data-cart-no="<%= c.getCartNo() %>">-</button>
		                        <input type="text" class="product-quantity" value="<%= c.getProductQuantity() %>" readonly>
		                        <button class="quantity-btn increase" data-cart-no="<%= c.getCartNo() %>">+</button>
		                    </div>
		                </div>
		            </div>
		            <div class="price-section">
		                <div class="top-box">
		                    <p class="original-price" data-original-price="<%= c.getOriginalPrice() %>">₩ <%= String.format("%,d", c.getOriginalPrice() * c.getProductQuantity()) %></p>
		                </div>
		                <div class="bottom-box">
		                    <p class="label">상품금액</p>
		                    <p class="discounted-price" data-discounted-price="<%= c.getDiscountedPrice() %>">₩ <%= String.format("%,d", c.getDiscountedPrice() * c.getProductQuantity()) %></p>
		                </div>
		                <div class="delivery-price">
		                    <p>배송비</p>
		                    <p>₩ 0</p> <!-- 0으로 고정 -->
		                </div>
		            </div>
		            <button class="buy-now">바로 구매</button>
		        </div>
		        <% } %> <!-- for문 종료 -->
	        <% } %>
	    </div>
	
	    <!-- 오른쪽 결제 정보 영역 -->
	    <div class="payment-summary">
	        <div class="summary-box">
	            <div class="original-payment">
	                <p class="original-payment-price">₩ <%= String.format("%,d", list.stream().mapToInt(c -> c.getOriginalPrice() * c.getProductQuantity()).sum()) %></p>
	            </div>
	            <div class="discounted-payment">
	                <p class="plus">합계</p>
	                <p class="discounted-payment-price">₩ <%= String.format("%,d", list.stream().mapToInt(c -> c.getDiscountedPrice() * c.getProductQuantity()).sum()) %></p>
	            </div>
	            <div class="delivery-payment">
	                <p>배송</p>
	                <p>₩ 0</p> <!-- 0으로 고정 -->
	            </div>
	            <br><br>
	            <div class="total-payment">
	                <p>총액</p>
	                <p id="total-price">₩ <%= String.format("%,d", list.stream().mapToInt(c -> c.getDiscountedPrice() * c.getProductQuantity()).sum()) %></p>
	            </div>
	            <button class="checkout-btn">결제하기</button>
	        </div>
	    </div>
	</div>
	
	<%@ include file="../common/footer.jsp" %>
	
	<script>
	
		function updateTotalPrice() {
		    let totalOriginal = 0;
		    let totalDiscounted = 0;
		
		    $(".cart-item").each(function() {
		        let isChecked = $(this).find(".cart-item-checkbox").prop("checked");
		        if (isChecked) {
		            let productQuantity = parseInt($(this).find(".product-quantity").val());
		            let originalPrice = parseFloat($(this).find(".original-price").data("original-price"));
		            let discountedPrice = parseFloat($(this).find(".discounted-price").data("discounted-price"));
		
		            let totalOriginalPrice = originalPrice * productQuantity;
		            let totalDiscountedPrice = discountedPrice * productQuantity;
		
		            totalOriginal += totalOriginalPrice;
		            totalDiscounted += totalDiscountedPrice;
		        }
		    });
		
		    $(".original-payment-price").text("₩ " + totalOriginal.toLocaleString("en-US"));
		    $(".discounted-payment-price").text("₩ " + totalDiscounted.toLocaleString("en-US"));
		    $("#total-price").text("₩ " + totalDiscounted.toLocaleString("en-US"));
		}
		
		$(document).ready(function() {
			$(".cart-item-checkbox").prop("checked", true);
		    $("#select-all").prop("checked", true);
		    
			updateTotalPrice();
		
		    $(".quantity-btn").click(function() {
		        let button = $(this);
		        let cartNo = button.data("cart-no");
		        let inputField = button.siblings(".product-quantity");
		        let newQuantity = parseInt(inputField.val());
		
		        if (button.hasClass("increase")) {
		            newQuantity += 1;
		        } else if (button.hasClass("decrease")) {
		            if (newQuantity > 1) {
		                newQuantity -= 1;
		            } else {
		                return;
		            }
		        }
		
		        inputField.val(newQuantity);
		
		        $.ajax({
		            url: "updateQuantity.ct",
		            method: "POST",
		            data: {
		                cartNo: cartNo,
		                productQuantity: newQuantity
		            },
		            dataType: "json",
		            success: function(response) {
		                if (response.status === "success") {
		                    let cartItem = button.closest(".cart-item");
		
		                    let originalPrice = parseFloat(cartItem.find(".original-price").data("original-price"));
		                    let discountedPrice = parseFloat(cartItem.find(".discounted-price").data("discounted-price"));
		
		                    let totalOriginalPrice = originalPrice * newQuantity;
		                    let totalDiscountedPrice = discountedPrice * newQuantity;
		
		                    cartItem.find(".original-price").text("₩ " + totalOriginalPrice.toLocaleString("en-US"));
		                    cartItem.find(".discounted-price").text("₩ " + totalDiscountedPrice.toLocaleString("en-US"));
		
		                    updateTotalPrice();
		                } else {
		                    alert("수량 변경 실패");
		                }
		            },
		            error: function() {
		                alert("서버 오류");
		            }
		        });
		    });
		
		    // 개별 상품 체크박스 클릭 시 가격 업데이트
		    $(".cart-item-checkbox").change(updateTotalPrice);
		
		    // 전체 선택 체크박스 동작
		    $("#select-all").change(function() {
		        let isChecked = $(this).prop("checked");
		        $(".cart-item-checkbox").prop("checked", isChecked);
		        updateTotalPrice();
		    });
		
		    // 개별 체크박스 상태에 따라 전체 선택 체크박스 변경
		    $(".cart-item-checkbox").change(function() {
		        let totalCheckboxes = $(".cart-item-checkbox").length;
		        let checkedCheckboxes = $(".cart-item-checkbox:checked").length;
		
		        $("#select-all").prop("checked", totalCheckboxes === checkedCheckboxes);
		    });
		});
		
		function deleteCartItem(cartNo) {
		    if (!confirm("해당 상품을 장바구니에서 삭제하시겠습니까?")) {
		        return;
		    }
		    
		       $.ajax({
		           url: "delete.ct", // 삭제 요청을 처리하는 서블릿 URL
		           method: "POST",
		           data: { cartNo: cartNo },
		           success: function(response) {
		               if (response > 0) {
		                   alert("상품이 삭제되었습니다.");
		                   
		                   location.reload(); // 페이지 새로고침
		               } else {
		                   alert("삭제에 실패했습니다.");
		               }
		           },
		           error: function() {
		               alert("서버 통신 오류가 발생했습니다.");
		           }
		       });
		   }
		
		function deleteSelectedItems() {
		    const $checkDeleteForm = $("#checkDeleteForm");
		    const $checkItems = $(".cart-item-checkbox:checked"); // 체크된 상품만 가져오기
		
		    if ($checkItems.length === 0) {
		        alert("삭제할 상품을 선택하세요.");
		        return;
		    }
		    
		    if (!confirm("선택된 상품을 장바구니에서 삭제하시겠습니까?")) {
		        return;
		    }
		
		    let cartNos = "";
		    $checkItems.each(function() {
		        cartNos += $(this).val() + ","; // 값들을 쉼표(,)로 연결
		    });
		
		    cartNos = cartNos.slice(0, -1); // 마지막 쉼표 제거
		    $("#delCartNos").val(cartNos); // hidden input에 cartNos 값 설정
		    $checkDeleteForm.submit(); // 폼 전송
		}
		
	</script>
</body>
</html>