<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <!-- jQuery 온라인 방식 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

<h1>AJAX 개요</h1>

<p>
    Asynchronous Javascript And XML 의 약자로...
    <!-- 설명 생략 -->
</p>

<pre>
    <!-- jQuery AJAX 설명 -->
</pre>

<hr>

<h1> jQuery 라이브러리를 이용한 ajax 테스트</h1>

<h3> 1. 버튼 클릭시 get 방식으로 서버에 데이터 전송 및 응답</h3>
입력: <input type="text" id="input1">
<button type="button" id="btn1">전송</button>
<br>
응답: <label id="output1">현재응답없음</label>

<script>
    $(function() {
        $("#btn1").click(function() {
            $.ajax({
                url: "/ajax/jqAjax1.do",
                type: "get",
                data: {input: $("#input1").val()},
                success: function(result) {
                    $("#output1").text(result);
                },
                error: function() {
                    console.log("ajax 통신 실패!");
                },
                complete: function() {
                    console.log("ajax 통신 성공 여부와 상관 없이 호출");
                }
            });
        });
    });
</script>

<hr>

<h3> 2. 버튼 클릭시 post 방식으로 서버에 데이터 전송 및 응답</h3>
이름: <input type="text" id="input2_1"><br>
나이: <input type="number" id="input2_2"><br>
<button type="button" onclick="test2();">전송</button>
<br>
응답: <label id="output2">현재 응답 없음</label>

<script>
    function test2() {
        let name = $("#input2_1").val();
        let age = $("#input2_2").val();
        $.ajax({
            url: "/ajax/jqAjax2.do",
            type: "post",
            data: {
                name: name,
                age: age
            },
            success: function(result) {
                $("#output2").text("이름: " + result.name + ", 나이: " + result.age);
                $("#input2_1").val("");
            },
            error: function() {
                console.log("ajax 통신 실패");
            }
        });
    }
</script>

<hr>

<h3> 3. 서버로 데이터 전송 후, 조회된 "객체"를 응답데이터로 받기</h3>
회원번호 입력: <input type="number" id="input3">
<button onclick="test3()">조회</button>
<div id="output3"></div>

<script>
    function test3() {
        let userNo = $("#input3").val();
        $.ajax({
            url: "/ajax/jqAjax3.do",
            type: "get",
            data: {userNo: userNo},
            success: function(response) {
                let resultStr = "회원번호: " + response.userNo + "<br>" +
                                "이름: " + response.userName + "<br>" +
                                "나이: " + response.age + "<br>" +
                                "성별: " + response.gender;
                $("#output3").html(resultStr);
            }
,
            error: function() {
                console.log("ajax 통신 실패");
            }
        });
    }
</script>

	<br>
	<hr>
	<h3> 4. 응답데이터로 여러개의 객체들이 담겨있는 ArrayList 받기</h3>
	<!-- 
		여러행 조회일 경우
		-DAO에서 
		ArrayList<Member> list = new ArrayList<>();
		...
		
		rset = pstmt.executeQuery();
		while(rset.next()){
		Member m = new Member(x,x,x);
		list.add(m);
		}
	 -->
		<button onclick = "test4();">회원전체 조회</button>
		<table id="output4" border="1" style="text-align: center;">
				<thead>
				
				<tr>
				
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
						
						
				</tr>
				</thead>
				<tbody>
				
				</tbody>
				
				
		</table>
		
		<script>
			function test4(){
				
				$.ajax({
					
					url : "/ajax/jqAjax4.do",
					type: "post",
					success : function(result){
						//console.log(result);
						
						let = resultStr = "";
						for(let i = 0; i< result.length; i++){
							
							//console.log(result[i]);
							resultStr += "<tr>"
							+"<td>"+result[i].userNo+"</td>"
							+"<td>"+result[i].userName+"</td>"
							+"<td>"+result[i].age+"</td>"
							+"<td>"+result[i].gender+"</td>"

							+"</tr>";
						}
						
						$("#output4 tbody").html(resultStr);
						
					},
					error : function(){
						console.log("실패")
					}
					
				})
			}
		</script>

</body>
</html>


<br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>