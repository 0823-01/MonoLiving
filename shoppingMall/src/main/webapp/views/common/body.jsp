<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MONO Living</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link  rel="preconnect"
       href="https://fonts.gstatic.com"
       crossorigin="crossorigin">
<link  href="https://fonts.googleapis.com/css2?family=Zen+Antique+Soft&family=Zen+Kaku+Gothic+New:wght@300;400;500;700;900&display=swap"
       rel="stylesheet">
<title>MONO</title>
<link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>

			<div id="homebody">
            <!-- Main Image -->
            <div id="main-image-container">
                <img src="resources/images/main/main1.jpeg" alt="main image">
            </div>

            <!-- Main Text -->
            <div id="main-text1">WHAT'S NEW</div>
            <div class="rounded-box"></div>

            <!-- Product Grid -->
            <div id="product-grid">

                <div class="product" id="pro1">
                    <img
                        src="resources/images/main/img1.png"
                        alt="KALLAX Chair"
                        style="width: 400px; height: 400px;">
                    <div class="name">KALLAX 선반유닛, 화이트</div>
                    <div class="price">49,900원</div>
                </div>
                <div class="product" id="pro2">
                    <img
                        src="resources/images/main/img2.png"
                        alt="BOLLSIDAN Stool"
                        style="width: 400px; height: 400px;">
                    <div class="name">BOLLSIDAN 볼시단 노트북스탠드, 화이트</div>
                    <div class="price">49,900원</div>
                </div>
                <div class="product" id="pro3">
                    <img
                        src="resources/images/main/img3.png"
                        alt="VIHALS Dresser"
                        style="width: 400px; height: 400px;">
                    <div class="name">VIHALS 비할스 미닫이수납장, 화이트</div>
                    <div class="price">49,900원</div>
                </div>
                <div class="product" id="pro4">
                    <img
                        src="resources/images/main/img4.png"
                        alt="ALEX Shelf Unit"
                        style="width: 400px; height: 400px;">
                    <div class="name">ALEX 알렉스 서랍유닛, 화이트</div>
                    <div class="price">49,900원</div>
                </div>
                <div class="product" id="pro5">
                    <img
                        src="resources/images/main/img5.png"
                        alt="KURA Bed"
                        style="width: 400px; height: 400px;">
                    <div class="name">KURA 쿠라 양면침대, 화이트/소나무</div>
                    <div class="price">49,900원</div>
                </div>
                <div class="product" id="pro6">
                    <img
                        src="resources/images/main/img6.png"
                        alt="OMAR Bed"
                        style="width: 400px; height: 400px;">
                    <div class="name">OMAR 오마르 침대섹션1</div>
                    <div class="price">49,900원</div>
                </div>
            </div>

            <!-- Main2 Image with rounded corners -->
            <div id="main-image-container-2" style="padding: 20px; overflow: hidden;">
                <img
                    src="resources/images/main/main2.jpeg"
                    alt="main image"
                    id="main2-image"
                    style="width: 100%; height: auto;">
            </div>

            <!-- "STEADY SELLER" Section -->
            <div id="steady-seller-section">
                <div id="steady-seller-container">
                    <div id="steady-seller-text">
                        <div style="font-size: 30px;">STEADY SELLER</div>
                        <div style="margin-top: 25px;">VIEW MORE →</div>
                    </div>
                    <div id="steady-seller-grid">
                        <div class="steady-seller-product" id="product1">
                            <img src="resources/images/main/furniture9.png" alt="Seller 1">
                            <div class="product-info">
                                <div>Product 1</div>
                                <div>₩99,900</div>
                            </div>
                        </div>
                        <div class="steady-seller-product" id="product2">
                            <img src="resources/images/main/furniture8.png" alt="Seller 2">
                            <div class="product-info">
                                <div>Product 2</div>
                                <div>₩49,900</div>
                            </div>
                        </div>
                        <div class="steady-seller-product">
                            <img src="resources/images/main/furniture7.png" alt="Seller 3">
                            <div class="product-info">
                                <div>Product 3</div>
                                <div>₩29,900</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

<!-- NEW IN and VIEW MORE Section -->
<div id="fun-section" style="margin-bottom: 50px; margin-top: 50px; position: relative;"> <!-- 부모 요소에 position 추가 -->
    <div id="sopaSession" style="display: flex; justify-content: space-between; flex-wrap: wrap; gap: 20px; position: relative; top: 250px; padding: 30px;" > <!-- top으로 이동 -->
        <div style="text-align: center;">
            <img src="resources/images/main/sopaimg.png" style="width: 300px; height: 330px; margin-left: 80px;">
            <div id="sopaimgt" style="margin-left: 80px; padding: 30px 0;"> <!-- 위아래 여백 추가 -->
                <b>[10-11월 행사] 켈리 Q 침대 + 아벨 Q 메트릭스</b> <br>
                <br>
                <s>2,030,000</s><br>
                1,190,000
            </div>
        </div>
        <div style="text-align: center;">
            <img src="resources/images/main/sopaimg1.png" style="width: 300px; height: 330px; ">
            <div id="sopaimg1t" style="padding: 30px 0;">
                <b>[10-11월 행사] 세비아 Q침대 +모닝 Q 매트릭스</b> <br>
                <br>
                <s>2,030,000</s><br>
                1,190,000
            </div>
        </div>
        <div style="text-align: center;">
            <img src="resources/images/main/sopaimg2.png" style="width: 300px; height: 330px; ">    
            <div id="sopaimg2t" style="padding: 30px 0;">
                <b>[10-11월 행사] 마티니 SS침대 + 오르보 매트릭스</b> <br>
                <br>
                <s>2,030,000</s><br>
                1,190,000
            </div>
        </div>
        <div style="text-align: center;">
            <img src="resources/images/main/sopaimg3.png" style="width: 300px; height: 330px; margin-right: 80px;">
            <div id="sopaimg3t" style="margin-right: 80px; padding: 30px 0;">
                <b>[10-11월 행사] 솔로 SS침대 + 데이 SS매트릭스</b> <br>
                <br>
                <s>2,030,000</s><br>
                1,190,000
            </div>
        </div>
    </div>
</div>



            <div id="main-content">
                <div id="main4-image">
                    <img src="resources/images/main/main4.jpeg" alt="Main Image">
                </div>

                <div id="sopaPart">
                    <div id="product-grid">
                        <div class="product">
                            <img
                                src="resources/images/main/sopa1.png"
                                alt="Product 1"
                                style="width: 400px; height: 300px;">
                            <div class="name">시애나 스킹능 카시미라 패브릭 소파</div>
                            <div class="price">₩3,040,000
                                <span class="discount">24%</span></div>
                        </div>
                        <div class="product">
                            <img
                                src="resources/images/main/sopa2.png"
                                alt="Product 2"
                                style="width: 400px; height: 300px;">
                            <div class="name">파비아 4인 오픈형 토고 소가죽 소파</div>
                            <div class="price">₩3,600,000
                                <span class="discount">25%</span></div>
                        </div>
                        <div class="product">
                            <img
                                src="resources/images/main/sopa3.png"
                                alt="Product 3"
                                style="width: 400px; height: 300px;">
                            <div class="name">에비뉴 4인 카시미라 패브릭 소파</div>
                            <div class="price">₩2,698,000
                                <span class="discount">29%</span></div>
                        </div>

                        <div class="product">
                            <img
                                src="resources/images/main/sopa4.png"
                                alt="Product 3"
                                style="width: 400px; height: 300px;">
                            <div class="name">에비뉴 4인 카시미라 패브릭 소파</div>
                            <div class="price">₩2,698,000
                                <span class="discount">29%</span></div>
                        </div>

                        <div class="product">
                            <img
                                src="resources/images/main/sopa5.png"
                                alt="Product 3"
                                style="width: 400px; height: 300px;">
                            <div class="name">에비뉴 4인 카시미라 패브릭 소파</div>
                            <div class="price">₩2,698,000
                                <span class="discount">29%</span></div>
                        </div>

                        <div class="product">
                            <img
                                src="resources/images/main/sopa6.png"
                                alt="Product 3"
                                style="width: 400px; height: 300px;">
                            <div class="name">에비뉴 4인 카시미라 패브릭 소파</div>
                            <div class="price">₩2,698,000
                                <span class="discount">29%</span></div>
                        </div>
                    </div>
                </div>

            </div>

            <div id="main-content1" style="padding: 50px; margin-top: 50px;">
                <div class="text-section">
                    <div
                        class="rounded-box1"
                        style="width: 80px;height: 1.5px; border-radius: 15px; background-color: white; margin-bottom: 25px;"></div>

                    <b>가구 하나로 완성한
                        <br>
                        모노만의 휴식 루틴</b>
                    <br><br><br>
                    <div
                        class="rounded-box2"
                        style="width: 450px;height: 1px; border-radius: 15px; background-color: white; margin-bottom: 30px;"></div>

                    AM 10:37<br>
                    상쾌하게 맞이하는 하루<br>
                    건강하게 나를 충전하는 아침
                    <br><br><br>
                    <div
                        class="rounded-box3"
                        style="width: 400px;height: 1px; border-radius: 15px; background-color: white; margin-bottom: 30px;"></div>

                    오롯이 나에게 집중한<br>
                    나만의 공간 모노가 제공합니다

                    <!-- .rounded-box { width: 200px; height: 8px; border-radius: 15px;
                    background-color: rgba(151, 130, 123, 0.8); margin: 10px auto 30px; } -->
                    <div class="images-section" style="margin-top: 100px;">
                        <img src="resources/images/main/room.png" alt="Room">
                        <img src="resources/images/main/room1.png" alt="Room1">
                        <a href="인테리어 url" style="margin-top: 280px;">인테리어 더보기</a>
                    </div>

                </div>

                <div class="room2">
                    <img src="resources/images/main/room2.jpg" alt="Room2">
                </div>
            </div>

            <div
                style="text-align: center; margin: 20px 0; margin-bottom: 90px; margin-top: 90px;">
                <!-- keyword 이미지의 가운데 정렬 -->
                <img src="resources/images/main/keyword.png" alt="Keyword Image">
            </div>

            <div
                id="bottom-set"
                style="display: flex; justify-content: center; gap: 20px; margin: 10px;">
                <img
                    src="resources/images/main/bottom1.png"
                    style="border-radius: 80px; width: 350px; height: 350px; margin-right: 5px;">
                <img
                    src="resources/images/main/bottom2.png"
                    style="border-radius: 80px; width: 350px; height: 350px;">
                <img
                    src="resources/images/main/bottom3.png"
                    style="border-radius: 80px; width: 350px; height: 350px;">
                <img
                    src="resources/images/main/bottom4.png"
                    style="border-radius: 80px; width: 350px; height: 350px;">
            </div>
		</div>
            
            <script src="resources/js/login.js"></script>
            
  
	

</body>
</html>