<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Notice, com.kh.common.model.vo.PageInfo" %>
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	int pageLimit = pi.getPageLimit();
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
<title>MONO</title>

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

    .notice {
        width: 800px;
        margin: 100px auto; /* 화면 중앙에 배치 */
    }

    h1 {
        font-size: 26px;
        font-weight: bold;
        margin-bottom: 40px;
    }

    .notice-list {
        list-style: none;
    }

    .notice-list div {
        color: #333;
        font-size: 15px;
        list-style: none;
        display: block; /* 링크를 블록 요소로 변경해 줄 전체가 클릭 가능 */
        padding: 20px 0;
        border-bottom: 1px solid #ddd;
        cursor: pointer;
    }

    .notice-list div:hover {
        color: #999; /* 텍스트 색상 변경 */
    }

    .notice-list p {
        font-size: 15px;
        font-weight: 300;
        margin-bottom: 15px;
    }

    .notice-list span {
        font-size: 14px;
        color: #999;
        font-weight: 300;
        display: block;
    }
    
    /* 페이징바 스타일 */
    .paging-bar {
	    display: flex;
	    justify-content: center;
	    list-style: none;
	    padding: 0;
	    margin: 20px 0;
	}
	
	.active, .non-active {
	    margin: 0px 8px;
	    display: inline-block;
	    padding: 1px 10px;
	    border: none;
	    background-color: white;
	    cursor: pointer;
	    color: #999;
        font-weight: 300;
        font-size: 18px;
	}
	
	.prev-next {
		margin: 0px 8px;
	    display: inline-block;
	    padding: 1px 10px;
	    border: none;
	    background-color: white;
	    cursor: pointer;
	    color: black;
        font-weight: 300;
        font-size: 18px;
	}
	
	.active:hover, .non-active:hover {
	    color: #222;
        border-bottom: 1px solid #333;
	}
	
	.paging-bar .active {
	    font-weight: bold;
	    color: black;
	}

</style>
</head>
<body>

	<%@ include file="../common/header.jsp" %>
	
	<!-- 공지사항 리스트 -->
	<div class="notice">
	    <h1>공지사항</h1>
	    
    	<!-- 추후에 관리자만 보이도록 변경 
	    <div align="right" style="width : 800px;">
			<a href="<%= contextPath %>/enrollForm.no" 
			   class="btn btn-sm btn-secondary">
				글작성
			</a>
		</div>
			-->
	    <ul class="notice-list">
	    
	    	<% for(Notice n : list) { %>
		        <li>
				    <div data-nno="<%= n.getNoticeNo() %>"> <!-- 번호를 data 속성으로 숨김 -->
				        <p><%= n.getNoticeTitle() %></p>
				        <span><%= n.getNoticeCreateAt() %></span>
				    </div>
				</li>
		    <% } %>
		    
	    </ul>
	</div>
	
	<!-- 진짜 김다훈이 존나 힘들게 만든 페이징바 -->
	<div align="center" class="paging-bar">

	    <% if(currentPage > pageLimit) { %> <!-- 페이징바의 첫번째 구간 (1~5)일 때 << 버튼 표시 안 함 -->
	        <button onclick="location.href = '<%= contextPath %>/list.no?currentPage=1';" class="prev-next">
	            &lt;&lt;
	        </button>
	    <% } %>
	
	    <% if(currentPage > pageLimit) { %> <!-- 이전 구간의 마지막 페이지로 이동 -->
	        <button onclick="location.href = '<%= contextPath %>/list.no?currentPage=<%= (startPage - 1) %>';" class="prev-next">
	            &lt;
	        </button>
	    <% } %>
	
	    <% for(int p = startPage; p <= endPage; p++) { %>
	        <% if(p != currentPage) { %>
	            <button onclick="location.href = '<%= contextPath %>/list.no?currentPage=<%= p %>';" class="non-active">
	                <%= p %>
	            </button>
	        <% } else { %>
	            <button class="active">
	                <%= p %>
	            </button>
	        <% } %>
	    <% } %>
	
	    <% if(currentPage < maxPage && endPage < maxPage) { %> <!-- 마지막 구간에서만 >와 >> 버튼을 숨김 -->
	        <button onclick="location.href = '<%= contextPath %>/list.no?currentPage=<%= (endPage + 1) %>';" class="prev-next">
	            &gt;
	        </button>
	        
	        <button onclick="location.href = '<%= contextPath %>/list.no?currentPage=<%= maxPage %>';" class="prev-next">
	            &gt;&gt;
	        </button>
	    <% } %>
	
	</div>


	
	<!-- 클릭 시 상세조회 페이지로 이동. 근데 안됨 씨발진짜 언디파인드래씨빨 -->
	<!-- 해결완료! url 은 제대로 처리되는듯 하지만 흰화면만 뜸ㅠ jsp나 컨트롤러 문제일수도잇구 DB 문제일수도잇구.. 좃같네-->
	<script>
		$(function() {
			
			$(".notice-list>li>div").click(function() {
				
				let nno = $(this).data("nno");
				
				location.href = "<%= contextPath %>/detail.no?nno=" + nno;
				
			});
			
		});
	</script>
	
	<%@ include file="../common/footer.jsp" %>

</body>
</html>