<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/static/css/enrollProduct.css>" rel="stylesheet" />
</head>
<body>

	<%@ include file="../common/nav.jsp" %>
	
	<div id="layoutSidenav_content">
       <main>
	
		    <div class="container mt-5">
		        <h1 class="text-center mb-4">상품 등록</h1>
				<form id="insert-form" action="<%= contextPath %>/insert.pr" method="post" enctype="multipart/form-data" class="shadow p-4 rounded bg-light">
					<div class="mb-3">
						<label for="productName" class="form-label">상품 이름:</label>
						<input type="text" id="productName" name="productName" class="form-control" required>
					</div>
					<div class="mb-3">
						<label for="productDescription" class="form-label">상품 설명:</label>
						<textarea id="productDescription" name="productDescription" class="form-control"></textarea>
					</div>
					<div class="mb-3">
						<label for="categoryNo" class="form-label">카테고리:</label>
						<select id="categoryNo" name="categoryNo" class="form-select" required> <!-- 수정된 부분 -->
							<option value="" disabled selected>카테고리 선택</option>
							<option value="1">침대</option>
							<option value="2">테이블·식탁·책상</option>
							<option value="3">서랍·수납장</option>
							<option value="4">진열장·책장</option>
							<option value="5">의자</option>
							<option value="6">행거·옷장</option>
						</select>
					</div>
				
		            <div class="mb-3">
		                <label for="price" class="form-label">가격:</label>
		                <input type="number" id="price" name="price" class="form-control" required min="0">
		                <input type="range" id="priceSlider" min="0" max="5000000" step="100" value="0" oninput="updatePrice(this)" class="form-range mt-2">
		                <small class="form-text text-muted">슬라이더로 가격을 조절하거나 직접 입력하세요.</small>
		            </div>
		            <div class="mb-3">
		                <label for="quantity" class="form-label">재고:</label>
		                <input type="number" id="quantity" name="productQuantity" class="form-control" required min="0" step="1">     
		            </div>
		            <div class="mb-3">
		                <label for="productSize" class="form-label">상품 사이즈  (가로 x 세로 x 높이):</label>
		                <input type="text" id="productSize" name="productSize" class="form-control" required>
		            </div>
		            <div class="mb-3">
		                <label for="material" class="form-label">재질:</label>
		                <input type="text" id="material" name="material" class="form-control" required>
		            </div>
		            <div class="mb-3">
		                <label for="color" class="form-label">색상:</label>
		                <input type="text" id="color" name="color" class="form-control" required>
		            </div>
		            <div class="mb-3">
		                <label for="assemblyYN" class="form-label">조립 여부:</label>
		                <select id="assemblyYN" name="assemblyYN" class="form-select" required>
		                    <option value="Y">예</option>
		                    <option value="N">아니오</option>
		                </select>
		            </div>
		            <div class="mb-3">
		                <label for="discount" class="form-label">할인율 (%):</label>
		                <input type="number" id="discount" name="discount" class="form-control" min="0" max="100" step="1" required>
		            </div>
					<div class="mb-3">
						<label for="country" class="form-label">제조국 : </label>
						<select id="countrySelect" name="country" class="form-select" onchange="toggleCountryInput()" required>
							<option value="" disabled selected>국가 선택</option>
							<option value="미국">미국</option>
							<option value="대한민국">대한민국</option>
							<option value="중국">중국</option>
							<option value="일본">일본</option>
							<option value="기타">기타</option>
						</select>
						<input type="text" id="countryInput" name="countryInput" class="form-control mt-2" placeholder="국가를 직접 입력" style="display: none;">

					</div>
		            
		            <div class="mb-3">
		                <label for="thumbnailImage" class="form-label">썸네일 이미지:</label>
		                <input type="file" id="detailImage1" name="detailImage1" class="form-control" accept="image/*" required>
		            </div>
		            <div class="mb-3">
		                <label for="detailImage" class="form-label">상품 상세 이미지 (최대 5개):</label>
			         	   <%for(int i=2; i<=6; i++) { %>
			               		<input type="file" id="detailImage<%= i %>" name="detailImage<%= i %>" class="form-control">
			               <%} %>
		                <small class="form-text text-muted">최대 5개의 이미지를 업로드할 수 있습니다.</small>
		            </div>
		            <button type="submit" class="btn btn-primary">상품 등록</button>
		        </form>
		    </div>
		    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		
		    <script>
		        function toggleCountryInput() {
		            var select = document.getElementById("countrySelect");
		            var input = document.getElementById("countryInput");
		            if (select.value === "other") {
		                input.style.display = "block"; // '기타' 선택 시 입력 필드 보이기
		                input.required = true; // 필수 입력으로 설정
		            } else {
		                input.style.display = "none"; // '기타' 아닐 경우 숨기기
		                input.required = false; // 필수 입력 해제
		            }
		        }
		
		        function updatePrice(slider) {
		            var priceInput = document.getElementById("price");
		            priceInput.value = slider.value; // 슬라이더 값이 가격 입력 필드에 반영
		        }
		    </script>
		  </main>
		</div>


</body>
</html>