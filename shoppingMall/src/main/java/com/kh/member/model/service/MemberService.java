package com.kh.member.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {

    /**
     * 회원 정보 수정 서비스 메소드
     * @param m
     * @return 업데이트된 회원 정보 (성공 시) 또는 null (실패 시)
     */
    public Member updateMember(Member m) {
        Connection conn = getConnection();
        
        // userId를 기준으로 회원 정보 수정
        int result = new MemberDao().updateMember(conn, m);
        
        Member updateMem = null;
        
        if (result > 0) {
            commit(conn);
            
            // 갱신된 회원의 정보를 다시 조회해 와서 session에 다시 덮어씌울 것
            updateMem = new MemberDao().selectMember(conn, m.getUserId());
        } else {
            rollback(conn);
        }
        
        close(conn); // Connection 객체 반납
        return updateMem;
    }
    
    /**
     * 로그인 요청 서비스 메소드 
     * @param m => 사용자가 입력한 아이디, 비밀번호 값 
     * @return => 로그인 할 사용자 1명의 정보
     */
    public Member loginMember(Member m) {
        Connection conn = getConnection();
        Member loginUser = new MemberDao().loginMember(conn, m);
        close(conn);
        return loginUser;
    }
    
    /**
     * 회원 가입 요청 서비스 메소드 
     * @param m => 회원 가입할 회원의 정보들 
     * @return => 처리된 행의 갯수(int)
     */
    public int insertMember(Member m) {
        Connection conn = getConnection();
        int result = new MemberDao().insertMember(conn, m);
        
        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        
        close(conn);
        return result;
    }
    
    /**
     * 아이디 중복 체크 서비스 메소드
     * @param checkId => 중복 체크할 아이디
     * @return => 현재 사용중인 아이디의 갯수
     */
    public int idCheck(String checkId) {
        Connection conn = getConnection();
        int count = new MemberDao().idCheck(conn, checkId);
        close(conn);
        return count;
    }
    
    /**
     * 비밀번호 찾기 서비스 메소드
     * @param userName => 사용자의 이름
     * @param email => 사용자의 이메일
     * @param userId => 사용자의 아이디
     * @return => 사용자의 비밀번호
     */
    public String findPassword(String userName, String email, String userId) {
        Connection conn = JDBCTemplate.getConnection(); // Connection 객체 생성
        String userPwd = new MemberDao().findPassword(conn, userName, email, userId); // DAO 호출
        
        JDBCTemplate.close(conn); // Connection 객체 반납
        return userPwd; // 비밀번호 반환
        
    }

	/**
	 * 회원 탈퇴 서비스 메소드
	 * @param m
	 * @return
	 */
	public int deleteMember(Member m) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn, m);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	   public String findId(String userName, String email) {
	        Connection conn = JDBCTemplate.getConnection(); // Connection 객체 생성
	        String userId = new MemberDao().findId(conn, userName, email); // DAO 호출
	        
	        JDBCTemplate.close(conn); // Connection 객체 반납
	        return userId; // 비밀번호 반환
	        
	    }
}
