<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.cart.model.vo.Cart, com.kh.product.model.vo.ProductInfo, java.text.NumberFormat" %>
<%
	ArrayList<Cart> list = (ArrayList<Cart>)request.getAttribute("list");

	// 세자릿수마다 콤마 찍으려구
	NumberFormat formatter = NumberFormat.getNumberInstance();
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
        font-size: 18px;
        margin-bottom: 5px;
    }

    .product-details p {
        font-size: 14px;
        color: #666;
    }

	/*
    .quantity-control {
        display: flex;
        align-items: center;
        border: 1px solid #ccc;
        border-radius: 3px;
        overflow: hidden;
        width: 80px;
        margin-top: 26px;
    }
    

	
    .quantity-btn {
	    background-color: white;
	    border: 1px solid #ccc;
	    padding: 5px;
	    font-size: 18px;
	    cursor: pointer;
	    width: 25px;
	    height: 25px;
	    display: inline-flex;
	    align-items: center;
	    justify-content: center;
	    margin: 0;
	}
    

    .quantity-btn:hover {
        background-color: #f0f0f0;
    }

	
    #quantity-input {
        width: 30px;
        text-align: center;
        border: none;
        font-size: 14px;
        outline: none;
    }
    */

    .price-section {
        margin-top: 10px;
    }

    .bottom-box, .delivery-fee {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    
    .bottom-box {
    	margin-bottom: 5px;
    }
    
    .delivery-fee {
    	margin-bottom: 25px;
    }
    
    .top-box p, .bottom-box p, .delivery-fee p, .total-price p {
	    margin: 0; /* 이거 안하면 마진이 생기더라구 */
	}

    .original-price, .total-original-price {
        color: gray;
        font-size: 14px;
        text-align: right;
    }
    
    .plus {
    	margin: 0;
    }

    .discount-price {
        font-weight: 600;
        font-size: 18px;
        margin: 0;
    }

    .delivery-fee {
        font-size: 14px;
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

    .total-discount-price, .total-delivery-price, .real-total-price {
        display: flex;
        justify-content: space-between;
    }
    
    .total-discount-price {
        font-size: 18px;
        margin-bottom: 5px;
    }
    
    .total-delivery-price {
    	font-size: 14px;
    }

    .real-total-price {
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
                    <input type="checkbox" id="select-all" onclick="toggleSelectAll(this)">
                    <span class="select-all">전체선택</span>
                </label>
                <button class="delete-selected">선택 삭제</button>
            </div>

			<% if (list.isEmpty()) { %>
				<p>텅텅</p>
			<% } else { %>

	            <!-- 장바구니 상품 리스트 출력 -->
	            <% for (Cart c : list) { %>
	            <div class="cart-item">
	            
	                <div class="checkbox-delete">
	                    <input type="checkbox" name="cartItem" class="cart-item-checkbox" value="<%= c.getCartNo() %>" onclick="updateSelectAllCheckbox()">
	                    <button class="delete-btn" onclick="deleteCartItem(<%= c.getCartNo() %>)">삭제</button>
	                </div>
	                
	                <script>
	                
		                function deleteCartItem(cartNo) {
		                    if (confirm("해당 상품을 장바구니에서 삭제하시겠습니까?")) {
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
		                }
	                
	               	</script>
	                
	                
	                
	                
	                <div class="product-info">
	                
	                	<!-- 상품썸네일이미지 -->
	                    <img src="<%= contextPath %>/<%= c.getTitleImg() %>" alt="상품 이미지" class="product-image">
	                    
	                    <div class="product-details">
	                    	<!-- 상품이름 -->
	                        <h2><%= c.getProductName() %></h2>
	                        
	                        <!-- 수량조절버튼 -->
	                        <div class="quantity-control">
							    
							    <input type="number" min="1" id="quantity" value="<%= c.getProductQuantity() %>">
							    
							    <button class="quantity-btn" onclick="updateQuantity()">수량 변경</button>
							    
							</div>
							
							
							<!-- 
							<div class="subdiv">
		                        <div class="basketprice"><input type="hidden" name="p_price" id="p_price3" class="p_price" value="15200">15,200원</div>
		                        <div class="num">
		                            <div class="updown">
		                                <input type="text" name="p_num3" id="p_num3" size="2" maxlength="4" class="p_num" value="1" onkeyup="javascript:basket.changePNum(3);">
		                                <span onclick="javascript:basket.changePNum(3);"><i class="fas fa-arrow-alt-circle-up up">-</i></span>
		                                <span onclick="javascript:basket.changePNum(3);"><i class="fas fa-arrow-alt-circle-down down">+</i></span>
		                            </div>
		                        </div>
		                        <div class="sum">15,200원</div>
		                    </div>
							
							
							

							<script>
							let basket = {
								    totalCount: 0, 
								    totalPrice: 0,
								    //체크한 장바구니 상품 비우기
								    delCheckedItem: function(){
								        document.querySelectorAll("input[name=buy]:checked").forEach(function (item) {
								            item.parentElement.parentElement.parentElement.remove();
								        });
								        //AJAX 서버 업데이트 전송
								    
								        //전송 처리 결과가 성공이면
								        this.reCalc();
								        this.updateUI();
								    },
								    //장바구니 전체 비우기
								    delAllItem: function(){
								        document.querySelectorAll('.row.data').forEach(function (item) {
								            item.remove();
								          });
								          //AJAX 서버 업데이트 전송
								        
								          //전송 처리 결과가 성공이면
								          this.totalCount = 0;
								          this.totalPrice = 0;
								          this.reCalc();
								          this.updateUI();
								    },
								    //재계산
								    reCalc: function(){
								        this.totalCount = 0;
								        this.totalPrice = 0;
								        document.querySelectorAll(".p_num").forEach(function (item) {
								            if(item.parentElement.parentElement.parentElement.previousElementSibling.firstElementChild.firstElementChild.checked == true){
								                var count = parseInt(item.getAttribute('value'));
								                this.totalCount += count;
								                var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
								                this.totalPrice += count * price;
								            }
								        }, this); // forEach 2번째 파라메터로 객체를 넘겨서 this 가 객체리터럴을 가리키도록 함. - thisArg
								    },
								    //화면 업데이트
								    updateUI: function () {
								        document.querySelector('#sum_p_num').textContent = '상품갯수: ' + this.totalCount.formatNumber() + '개';
								        document.querySelector('#sum_p_price').textContent = '합계금액: ' + this.totalPrice.formatNumber() + '원';
								    },
								    //개별 수량 변경
								    changePNum: function (pos) {
								        var item = document.querySelector('input[name=p_num'+pos+']');
								        var p_num = parseInt(item.getAttribute('value'));
								        var newval = event.target.classList.contains('up') ? p_num+1 : event.target.classList.contains('down') ? p_num-1 : event.target.value;
								        
								        if (parseInt(newval) < 1 || parseInt(newval) > 99) { return false; }

								        item.setAttribute('value', newval);
								        item.value = newval;

								        var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
								        item.parentElement.parentElement.nextElementSibling.textContent = (newval * price).formatNumber()+"원";
								        //AJAX 업데이트 전송

								        //전송 처리 결과가 성공이면    
								        this.reCalc();
								        this.updateUI();
								    },
								    checkItem: function () {
								        this.reCalc();
								        this.updateUI();
								    },
								    delItem: function () {
								        event.target.parentElement.parentElement.parentElement.remove();
								        this.reCalc();
								        this.updateUI();
								    }
								}

								// 숫자 3자리 콤마찍기
								Number.prototype.formatNumber = function(){
								    if(this==0) return 0;
								    let regex = /(^[+-]?\d+)(\d{3})/;
								    let nstr = (this + '');
								    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
								    return nstr;
								};
						    </script>
						     -->

	                        
	                        
	                    </div>
	                </div>
	                
	                <div class="price-section">
	                    <div class="top-box">
						    <p class="original-price" id="price-<%= c.getCartNo() %>"><del>₩ <%= formatter.format(c.getTotalPrice()) %></del></p>
						</div>
						<div class="bottom-box">
							<p class="label">상품금액</p>
							<h1 id="justTotal"></h1>
						    <p  data-price="<%= c.getPrice() %>" data-discount="<%= c.getDiscount() %>" class="discount-price" id="price" >₩ <%= formatter.format(c.getTotalDiscountPrice()) %></p>
						</div>
						
	                    <div class="delivery-fee">
	                        <p>배송비</p>
	                        <p>₩ 0</p> <!-- 0으로 고정 -->
	                    </div>
	                </div>
	                
	                <button class="buy-now">바로 구매</button>
	            </div>
	            
	            <% System.out.println(c.getCartNo() + c.getProductQuantity()); %>
	            <% } %> <!-- for문 종료 -->
	        <% } %>
        </div>
	
	    <!-- 오른쪽 결제 정보 영역 -->
	    <div class="payment-summary">
	        <div class="summary-box">
	        	<div class="total-price">
	        		<p class="total-original-price">
	                    <del>₩ <% 
	                    int justTotal = 0;
	                    for (Cart c : list) { 
	                    	justTotal += c.getTotalPrice();
	                    } 
	                    out.print(formatter.format(justTotal));
	                    %></del>
	                </p>
	        	</div>
	            <div class="total-discount-price">
	                <p class="plus">합계</p>
	                <p class="discount-price" >
	                    ₩ 
	                    <% 
	                    int realTotal = 0;
	                    for (Cart c : list) { 
	                    	realTotal += c.getTotalDiscountPrice();
	                    } 
	                    out.print(formatter.format(realTotal));
	                    %>
	                </p>
	            </div>
	            <div class="total-delivery-price">
	                <p>배송</p>
	                <p>₩ 0</p> <!-- 0으로 고정 -->
	            </div>
	            <br><br>
	            <div class="real-total-price">
	                <p>총액</p>
	                <!-- 배송비는 0으로 고정. 고로 총액 == 합계 -->
	                <p>₩ <%= formatter.format(realTotal) %></p>
	            </div>
	            <button class="checkout-btn">결제하기</button>
	        </div>
	    </div>
	</div>
	
	<script>
	
	
		// 전체 선택/해제 기능
        function toggleSelectAll(selectAllCheckbox) {
            const checkboxes = document.querySelectorAll(".cart-item-checkbox");
            checkboxes.forEach((checkbox) => {
                checkbox.checked = selectAllCheckbox.checked;
            });
        }
		
     	// 개별 체크박스 클릭 시 전체선택 체크박스 업데이트
        function updateSelectAllCheckbox() {
            const selectAllCheckbox = document.getElementById("select-all");
            const checkboxes = document.querySelectorAll(".cart-item-checkbox");
            const allChecked = Array.from(checkboxes).every(checkbox => checkbox.checked);

            selectAllCheckbox.checked = allChecked;
        }
     	
    </script>
	
	<%@ include file="../common/footer.jsp" %>

</body>
</html>