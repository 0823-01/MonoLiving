package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	// 1. Connection 객체 생성 후 해당 Connection 객체를 반환해주는 메소드
		public static Connection getConnection() {
			
			// driver.properties 파일로부터 접속 관련 정보들 읽어들이기
			Properties prop = new Properties();
			
			// 읽어들일 파일 경로
			String path = "/sql/driver/driver.properties";
			// > 맨 처음의 / 는 classes 폴더를 의미한다.
			
			String fileName = JDBCTemplate.class.getResource(path).getPath();
			// > driver.properties 파일을 컴파일된 파일들이 모여있는
			//   src/main/webapp/WEB-INF/classes 내부에서 찾는다.
			// > webapp이 최종적으로 배포되기 때문에
			//	 배포되는 폴더 안에 있는 파일로 정확하게 가져와야 함
			
			// C:\05_Web-workspace2\JSP_Project\src\main
			// \webapp\WEB-INF\classes\sql\driver\driver.properties
			
			try {
				prop.load(new FileInputStream(fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Connection conn = null;
			
			
			try {
				// 1) JDBC Driver 등록
				Class.forName(prop.getProperty("driver"));
				
				// 2) DB 접속정보를 제시하면서 Connection 객체 생성
				conn = DriverManager.getConnection(prop.getProperty("url"),
												   prop.getProperty("username"),
												   prop.getProperty("password"));
				
				// 3) 자동커밋 해제
				conn.setAutoCommit(false);
				// > 위의 구문을 설정하지 않으면 기본적으로 "자동 커밋"이 됨
				// > 그동안 안했던 이유
				//   : 어차피 한개의 트랜잭션에 한개의 dml 문만 처리했기 때문
				// > 앞으로는 한개의 트랜잭션 안에 여러개의 dml문을 처리할 것이기 때문에
				//	 반드시 설정을 하고 들어가자.
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			// 최종적으로 만들어진 Connection 반환
			return conn;
			

			
			
		}
		
		// 2_1. 전달받은 Connection 객체를 가지고 commit 해준느 메소드
		public static void commit(Connection conn) {
			try {
				
				if(conn!=null && !conn.isClosed()) {
					conn.commit();
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 2_2. 전달받은 Connection 객체를 가지고 rollback 해준느 메소드
		public static void rollback(Connection conn) {
			try {
				
				if(conn != null && !conn.isClosed()) {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 3_1. 전달받은 Connection 객체를 반납시켜주는 메소드
		public static void close(Connection conn) {
			try {
					
				if(conn != null && !conn.isClosed()) {
						conn.close();
					}		
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		
		// 3_2. 전달받은 (Prepared)Statement 객체를 반납시켜주는 메소드
		// > 다형성, 오버로딩 적용된 메소드임
		public static void close(Statement stmt) {
			try {
				
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		// 3_3. 전달받은 ResultSet 객체를 반납시켜주는 메소드
		public static void close(ResultSet rset) {
			try {
				if(rset != null && !rset.isClosed()) {
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
