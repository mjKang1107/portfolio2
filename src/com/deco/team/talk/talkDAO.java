package com.deco.team.talk;

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

public class talkDAO {
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
	// 자원해제코드 - finally 구문에서 사용
	
	public void createTalk(talkDTO tdto){
		int result = -1;
		int idx = 0;
		
		try {
			conn = getConnection();
			
			sql = "select max(idx) from talk";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				idx = rs.getInt(1) + 1;
			}
			
			sql = "insert into talk values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			pstmt.setInt(2, tdto.getTeam_idx());
			pstmt.setString(3, tdto.getFilename());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	public List getTalkList(){
		List talkList = new ArrayList();
		
		try {
			conn = getConnection();
			sql = "select * from talk order by idx desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				talkDTO tdto = new talkDTO();
				
				tdto.setIdx(rs.getInt("idx"));
				tdto.setTeam_idx(rs.getInt("team_idx"));
				tdto.setFilename(rs.getString("filename"));
				
				talkList.add(tdto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return talkList;
	}
	
	public talkDTO getTalkInfo(int team_idx) {
		talkDTO tdto = null;
		
		try {
			conn = getConnection();
			sql = "select * from talk where team_idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, team_idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				tdto = new talkDTO();
				
				tdto.setIdx(rs.getInt("idx"));
				tdto.setTeam_idx(rs.getInt("team_idx"));
				tdto.setFilename(rs.getString("filename"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return tdto;
	}
}
