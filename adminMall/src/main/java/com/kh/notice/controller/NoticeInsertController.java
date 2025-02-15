package com.kh.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.common.MonoFileRenamePolicy;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10 * 1024 * 1024;
			String savePath = request.getServletContext().getRealPath("/resources/notice_file/");
			
			MultipartRequest multiRequest
				= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MonoFileRenamePolicy());
			
			String noticeTitle = multiRequest.getParameter("noticeTitle");
			String noticeContent = multiRequest.getParameter("noticeContent");
			
			Notice n = new Notice();
			
			n.setNoticeTitle(noticeTitle);
			n.setNoticeContent(noticeContent);
			
			String key = "upfile";
			if(multiRequest.getOriginalFileName(key) != null) {
				
				String noticeFileName = multiRequest.getOriginalFileName(key);
				String noticeUpdateFile = multiRequest.getFilesystemName(key);
				int fileSize = (int)multiRequest.getFile(key).length(); // 파일 크기 가져오기
				String noticeFilePath = "resources/notice_file/";
				
				n.setNoticeFileName(noticeFileName);
				n.setNoticeUpdateFile(noticeUpdateFile);
				n.setNoticeFileSize(fileSize); // 파일 크기를 바이트 단위로 저장
				n.setNoticeFilePath(noticeFilePath);
			}
			
			int result = new NoticeService().insertNotice(n);
			
			if(result > 0) {
				
				response.sendRedirect(request.getContextPath() + "/list.no?currentPage=1");
				
			} else {
				
				System.out.println("실패");
					
				}
			}
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
