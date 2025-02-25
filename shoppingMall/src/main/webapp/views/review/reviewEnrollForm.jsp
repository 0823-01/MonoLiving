<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.review.model.vo.Review" %>
<%
	ArrayList<Review> list = (ArrayList<Review>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" rel="stylesheet">
<style>
	.outer {
		width: 800px;
		margin : auto;
		margin-top : 30px;
		border : 1px solid gray;
	}
	table {
		padding-left: 20px;
		margin: auto;
		border : 1px solid gray;
	}
	table>th {
		margin-bottom: 30px;
	}
	#enroll-form input,
	#enroll-form textarea {
		width: 100%;
		box-sizing: border-box;
		cursor: pointer;
	}
	#reviewImg-area {
		width : 100%;
	}

	/* 별점 스타일 */
	.star_rating {
		width: 100%; 
		box-sizing: border-box; 
		display: inline-flex; 
		float: left;
		flex-direction: row; 
		justify-content: flex-start;
	}
	.star-rating .star {
		background: url('resources/images/icon/빈별.png') no-repeat;
		width: 25px; 
		height: 25px; 
		margin-right: 10px;
		display: inline-block;
		background-size: 100%; 
		box-sizing: border-box; 
	}
	.star-rating .star.on {
		background: url('resources/images/icon/꽉찬별.png') no-repeat;
		width: 25px; 
		height: 25px; 
		margin-right: 10px;
		display: inline-block;
		background-size: 100%; 
		box-sizing: border-box; 
	}
	

	
</style>
</head>
<body>
	
	<%@ include file="../common/header.jsp" %>

	

	<div class="outer">

		<br>
		<h2 align="center">리뷰 작성</h2>
		<br>

		<form id="enroll-form"
			  action="<%= contextPath %>/insert.re"
			  method="post"
			  enctype="multipart/form-data">

			  <input type="hidden" name="userNo"
			  		 value="<%= loginUser.getUserNo() %>">

			<table>
				 <!-- 상품 정보 표시 -->
				 <tr>
					<th>상품 정보</th>
					<td colspan="2">
						<img src="<%= productThumbnail %>" alt="상품 이미지" class="product-thumbnail">
						<span><%= productName %></span>
					</td>
				</tr>


				<tr>
					<th>
						구매하신 상품은 만족하시나요?
					</th>
				</tr>

				<tr>
					<td conlspan="3">
						
						 <div class="star-rating">
				            <span class="star" value="1"></span>
				            <span class="star" value="2"></span>
				            <span class="star" value="3"></span>
				            <span class="star" value="4"></span>
				            <span class="star" value="5"></span>
				        </div>
						
						<input type="hidden" id="reviewStar" name="reviewStar" value="">   

					</td>
				</tr>
				
				

				<tr>
					<th>자세한 리뷰를 작성해 주세요.</th>
				</tr>

				<tr>
					<td colspan="3">
						<textarea name="reviewComment"
								  rows="5"
								  style="resize : none;"
								  required></textarea>
					</td>
				</tr>

				<!-- 리뷰 이미지 미리보기 영역 -->
				<tr>
					<th>사진 첨부하기</th>
				</tr>

				<tr>
					<td>
						<img id="reviewImg1"
							 width="150px" height="120px">
					</td>
					<td>
						<img id="reviewImg2"
							 width="150px" height="120px">
					</td>
					<td>
						<img id="reviewImg3"
							 width="150px" height="120px">
					</td>
				</tr>

			</table>

			<!-- 실제 파일을 입력 받을 수 있는 요소 -->
			<div id="file-area" style="display : none;">
				<input type="file" id="file1" name="file1"
					   onchange="loadImg(this, 1);">
				<input type="file" id="file2" name="file2"
					   onchange="loadImg(this, 2);">
				<input type="file" id="file3" name="file3"
					   onchange="loadImg(this, 3);">
			</div>

			
			
			<br><br>
		
			<div align="center">
				<button type="submit">등록하기</button>
			</div>

		</form>

	</div>
	
	<script>
		$('.star-rating span').click(function() {
		    $(this).parent().children('span').removeClass('on');
		    $(this).addClass('on').prevAll('span').addClass('on');
		    
		    // value 속성에서 값 가져오기
		    $("input[name=reviewStar]").val($(this).attr("value"));
	
		    console.log("Selected star rating: " + $(this).attr("value")); // 디버깅용 콘솔 출력
		});
		
		
		$(function() {

			$("#reviewImg1").click(function() {

				$("#file1").click();
			});
			$("#reviewImg2").click(function() {

				$("#file2").click();
			});
			$("#reviewImg3").click(function() {

				$("#file3").click();
			});
		});

		function loadImg(inputFile, num) {

			if(inputFile.files.length == 1) {

				let reader = new FileReader();
				reader.readAsDataURL(inputFile.files[0]);

				reader.onload = function(e) {

					switch(num) {
						case 1 : $("#reviewImg1").attr("src", e.target.result); break;
						case 2 : $("#reviewImg2").attr("src", e.target.result); break;
						case 3 : $("#reviewImg3").attr("src", e.target.result); break;
					}
				};
			} else {

				switch(num) {
					case 1 : $("#reviewImg1").removeAttr("src"); break;
					case 2 : $("#reviewImg2").removeAttr("src"); break;
					case 3 : $("#reviewImg3").removeAttr("src"); break;
				}
			}
		}
		</script>

</body>
</html>