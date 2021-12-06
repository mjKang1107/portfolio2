package com.deco.team.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.deco.user.userDAO;

public class calendarDAO {

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

	public void insertSchedule(calendarDTO cdto) {
		
		int idx = 0;
		
		try {
			conn = getConnection();
			sql = "select max(idx) from team_calendar";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				idx = rs.getInt(1) + 1;
			}
			
			sql = "insert into team_calendar values(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			pstmt.setInt(2, cdto.getTeam_idx());
			pstmt.setInt(3, cdto.getUser_idx());
			pstmt.setString(4, cdto.getTitle());
			pstmt.setString(5, cdto.getDescription());
			pstmt.setString(6, cdto.getStart());
			pstmt.setString(7, cdto.getEnd());
			pstmt.setString(8, cdto.getType());
			pstmt.setString(9, cdto.getBackgroundcolor());
			pstmt.setString(10, cdto.getTextcolor());
			pstmt.setBoolean(11, cdto.isAllday());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray readSchedule(calendarDTO cdto) {
		JSONArray scheduleList = new JSONArray();
		JSONObject schedule = null;
		
		try {			
			conn = getConnection();
			sql = "select * from team_calendar where team_idx=? and !(end<? and start>?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cdto.getTeam_idx());
			pstmt.setString(2, cdto.getStart());
			pstmt.setString(3, cdto.getEnd());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				schedule = new JSONObject();
				
				schedule.put("_id", rs.getInt("idx"));
				schedule.put("username", new userDAO().getUserNickNameByNum(rs.getInt("user_idx")));
				schedule.put("title", rs.getString("title"));
				schedule.put("description", rs.getString("description"));
				schedule.put("start", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(rs.getTimestamp("start")));
				schedule.put("end", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(rs.getTimestamp("end")));
				schedule.put("type", rs.getString("type"));
				schedule.put("backgroundColor", rs.getString("backgroundcolor"));
				schedule.put("textColor", rs.getString("textcolor"));
				schedule.put("allDay", rs.getBoolean("allday"));
				
				scheduleList.add(schedule);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return scheduleList;
	}
	
	public int updateSchedule(calendarDTO cdto) {
		int result = -1;
		
		try {
			conn = getConnection();
			sql = "select user_idx from team_calendar where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cdto.getIdx());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt(1) == cdto.getUser_idx()) {
					
					sql = "update team_calendar set allDay=?, description=?, end=?, start=?, "
							+ "backgroundColor=?, title=?, type=? where idx=?";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setBoolean(1, cdto.isAllday());
					pstmt.setString(2, cdto.getDescription());
					pstmt.setString(3, cdto.getEnd());
					pstmt.setString(4, cdto.getStart());
					pstmt.setString(5, cdto.getBackgroundcolor());
					pstmt.setString(6, cdto.getTitle());
					pstmt.setString(7, cdto.getType());
					pstmt.setInt(8, cdto.getIdx());
					
					result = pstmt.executeUpdate();
					
				} else {
					result = 0;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	
	public int updateDateSchedule(calendarDTO cdto) {
		int result = -1;
		
		try {
			conn = getConnection();
			sql = "select user_idx from team_calendar where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cdto.getIdx());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt(1) == cdto.getUser_idx()) {
					
					sql = "update team_calendar set end=?, start=? "
							+ " where idx=?";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, cdto.getEnd());
					pstmt.setString(2, cdto.getStart());
					pstmt.setInt(3, cdto.getIdx());
					
					result = pstmt.executeUpdate();
					
				} else {
					result = 0;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	
	public int deleteSchedule(calendarDTO cdto) {
		int result = -1;
		
		try {
			conn = getConnection();
			sql = "select user_idx from team_calendar where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cdto.getIdx());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt(1) == cdto.getUser_idx()) {
					sql = "delete from team_calendar where idx=?";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, cdto.getIdx());
					
					result = pstmt.executeUpdate();
					
				} else {
					result = 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}

}
