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

@WebServlet("/update.no")
public class NoticeUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NoticeUpdateController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (ServletFileUpload.isMultipartContent(request)) {
        	
            int maxSize = 10 * 1024 * 1024;
            String savePath = request.getServletContext().getRealPath("/resources/notice_file/");
            MultipartRequest multiRequest
            	= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MonoFileRenamePolicy());

            int noticeNo = Integer.parseInt(multiRequest.getParameter("nno"));
            String noticeTitle = multiRequest.getParameter("noticeTitle");
            String noticeContent = multiRequest.getParameter("noticeContent");

            Notice n = new Notice();
            n.setNoticeNo(noticeNo);
            n.setNoticeTitle(noticeTitle);
            n.setNoticeContent(noticeContent);

            String key = "reUpfile";
            if (multiRequest.getOriginalFileName(key) != null) {
            	
                // 새 파일이 업로드된 경우
                String originName = multiRequest.getOriginalFileName(key);
                String changeName = multiRequest.getFilesystemName(key);
                int newFileSize = (int) multiRequest.getFile(key).length();

                n.setNoticeFileName(originName);
                n.setNoticeUpdateFile(changeName);
                n.setNoticeFileSize(newFileSize);
                n.setNoticeFilePath("/resources/notice_file/");

                // 기존 파일이 있는 경우 삭제
                String existingFile = multiRequest.getParameter("noticeUpdateFile");
                
                if (existingFile != null) {
                	
                    File oldFile = new File(savePath + existingFile);
                    
                    if (oldFile.exists()) oldFile.delete();
                }
                
            } else {
            	
                // 새 파일이 없고 기존 파일 정보 유지
                Notice existingNotice = new NoticeService().selectNotice(noticeNo);
                n.setNoticeFileName(existingNotice.getNoticeFileName());
                n.setNoticeUpdateFile(existingNotice.getNoticeUpdateFile());
                n.setNoticeFileSize(existingNotice.getNoticeFileSize());
                n.setNoticeFilePath(existingNotice.getNoticeFilePath());
            }

            int result = new NoticeService().updateNotice(n);

            if (result > 0) {
                request.getSession().setAttribute("alertMsg", "게시글 수정에 성공했습니다.");
                response.sendRedirect(request.getContextPath() + "/detail.no?nno=" + noticeNo);
            } else {
                request.setAttribute("errorMsg", "게시글 수정에 실패했습니다.");
                request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
            }
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
