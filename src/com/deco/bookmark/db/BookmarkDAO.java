package com.deco.bookmark.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookmarkDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

   private Connection getConnection(){
	   try {
		   // Context 객체를 생성(프로젝트 정보를 가지고있는 객체)
		   Context initCTX = new InitialContext();
		   
		   // DB연동 정보를 불러오기(context.xml)
		   DataSource ds = 
				   (DataSource)initCTX.lookup("java:comp/env/jdbc/deco");
		   
		   conn = ds.getConnection();
		   
		   System.out.println(" 드라이버 로드, 디비연결 성공! ");
		   System.out.println(conn);
		   
	   } catch (NamingException e) {
		   e.printStackTrace();
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	   
    	return conn;
    }// getConnectino() - 디비연결 끝
   
   // 자원해제코드 - finally 구문에서 사용
   public void closeDB(){
	   // 자원해제
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   
   /////////////////////////////////////////////////////////////////////////////////////////////
   
   //addBookmark(bkDTO)
  	public void addBookmark(BookmarkDTO bmDTO){
 		
 		// 장바구니 번호
 		int idx = 0;
 		
 		try {
 			conn = getConnection();
 			
 			// 상품번호 계산 : 기존의 장바구니가 있으면 해당번호 + 1
 			sql = "select max(idx) from bookmark";
 			pstmt = conn.prepareStatement(sql);
 			
 			rs = pstmt.executeQuery();
 			
 			if(rs.next()){
 				idx = rs.getInt(1) + 1;
 			}

 			System.out.println("DAO : 번호 + " + idx);
 			
 			// 상품을 장바구니에 추가
 			sql = "insert into bookmark(idx, user_num, content_num, type) values(?,?,?,?);";
 			pstmt = conn.prepareStatement(sql);
 			
 			pstmt.setInt(1, idx);
 			pstmt.setInt(2, bmDTO.getUser_num());
 			pstmt.setInt(3, bmDTO.getContent_num());
 			pstmt.setInt(4, bmDTO.getType());
 			
 			pstmt.executeUpdate();
 			
 			System.out.println("DAO : 북마크 추가 완료");
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}finally {
 			closeDB();
 		}
 	}
  	//addBookmark(bkDTO)
   
  //checkBookmark(bkDTO)
   	public int checkBookmark(BookmarkDTO bmDTO){
   		int result = 0;
   		
   		try {
   			conn = getConnection();

   			sql = "select * from bookmark where user_num=? and content_num=? and type=?";
   			pstmt = conn.prepareStatement(sql);
   			
   			pstmt.setInt(1, bmDTO.getUser_num());
   			pstmt.setInt(2, bmDTO.getContent_num());
   			pstmt.setInt(3, bmDTO.getType());
   			
   			rs = pstmt.executeQuery();
   			
   			if(rs.next()){
   				result = 1;
   			}else{
   				result = 0;
   			}

   			System.out.println("DAO : 북마크 체크 완료! -> " + result);
   			
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}finally {
   			closeDB();
   		}
   		
   		return result;
   	}
   	//checkBookmark(bkDTO)
   
  //deleteBookmark(bkDTO)
   	public void deleteBookmark(BookmarkDTO bmDTO){
   		try {
   			conn = getConnection();

   			sql = "delete from bookmark where user_num=? and content_num=? and type=?";
   			pstmt = conn.prepareStatement(sql);
   			
   			pstmt.setInt(1, bmDTO.getUser_num());
   			pstmt.setInt(2, bmDTO.getContent_num());
   			pstmt.setInt(3, bmDTO.getType());
   			
   			pstmt.executeUpdate();

   			System.out.println("DAO : 북마크 삭제 완료! -> ");
   			
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}finally {
   			closeDB();
   		}
   	}
   	//deleteBookmark(bkDTO)
   
  //ckBookmark(bkDTO)
   	public int ckBookmark(int user_num, int content_num, int type){
   		int result = 0;
   		
   		try {
   			conn = getConnection();

   			sql = "select * from bookmark where user_num=? and content_num=? and type=?";
   			pstmt = conn.prepareStatement(sql);
   			
   			pstmt.setInt(1, user_num);
   			pstmt.setInt(2, content_num);
   			pstmt.setInt(3, type);
   			
   			rs = pstmt.executeQuery();
   			
   			if(rs.next()){
   				result = 1;
   			}else{
   				result = 0;
   			}

   			System.out.println("DAO : 북마크 체크 완료! -> " + result);
   			
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}finally {
   			closeDB();
   		}
   		
   		return result;
   	}
   	//ckBookmark(bkDTO)
   
   
   
   
   
   
   
}
