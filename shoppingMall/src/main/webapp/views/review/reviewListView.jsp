<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.review.model.vo.Review" %>
<%
	ArrayList<Review> list = (ArrayList<Review>)request.getAttribute("list");
	ArrayList<String> thumbnail = (ArrayList<String>)request.getAttribute("thumbnail");
%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MONO Living</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link  rel="preconnect"
       href="https://fonts.gstatic.com"
       crossorigin="crossorigin">
<link  href="https://fonts.googleapis.com/css2?family=Zen+Antique+Soft&family=
				Zen+Kaku+Gothic+New:wght@300;400;500;700;900&display=swap"
       rel="stylesheet">
<!-- 부트스트랩 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>MONO</title>
<style>
   <style>
        body {
            font-family: 'Zen Kaku Gothic New', sans-serif;
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 30px;
            max-width: 800px;
        }
        .review-card {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            padding: 15px;
        }
        .profile-header {
            display: flex;
            align-items: center;
        }
        .profile-pic {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            background-color: #ccc;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-right: 15px;
            font-size: 20px;
            color: white;
        }
        .review-date {
            color: #777;
            font-size: 0.9em;
        }
        .rating {
            color: gold;
            margin: 5px 0;
        }
        .review-images {
            display: flex;
            gap: 10px;
            margin-top: 15px;
        }
        .review-images img {
            width: 120px;
            height: 120px;
            border-radius: 5px;
        }
        hr {
            border-top: 1px solid #eaeaea;
            margin: 20px 0;
        }
    </style>
    
</style>
</head>
<body>

    <div class="container">
        <h2 class="text-center">리뷰 목록</h2>
        <hr>

        <!-- 리뷰 카드 예시 -->
        <div class="review-card">
            <div class="profile-header">
                <div class="profile-pic">A</div>
                <div>
                    <strong>작성자 이름</strong>
                    <div class="review-date">2023.10.18</div>
                    <div class="rating">★ ★ ★ ★ ☆</div>
                </div>
            </div>
            <div class="review-content">
                <p>리뷰 내용이 여기에 들어갑니다. 제품에 대한 사용 경험을 공유합니다.</p>
            </div>
            <div class="review-images">
                <img src="resources/images/review1.jpg" alt="리뷰 이미지 1">
                <img src="resources/images/review2.jpg" alt="리뷰 이미지 2">
            </div>
        </div>

        <!-- 추가 리뷰 카드 예시 -->
        <div class="review-card">
            <div class="profile-header">
                <div class="profile-pic">B</div>
                <div>
                    <strong>작성자 이름</strong>
                    <div class="review-date">2023.10.19</div>
                    <div class="rating">★ ★ ★ ★ ★</div>
                </div>
            </div>
            <div class="review-content">
                <p>이 제품은 정말 좋아요! 강력 추천합니다.</p>
            </div>
            <div class="review-images">
                <img src="resources/images/review3.jpg" alt="리뷰 이미지 3">
            </div>
        </div>

        <hr>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>