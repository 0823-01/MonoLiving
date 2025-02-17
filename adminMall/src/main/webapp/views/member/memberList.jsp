<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.common.model.vo.PageInfo
				, com.kh.member.model.vo.Member" %>   
<%
	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	// 페이징바 관련된 변수만 따로 셋팅하기
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        body { font-family: Arial, sans-serif; }
        h2 { font-size: 24px; margin-bottom: 10px; }
        .container { width: 80%; margin: auto; }
        .search-bar { text-align: right; margin-bottom: 10px; }
        .search-bar input[type="text"] { width: 200px; padding: 5px; }
        .search-bar button { padding: 5px 10px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 10px; text-align: center; border: 1px solid #ddd; }
        th { background-color: #f4f4f4; }
        .delete-button { background-color: #e74c3c; color: white; padding: 5px 10px; border: none; cursor: pointer; }
        .delete-button:hover { background-color: #c0392b; }
        
            /* 테이블 행 스타일 */
		    tbody tr {
		        transition: background-color 0.3s ease; /* 배경색 전환 효과 */
		    }
		
		    /* 호버 효과 */
		    tbody tr:hover {
		        background-color: #f0f0f0; /* 호버 시 배경색 */
		    }
		
		    /* 클릭할 수 있다는 시각적 단서 */
		    tbody tr {
		        cursor: pointer; /* 마우스 커서 변경 */
		    }
    </style>
</head>
<body>


	<%@ include file="../common/nav.jsp" %>

	<div id="layoutSidenav_content">
       <main>
       <br>
       
                <div class="container">
                <h2>회원 관리</h2>
                <p>회원의 권한을 변경하거나 계정을 삭제할 수 있습니다.</p>
                <div class="search-bar">
                    <input type="text" placeholder="검색">
                    <button>🔍</button>
                </div>
                <table class="memberList">
                    <thead>
                        <tr>
                            <th>회원 번호</th>
                            <th>회원 아이디</th>
                            <th>회원 E-mail</th>
                            <th>계정 생성일</th>
                            <th>회원 상태 여부</th>
                            <th>회원 계정 삭제</th>
                        </tr>
                    </thead>
                    <tbody>
                     <% if (list != null) { %>
				        <% for (Member m : list) { %>
				            <tr data-mno="<%= m.getUserNo() %>" style="cursor:pointer;">
				                <td><%= m.getUserNo() %></td>
				                <td><%= m.getUserId() %></td>
				                <td><%= m.getEmail() %></td>
				                <td><%= m.getEnrollDate() %></td>
				                <td><%= m.getStatus() %></td>
				                <td>
				                	 <% if ("Y".equals(m.getStatus())) { %>
				                        <button class="delete-button" onclick="deleteMember('<%= m.getUserNo() %>'); return false;">강제 탈퇴</button>
				                    <% } %>
				                </td>
				            </tr>
				        <% } %>
				    <% } else { %>
				        <tr>
				            <td colspan="6">회원 정보가 없습니다.</td>
				        </tr>
				    <% } %>
				    </tbody>
                </table>
            </div>

       </main>
    </div>
    <script>
       
        
        
        $(function() {
			
			$(".memberList>tbody>tr[data-mno]").click(function() {
				
				
				 let mno = $(this).data("mno");
			        console.log("회원 번호:", mno); // 상품 번호 확인
				
				location.href = "<%= contextPath %>/member-detail.me?mno=" + mno;
				
				
			});
			
		});
        
        function deleteMember(userNo) {
            console.log(userNo);
            if (confirm("해당 회원을 탈퇴시키겠습니까?")) {
                // 회원 탈퇴 요청
                $.ajax({
                    url: "<%= contextPath %>/deleteMember.me",
                    method: "POST",
                    data: { userNo: userNo },
                    success: function(response) {
                        console.log(response); // 응답을 콘솔에 출력해 확인
                        if (response.status === "success") {
                            // 탈퇴 성공 시 회원 관리 페이지로 리다이렉트
                            location.href = "<%= contextPath %>/member-managment.me";
                        } else {
                            alert("탈퇴 중 오류가 발생했습니다.");
                        } // 여기까지 success의 if-else 블록
                    }, // success 콜백 종료
                    error: function(xhr, status, error) {
                        alert("탈퇴 중 오류가 발생했습니다.");
                    }
                }); // AJAX 요청 종료
            } // confirm 블록 종료
        }

        
        
    </script>

</body>
</html>