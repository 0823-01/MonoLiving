package com.kh.member.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.vo.Member;

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


    /** 로그인 쿼리문 실행용 메소드
     * @param conn -> db접속용 객체
     * @param m -> 로그인할 사용자의 정보가 담긴 객체
     * @return -> 로그인할 회원 1명의 정보 
     */
    public Member loginMember(Connection conn, Member m) {
        Member loginUser = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        // 실행할 sql문 
        String sql = prop.getProperty("loginMember");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m.getUserId());
            pstmt.setString(2, m.getUserPwd());
            rset = pstmt.executeQuery();

            // 조회된 데이터를 Member 객체로 옮기기
            if (rset.next()) {
                loginUser = new Member(rset.getInt("USER_NO"),
                                       rset.getString("USER_ID"),
                                       rset.getString("USER_PWD"),
                                       rset.getString("USER_NAME"),
                                       rset.getString("GENDER"),
                                       rset.getString("USER_BIRTH"),
                                       rset.getString("EMAIL"),
                                       rset.getString("PHONE"),
                                       rset.getString("ADDRESS"),
                                       rset.getDate("ENROLL_DATE"),
                                       rset.getString("STATUS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return loginUser;
    }

    /** 회원가입 쿼리문 실행용 메소드 
     * @param conn => db접속용 객체 
     * @param m => 회원가입할 회원의 정보
     * @return => 처리된 행의 갯수
     */
    public int insertMember(Connection conn, Member m) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("insertMember");

        try {
            pstmt = conn.prepareStatement(sql);

     
            pstmt.setString(1, m.getUserId()); // USER_ID
            pstmt.setString(2, m.getUserPwd()); // USER_PWD
            pstmt.setString(3, m.getUserName()); // USER_NAME
            pstmt.setString(4, m.getGender()); // GENDER
            pstmt.setString(5, m.getUserBirth()); // USER_BIRTH
            pstmt.setString(6, m.getEmail()); // EMAIL
            pstmt.setString(7, m.getPhone()); // PHONE
            pstmt.setString(8, m.getAddress()); // ADDRESS
    

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }


    /** 회원 한 명의 정보 조회용 쿼리문 실행 메소드
     * 예원이가 수정함 이걸로 옮기기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * @param conn => DB접속용 객체
     * @param userId => 조회할 회원의 아이디
     * @return => 조회된 회원 한명의 정보
     */
    public Member selectMember(Connection conn, String userId) {
        Member m = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        // 실행할 sql문
        String sql = prop.getProperty("selectMember");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rset = pstmt.executeQuery();

            // 조회된 데이터를 Member 객체로 옮기기
            if (rset.next()) {
                m = new Member();
                m.setUserNo(rset.getInt("USER_NO"));
                m.setUserId(rset.getString("USER_ID"));
                m.setUserPwd(rset.getString("USER_PWD"));
                m.setUserName(rset.getString("USER_NAME"));
                m.setGender(rset.getString("GENDER"));
                m.setUserBirth(rset.getString("USER_BIRTH"));
                m.setEmail(rset.getString("EMAIL"));
                m.setPhone(rset.getString("PHONE"));
                m.setAddress(rset.getString("ADDRESS"));
                m.setEnrollDate(rset.getDate("ENROLL_DATE"));
                m.setStatus(rset.getString("STATUS"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        
        return m;
    }

    /** 비밀번호 변경용 쿼리문 실행 메소드 
     * @param conn => DB접속용 객체
     * @param hm => 비밀번호를 변경할 회원의 정보
     * @return => 처리된 행의 갯수 
     */
    public int updatePwdMember(Connection conn, HashMap<String, String> hm) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updatePwdMember");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, hm.get("updatePwd"));
            pstmt.setString(2, hm.get("userId"));
            pstmt.setString(3, hm.get("userPwd"));

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           close(pstmt);
        }
        return result;
    }


    /** 아이디 중복 체크용 쿼리문 실행
     * @param conn => DB접속용 객체 
     * @param checkId => 중복 체크할 아이디
     * @return => 현재 사용중인 아이디의 갯수 
     */
    public int idCheck(Connection conn, String checkId) {
        int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("idCheck");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, checkId);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                count = rset.getInt("COUNT(*)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return count;
    }
    
    public String findPassword(Connection conn, String userName, String email, String userId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String userPwd = null;
        String sql = prop.getProperty("pwFind");
        
        
        try {
            // SQL 쿼리 작성
            System.out.println("Query: " + sql);
            System.out.println("Parameters: userName=" + userName + ", email=" + email + ", userId=" + userId);

            // PreparedStatement 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, email);
            pstmt.setString(3, userId);
            	
            // 쿼리 실행
            rs = pstmt.executeQuery();
            
            // 결과 처리
            if (rs.next()) {
                userPwd = rs.getString("USER_PWD");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 자원 해제
            JDBCTemplate.close(rs);
            JDBCTemplate.close(pstmt);
        }

        return userPwd; // 비밀번호 반환
        
    }
    public String findId(Connection conn, String userName, String email) {
        PreparedStatement pstmt = null;
        ResultSet rs1 = null;
        String userId = null;
        String sql = prop.getProperty("idFind");
        
        
        try {
            // SQL 쿼리 작성
            System.out.println("Query: " + sql);
            System.out.println("Parameters: userName=" + userName + ", email=" + email);

            // PreparedStatement 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, email);
            	
            // 쿼리 실행
            rs1 = pstmt.executeQuery();
            
            // 결과 처리
            if (rs1.next()) {
                userId = rs1.getString("USER_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 자원 해제
            JDBCTemplate.close(rs1);
            JDBCTemplate.close(pstmt);
        }

        return userId; // 비밀번호 반환
        
    }
    /**
	 * 회원 정보 수정 쿼리문 실행용 메소드
	 * @param conn
	 * @param m
	 * @return
	 */
    public int updateMember(Connection conn, Member m) {
        int result = 0;
        PreparedStatement pstmt = null;

        // XML 파일에서 변경된 쿼리문을 가져옵니다
        String sql = prop.getProperty("updateMember");// SQL이 null 또는 빈 문자열인지 확인
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            // 수정할 회원 정보 바인딩
            pstmt.setString(1, m.getUserName());
            pstmt.setString(2, m.getGender());
            pstmt.setString(3, m.getUserBirth());
            pstmt.setString(4, m.getEmail());
            pstmt.setString(5, m.getPhone());
            pstmt.setString(6, m.getAddress());
            pstmt.setString(7, m.getUserId());  // USER_ID를 조건으로 사용
            
            // 쿼리 실행
            result = pstmt.executeUpdate();
            
            System.out.println("result : " + result);
         
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        
        return result;
    }


	/**
	 * 회원 탈퇴 쿼리문 실행용 메소드
	 * @param conn
	 * @param m
	 * @return
	 */
	public int deleteMember(Connection conn, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
}
