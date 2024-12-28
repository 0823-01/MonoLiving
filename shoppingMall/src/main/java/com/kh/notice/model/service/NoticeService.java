package com.kh.notice.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;


public class NoticeService {
	
	/**
	 * 2024.10.22
	 * 공지사항 전체 갯수 조회용 서비스 메소드
	 * @return => 공지사항 게시글의 총 갯수
	 */
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	/**
	 * 2024.10.22
	 * 공지사항 리스트 조회용 서비스 메소드
	 * @return
	 */
	public ArrayList<Notice> selectNoticeList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 2024.10.23
	 * 공지사항 조회수 증가용 서비스 메소드
	 * @param noticeNo => 조회수를 증가시킬 게시글 번호
	 * @return
	 */
	public int increaseCount(int noticeNo) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().increaseCount(conn, noticeNo);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	/**
	 * 2024.10.23
	 * 공지사항 상세조회 서비스용 메소드
	 * @param noticeNo
	 * @return
	 */
	public Notice selectNotice(int noticeNo) {
		
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn, noticeNo);
		
		close(conn);
		
		return n;
	}
	
	/**
	 * 2024.10.24
	 * 공지사항 작성용 서비스 메소드
	 * @param n
	 * @return
	 */
	public int insertNotice(Notice n) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().insertNotice(conn, n);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	/**
	 * 2024.10.26
	 * 공지사항 수정 서비스용 메소드
	 * @param n
	 * @return
	 */
	public int updateNotice(Notice n) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().updateNotice(conn, n);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	/**
	 * 2024.10.28
	 * 공지사항 삭제 서비스용 메소드
	 * @param noticeNo
	 * @return
	 */
	public int deleteNotice(int noticeNo) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteNotice(conn, noticeNo);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
}
