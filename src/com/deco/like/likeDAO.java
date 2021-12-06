package com.deco.like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.deco.user.userDTO;

public class likeDAO {
	

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
	
		//addLike(lDTO)
	public void addLike(likeDTO lDTO){
		int idx = 0;
		try {
			conn = getConnection();
			// 1. 번호 계산
			sql = "select max(idx) from like_";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				idx = rs.getInt(1) + 1;
			}else{
				idx = 1;
			}
			System.out.println("DAO : idx 번호 " + idx);
			
			// 2. 글 등록
			sql = "insert into like_ (idx, user_num, content_num, content_type) "
					   + "values(?,?,?,?)";
			   pstmt = conn.prepareStatement(sql);
			   // ?
			   pstmt.setInt(1, idx);
			   pstmt.setInt(2, lDTO.getUser_num());
			   pstmt.setInt(3, lDTO.getContent_num());
			   pstmt.setInt(4, lDTO.getContent_type());
			   
			   // 4 sql 실행
			   pstmt.executeUpdate();
			   
			 sql = "update share set like_=like_+1 where idx=?";
			 	pstmt = conn.prepareStatement(sql);
			 	
			 	pstmt.setInt(1, lDTO.getContent_num());
			 
			    pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	//addLike(lDTO)
	
	//deleteLike(lDTO)
	public void deleteLike(likeDTO lDTO) {
		try {
			conn = getConnection();
			
			sql = "delete from like_ where user_num=? and content_num=? and content_type=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, lDTO.getUser_num());
			pstmt.setInt(2, lDTO.getContent_num());
			pstmt.setInt(3, lDTO.getContent_type());
   			
			pstmt.executeUpdate();

			 sql = "update share set like_=like_-1 where idx=?";
			 	pstmt = conn.prepareStatement(sql);
			 	
			 	pstmt.setInt(1, lDTO.getContent_num());
			 
			    pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}
	//deleteLike(lDTO)
	

	// checkLike(lDTO)
	public int checkLike(likeDTO lDTO){
   		int result = 0;
    		
   		try {
   			conn = getConnection();

   			sql = "select * from like_ where user_num=? and content_num=? and content_type=?";
   			pstmt = conn.prepareStatement(sql);
   			
   			pstmt.setInt(1, lDTO.getUser_num());
   			pstmt.setInt(2, lDTO.getContent_num());
   			pstmt.setInt(3, lDTO.getContent_type());
	
   			rs = pstmt.executeQuery();
   			
   			if(rs.next()){
   				result = 1;
   			}
   			
   			System.out.println("result : " + result);
   			
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}finally {
   			closeDB();
   		}
   		
   		return result;
   	}
	// checkLike(lDTO)
	

	// likeCount()
	
	/* 예비용 (add , delete 정상 작동이면 삭제 필수)
	  
 	// 디비에 들어있는 좋아요 리턴   (shareList 용)
	public int likeCount(){
		int lcnt = 0;
		likeDTO lDTO = new likeDTO();
		try {
			// 1,2 드라이버 로드, 디비연결
			conn = getConnection();
			// 3 sql 작성(select) & pstmt 객체 생성
			sql = "select count(*) from like_ where user_num=? and content_num=? and content_type=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lDTO.getUser_num());
   			pstmt.setInt(2, lDTO.getContent_num());
   			pstmt.setInt(3, lDTO.getContent_type());
   			
			// 4 sql 실행
			rs = pstmt.executeQuery();
			// 5 데이터 처리
			if(rs.next()){
				lcnt = rs.getInt(1);
			}
			
			System.out.println("좋아요 " + lcnt);
						
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return lcnt;
	}
	// likeCount()
	 예비용 (add , delete 정상 작동이면 삭제 필수) */
	
	//getUserLikeContent
	public List<likeDTO> getUserLikeContent(int user_num, int type) {
		List<likeDTO> likeList = new ArrayList<likeDTO>();
		
		try {
			conn = getConnection();
			
			//type = 1 -share ,  2 - team,  other - all
			String getType = "";
			switch (type) {
			case 1:
				getType = " and content_type=1";
				break;
			
			case 2:
				getType = " and content_type=2";
				break;
				
			default:
				break;
			}
			
			sql = "select * from like_ where user_num=?" + getType;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				likeDTO lDTO = new likeDTO();
				lDTO.setIdx(rs.getInt(1));
				lDTO.setUser_num(rs.getInt(2));
				lDTO.setContent_num(rs.getInt(3));
				lDTO.setContent_type(rs.getInt(4));
				
				likeList.add(lDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return likeList;
	}
	//getUserLikeContent
	
}
