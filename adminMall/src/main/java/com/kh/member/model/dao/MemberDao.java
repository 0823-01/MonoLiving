package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import static com.kh.common.JDBCTemplate.*;

import com.kh.common.model.vo.PageInfo;
import com.kh.member.model.vo.Member;
import com.kh.product.model.vo.ProductInfo;

public class MemberDao {
	
	 private Properties prop = new Properties();

	    public MemberDao() {
	        // member-mapper.xml 파일로부터 query문들을 key + value 세트로 읽어서 담아둘 것 
	        String path = "/sql/member/member-mapper.xml";
	        String fileName = MemberDao.class.getResource(path).getPath();
	        try {
	            prop.loadFromXML(new FileInputStream(fileName));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    
		/**
		 * 전체 회원수 조회용 쿼리문 실행 메소드
		 * @param conn
		 * @return
		 */
		public int selectMemberCount(Connection conn) {
			
			int memberCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectMemberCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					memberCount = rset.getInt("COUNT");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			return memberCount;
			
		}  // selectMemberCount 메소드 영역 끝



		
		/**
		 * 회원 목록 조회용 쿼리문 실행 메소드
		 * @param conn
		 * @param pi
		 * @return
		 */
		public ArrayList<Member> selectMemberList(Connection conn, PageInfo pi) {
			
			ArrayList<Member> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectMemberList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage() - 1) * pi.getProductLimit() + 1;
				int endRow = startRow + pi.getProductLimit() - 1;
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Member m = new Member(rset.getInt("USER_NO"),
										 (rset.getString("USER_ID")),
										 (rset.getString("EMAIL")),
										 (rset.getDate("ENROLL_DATE")),
										 (rset.getString("STATUS")));
					list.add(m);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			return list;
			
		}  // selectMemberList 메소드 영역 끝



		/**
		 * 회원 정보 상세보기 조회용 쿼리문 실행 메소드
		 * @param conn
		 * @param userNo
		 * @return
		 */
		public Member seelctMemberDetail(Connection conn, int userNo) {
			
			Member m = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("seelctMemberDetail");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, userNo);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					
					m = new Member();
					
					m.setUserNo(rset.getInt("USER_NO"));
					m.setUserId(rset.getString("USER_ID"));
					m.setUserName(rset.getString("USER_NAME"));
					m.setGender(rset.getString("GENDER"));
					m.setUserBirth(rset.getString("USER_BIRTH"));
					m.setEmail(rset.getString("EMAIL"));
					m.setPhone(rset.getString("PHONE"));
					m.setAddress(rset.getString("ADDRESS"));
					m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return m;
			
		}  // seelctMemberDetail 메소드 영역 끝



		/**
		 * 회원 삭제용 쿼리문 실행 메소드
		 * @param conn
		 * @param userNo
		 * @return
		 */
		public int deleteMember(Connection conn, int userNo) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("deleteMember");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, userNo);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
			
			
		}  // deleteMember 메소드 영역 끝

}
