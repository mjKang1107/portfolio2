package com.deco.team.comment;

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

import com.deco.share_comment.commentDTO;

public class Team_commentDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	private Connection getConnection() {
		try {
			// Context 객체를 생성 (프로젝트 정보를 가지고있는객체)
			Context initCTX = new InitialContext();
			// DB연동 정보를 불러오기(context.xml)
			DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/deco");

			conn = ds.getConnection();

			System.out.println(" 드라이버 로드, 디비연결 성공! ");
			System.out.println(conn);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
	// getConnection() - 디비연결 끝

	// 자원해제코드 - finally 구문에서 사용
	public void closeDB() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void teamComment_insert(Team_commentDTO tcdto){
		int idx=0;
		
		try {
			conn = getConnection();
			sql="select max(idx) from team_comment";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				idx = rs.getInt(1)+1;
			}
			sql ="insert into team_comment(idx, team_idx, user_num, content, create_at, secret) "
					+ "values(?,?,?,?,now(),?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setInt(2, tcdto.getTeam_idx());
			pstmt.setInt(3, tcdto.getUser_num());
			pstmt.setString(4, tcdto.getContent());
			pstmt.setInt(5, tcdto.getSecret());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}
		
	// teamComment_List()
		public List teamComment_List() {
			List teamCommentList = new ArrayList();

			Team_commentDTO tcdto = null;
			
			try {
				conn = getConnection();
				sql = "select * from team_comment order by create_at desc";
				pstmt = conn.prepareStatement(sql);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					tcdto = new Team_commentDTO();

					tcdto.setIdx(rs.getInt("idx"));
					tcdto.setTeam_idx(rs.getInt("team_idx"));
					tcdto.setUser_num(rs.getInt("user_num"));
					tcdto.setContent(rs.getString("content"));
					tcdto.setCreate_at(rs.getString("create_at"));
					tcdto.setSecret(rs.getInt("secret"));
					
					teamCommentList.add(tcdto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return teamCommentList;
		}
		// teamComment_List()

		// teamComment_List(team_idx)
		public List teamComment_List(int team_idx) {
			List teamCommentList = new ArrayList();

			Team_commentDTO tcdto = null;
			
			try {
				conn = getConnection();
				sql = "select * from team_comment where team_idx=? order by create_at desc";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, team_idx);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					tcdto = new Team_commentDTO();

					tcdto.setIdx(rs.getInt("idx"));
					tcdto.setTeam_idx(rs.getInt("team_idx"));
					tcdto.setUser_num(rs.getInt("user_num"));
					tcdto.setContent(rs.getString("content"));
					tcdto.setCreate_at(rs.getString("create_at"));
					tcdto.setSecret(rs.getInt("secret"));
					
					teamCommentList.add(tcdto);
						
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return teamCommentList;
		}
		// teamComment_List(team_idx)
	
		// commentdelete(Team_commentDTO tcdto)
		public int commentDelete(Team_commentDTO tcdto){
			int check = -1;
			try {
				conn = getConnection();
				sql="select * from team_comment where idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, tcdto.getIdx());
				rs = pstmt.executeQuery();
				if(rs.next()){
					if(tcdto.getUser_num()==rs.getInt("user_num")){
						sql="delete from team_comment where idx=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, tcdto.getIdx());
						pstmt.executeUpdate();
						
						check = 1;
					}else{
						check = 0;
					}
				}else{
					check = -1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
			return check;
		}
		// commentdelete(Team_commentDTO tcdto)
		// commentUpdate(Team_commentDTO tcdto)
		public void commentUpdate(Team_commentDTO tcdto){
			try {
				conn = getConnection();
				sql="update team_comment set content=?, secret=? where idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, tcdto.getContent());
				pstmt.setInt(2, tcdto.getSecret());
				pstmt.setInt(3, tcdto.getIdx());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
			
		}
		// commentUpdate(Team_commentDTO tcdto)
		// teamdeletecomment
		public void teamdeletecomment(int team_idx){
			try {
				conn = getConnection();
				sql="delete from team_comment where team_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, team_idx);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
					
		}
	
	
	
	
	
	
	
}
