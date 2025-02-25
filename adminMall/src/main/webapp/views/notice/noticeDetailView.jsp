<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.notice.model.vo.Notice" %>
<% 
	Notice n = (Notice)request.getAttribute("n");
%>
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

    .noticeDetail {
        width: 800px; /* 전체 가로 사이즈 조정 */
        margin: 100px auto;
        padding: 20px;
        border: 0px solid #ccc; /*테두리@@@*/
    }

    .noticeTitle {
        font-size: 26px;
        font-weight: bold;
        margin-bottom: 5px;
    }

    .noticeDate-view {
        display: flex;
        justify-content: space-between;
        margin-bottom: 30px;
        padding: 20px 0;
        border-bottom: 1px solid #ddd;
    }

    .date, .views {
        font-size: 14px;
        color: #666;
    }

    .content {
        font-size: 16px;
        line-height: 1.6;
        margin-bottom: 30px;
        padding-bottom: 50px;
        border-bottom: 1px solid #ddd;
    }
    
    .attachment {
        background-color: #F7F7F7;
        font-size: 16px;
        line-height: 1.6;
        margin-bottom: 30px;
        padding: 20px;
    }

    .filename {
        font-size: 15px;
        margin: 0;
        padding: 0;
        line-height: 1.4;
    }

    .filebyte {
        font-size: 11px;
        color: #888;
        display: block;
        margin: 0;
    }

    .fileDown {
    	text-decoration: none;
    	color: #444;
    }
    
    .fileDown:hover {
    	color: #444;
    }

    .button-noticeList {
        text-align: center;
        margin: 0px 5px;
    }

    .back-button {
        background-color: #000;
        color: #fff;
        padding: 10px 50px;
        border: none;
        border-radius: 60px;
        font-size: 16px;
        text-decoration: none;
    }

    .back-button:hover {
        background-color: #333;
        text-decoration: none;
        color: #fff;
    }
    
    .button-container {
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    text-align: center;
	    padding: 10px 0;

    </style>

</head>
<body>

	<%@ include file="../common/nav.jsp" %>
	
	<div id="layoutSidenav_content">
       <main>
	
			<div class="noticeDetail">
			
				<input type="hidden" name="nno" value="<%= n.getNoticeNo() %>">
			
			    <h1 class="noticeTitle"><%= n.getNoticeTitle() %></h1>
			    
			    <div class="noticeDate-view">
			        <span class="date"><%= n.getNoticeCreateAt() %></span>
			        <span class="views">조회수 <%= n.getNoticeViewCount() %></span>
			    </div>
			    
			    <div class="content">
			        <%= n.getNoticeContent() %>
			    </div>
			    
			    <% if(n.getNoticeFilePath() != null) { %>
			    	<a class="fileDown" download="<%= n.getNoticeFileName() %>" href="<%= contextPath %>/<%= n.getNoticeFilePath() + n.getNoticeUpdateFile() %>">	
				    	<div class="attachment">
				            <p class="filename"><%= n.getNoticeFileName() %></p>
				            <p class="filebyte">
							    <%= n.getNoticeFileSize() != 0 ? String.format("%.2f KB", n.getNoticeFileSize() / 1024.0) : "파일 없음" %>
							</p>
			        	</div>
			        </a>
			    <% } %>
			    
			    <div class="button-container">
			    
			    	<div class="button-noticeList">
			        <a href="<%= contextPath %>/list.no" class="back-button">목록으로</a>
				    </div>
				    
				    <div class="button-noticeList">
				        <a href="<%= contextPath %>/updateForm.no?nno=<%= n.getNoticeNo() %>" class="back-button">수정하기</a>
				    </div>
				    <div class="button-noticeList">
					    <a href="javascript:void(0);" class="back-button" onclick="confirmDelete('<%= contextPath %>/delete.no?nno=<%= n.getNoticeNo() %>')">삭제하기</a>
					</div>
				    
			    </div>
			</div>
		</main>
	</div>
	
	<script>
	
	    function confirmDelete(url) {
	        if (confirm("해당 공지사항을 삭제하시겠습니까?")) {
	            location.href = url;
	        }
	    }
	    
	</script>
</body>
</html>