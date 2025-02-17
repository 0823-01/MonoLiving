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

    #update-form {
        width: 800px; /* 전체 가로 사이즈 조정 */
        margin: 100px auto;
        padding: 20px;
        border: 0px solid #ccc; /*테두리@@@*/
    }

    .noticeTitle {
        font-size: 26px;
        font-weight: bold;
        margin-bottom: 5px;
        width: 100%;
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

    .noticeContent {
        font-size: 16px;
        line-height: 1.6;
        margin-bottom: 50px;
        padding-bottom: 50px;
        border-bottom: 1px solid #ddd;
    }

    textarea {
        resize: none;
        width: 100%;
        height: 500px;
    }
    
    .noticeAtta {
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
    
    .deleteFile {
    	border: none;
    	background-color: #F7F7F7;
    	margin-top: 10px;
    }

    .noticeBtns {
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    text-align: center;
	    padding: 10px 0;
	}
	
	.noticeBtn, .btn-upload {
	    background-color: #000;
	    color: #fff;
	    padding: 10px 50px;
	    margin: 0 5px;
	    border: none;
	    border-radius: 60px;
	    font-size: 16px;
	    cursor: pointer;
	    text-align: center;
	}
	
	#upfileInput {
	    display: none;
	}

	.noticeBtn:hover, .btn-upload:hover {
        background-color: #333;
    }
    
    </style>

</head>
<body>

	<%@ include file="../common/nav.jsp" %>
	
	<div id="layoutSidenav_content">
		<main>
		
			<form id="update-form" action="<%= contextPath %>/update.no" method="post"
		        enctype="multipart/form-data">
		        
				<input type="hidden" name="nno" value="<%= n.getNoticeNo() %>">
				
			    <input type=text name="noticeTitle" class="noticeTitle" value="<%= n.getNoticeTitle() %>" required>
		
			    <div class="noticeDate-view"><!-- 제목과 내용 사이 절개선 --></div>
		
			    <div class="noticeContent">
					<textarea name="noticeContent" id="noticeContent" required><%= n.getNoticeContent() %></textarea>
			    </div>
			    
				<div class="noticeAtta" style="<%= n.getNoticeFileName() != null ? "" : "display: none;" %>">
					<div id="filePreview">
						<p class="filename"><%= n.getNoticeFileName() != null ? n.getNoticeFileName() : "" %></p>
						<input type="hidden" name="noticeUpdateFile" value="<%= n.getNoticeUpdateFile() %>">
						
		            	<p class="filebyte"><%= n.getNoticeFileSize() != 0 ? String.format("%.2f KB", n.getNoticeFileSize() / 1024.0) : "" %></p>
						<input type="hidden" name="noticeFileSize" value="<%= n.getNoticeFileSize() %>">
						
						<button class="deleteFile" type="button" id="fileRemoveBtn">삭제</button>
						<input type="hidden" name="deleteFile" id="deleteFile" value="false">
					</div>
		    	</div>
			
			    <div class="noticeBtns">
				    <label for="upfileInput">
				        <button type="submit" class="noticeBtn">수정하기</button>
				    </label>
				    
				    <label for="upfileInput">
						<div class="btn-upload">첨부파일등록</div>
					</label>
					<input type="file" name="reUpfile" id="upfileInput" multiple>
					
					<label for="upfileInput">
						<button type="reset" class="noticeBtn">초기화</button>
					</label>
			    </div>
			</form>
		</main>
	</div>

	<script>
	    $(document).ready(function() {
	
	        // 📌 새 파일 선택 시 미리보기 갱신 (파일 삭제 후에도 정상적으로 표시)
	        $('#upfileInput').change(function() {
	            var file = this.files[0];
	            if (file) {
	                var fileSizeKB = (file.size / 1024).toFixed(2) + ' KB';
	                $('.noticeAtta .filename').text(file.name);
	                $('.noticeAtta .filebyte').text(fileSizeKB);
	
	                // 기존 파일이 삭제된 상태라면 noticeAtta 전체를 다시 보이도록 처리
	                $('.noticeAtta').show();
	                $("#deleteFile").val("false"); // 새 파일 선택 시 삭제 상태 해제
	            }
	        });
	
	        // 📌 첨부 파일 삭제 버튼 클릭 시
	        $("#fileRemoveBtn").click(function() {
	            if (confirm("첨부 파일을 삭제하시겠습니까?")) {
	                $(".noticeAtta").hide(); // 파일 미리보기 숨기기
	                $("#deleteFile").val("true"); // 서버에 파일 삭제 요청 전달
	                
	                // 추가: input[type=file] 초기화 (새 파일 선택 시 리셋 문제 방지)
	                $("#upfileInput").val("");
	            }
	        });
	
	    });
	</script>

</body>
</html>