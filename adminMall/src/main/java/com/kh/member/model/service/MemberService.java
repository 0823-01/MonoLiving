package com.kh.member.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.PageInfo;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	
	
	/**
	 * 전체 회원수 조회 서비스 메소드
	 * @return
	 */
	public int selectMemberCount() {
		
		Connection conn = getConnection();
		
		int memberCount = new MemberDao().selectMemberCount(conn);
		
		close(conn);
		
		return memberCount;
		
	}  // selectMemberCount 메소드 영역 끝
	
	
	
	

	/**
	 * 회원 리스트 조회 서비스 메소드
	 * @param pi
	 * @return
	 */
	public ArrayList<Member> selectMemberList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Member> list = new MemberDao().selectMemberList(conn, pi);
		
		close(conn);
		
		return list;
		
	}   //  selectMemberList 메소드 영역 끝





	/**
	 * 회원 정보 상세보기 서비스 메소드
	 * @param userNo
	 * @return
	 */
	public Member selectMemberDetail(int userNo) {
		
		Connection conn = getConnection();
		
		Member m = new MemberDao().seelctMemberDetail(conn, userNo);
		
		close(conn);
		
		return m;
	}  // selectMemberDetail 메소드 영역 끝





	/**
	 * 회원 강제 탈퇴(삭제)용 서비스 메소드
	 * @param userNo
	 * @return
	 */
	public int deleteMember(int userNo) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn, userNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}  // deleteMember 메소드 영역 끝

	
	
	
	


}
