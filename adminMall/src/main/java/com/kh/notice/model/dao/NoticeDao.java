package com.kh.notice.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.model.vo.PageInfo2;
import com.kh.notice.model.vo.Notice;


public class NoticeDao {
	
	private Properties prop = new Properties();

		// 공통코드 - 쿼리문들을 키 + 밸류 세트로 불러오기
		public NoticeDao() {
		
		String path = "/sql/notice/notice-mapper.xml";
		
		String fileName = NoticeDao.class.getResource(path).getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * 2024.10.22
	 * 공지사항 리스트 갯수를 구하는 쿼리문 실행용 메소드
	 * @param conn
	 * @return
	 */
	public int selectListCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	/**
	 * 2024.10.22
	 * 공시사항 리스트 조회용 쿼리문 실행 메소드
	 * @param conn
	 * @param pi => 구간별로 끊을 때 필요한 변수
	 * @return
	 */
	public ArrayList<Notice> selectNoticeList(Connection conn, PageInfo2 pi) {
		
		ArrayList<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Notice(rset.getInt("NOTICE_NO"),
									rset.getString("NOTICE_TITLE"),
									rset.getString("NOTICE_CREATE_AT")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int increaseCount(Connection conn, int noticeNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 2024.10.23
	 * 공지사항 상세조회용 쿼리문 실행 메소드
	 * @param conn
	 * @param noticeNo
	 * @return
	 */
	public Notice selectNotice(Connection conn, int noticeNo) {
		
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNotice");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				n = new Notice();
				
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				n.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				n.setNoticeViewCount(rset.getInt("NOTICE_VIEW_COUNT"));
				n.setNoticeCreateAt(rset.getString("NOTICE_CREATE_AT"));
				n.setNoticeFileName(rset.getString("NOTICE_FILE_NAME"));
				n.setNoticeUpdateFile(rset.getString("NOTICE_UPDATE_FILE"));
				n.setNoticeFileSize(rset.getInt("NOTICE_FILE_SIZE"));
				n.setNoticeFilePath(rset.getString("NOTICE_FILE_PATH"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return n;
	}
	
	/**
	 * 2024.10.25
	 * 공지사항 작성용 쿼리문 실행 메소드
	 * @param conn
	 * @param n
	 * @return
	 */
	public int insertNotice(Connection conn, Notice n) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNotice");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getNoticeFileName());
			pstmt.setString(4, n.getNoticeUpdateFile());
			pstmt.setInt(5, n.getNoticeFileSize());
			pstmt.setString(6, n.getNoticeFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 2024.10.26
	 * 공지사항 수정용 쿼리문 실행 메소드
	 * @param conn
	 * @param n
	 * @return
	 */
	public int updateNotice(Connection conn, Notice n) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateNotice");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());

			// 파일 정보가 NULL이면 null 바인딩, 그렇지 않으면 값 바인딩
			pstmt.setString(3, n.getNoticeFileName() != null ? n.getNoticeFileName() : null);
			pstmt.setString(4, n.getNoticeFileName());
			pstmt.setString(5, n.getNoticeUpdateFile() != null ? n.getNoticeUpdateFile() : null);
			pstmt.setString(6, n.getNoticeUpdateFile());
			pstmt.setInt(7, n.getNoticeFileSize());
			pstmt.setInt(8, n.getNoticeFileSize() != 0 ? n.getNoticeFileSize() : 0);
			pstmt.setString(9, n.getNoticeFilePath() != null ? n.getNoticeFilePath() : null);
			pstmt.setString(10, n.getNoticeFilePath());

			// WHERE 절
			pstmt.setInt(11, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 2024.10.28
	 * 공지사항 삭제용 쿼리문 실행 메소드
	 * @param conn
	 * @param noticeNo
	 * @return
	 */
	public int deleteNotice(Connection conn, int noticeNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteNotice");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
}
