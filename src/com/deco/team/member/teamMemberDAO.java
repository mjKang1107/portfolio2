package com.deco.team.member;

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

public class teamMemberDAO {

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

	// joinTeam(teamMemberDTO tmDTO)
	public int joinTeam(teamMemberDTO tmDTO) {

		int idx = 0;

		int result = -1;

		try {
			conn = getConnection();
			sql = "select max(idx) from team_member";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				idx = rs.getInt(1) + 1;
			}

			sql = "insert into team_member values(?,?,?,?,now())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);
			pstmt.setInt(2, tmDTO.getTeam_idx());
			pstmt.setInt(3, tmDTO.getMember());
			pstmt.setInt(4, 0);

			result = pstmt.executeUpdate();

			System.out.println("DAO : 회원참가완료!!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// joinTeam(teamMemberDTO tmDTO)

	// onMemberSubmit(teamMemberDTO tmDTO)
	public void onMemberSubmit(teamMemberDTO tmDTO) {
		try {
			conn = getConnection();
			sql = "update team_member set submit=1 where team_idx=? and member=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, tmDTO.getTeam_idx());
			pstmt.setInt(2, tmDTO.getMember());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// onMemberSubmit(teamMemberDTO tmDTO)

	// joinMember(int idx)
	public void joinMember(int idx) {
		try {
			conn = getConnection();
			sql = "update team_member set submit=1 where idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			pstmt.executeUpdate();

			System.out.println("DAO : 업데이트 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// joinMember(int idx)

	// memberSubmit(teamMemberDTO tmDTO)
	public int checkMemberSubmit(teamMemberDTO tmDTO) {
		int result = 0;
		try {
			conn = getConnection();
			sql = "select submit from team_member where team_idx=? and member=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, tmDTO.getTeam_idx());
			pstmt.setInt(2, tmDTO.getMember());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// memberSubmit(teamMemberDTO tmDTO)

	// deleteMember(teamMemberDTO tmDTO)
	public int deleteMember(teamMemberDTO tmDTO) {
		int result = -1;

		try {
			conn = getConnection();
			sql = "delete from team_member where team_idx=? and member=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, tmDTO.getTeam_idx());
			pstmt.setInt(2, tmDTO.getMember());

			result = pstmt.executeUpdate();

			System.out.println("DAO : 멤버 삭제 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// deleteMember(teamMemberDTO tmDTO)

	// deleteMemberOfIdx(int idx)
	public int deleteMemberOfIdx(int idx) {
		int result = -1;

		try {
			conn = getConnection();
			sql = "delete from team_member where idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			result = pstmt.executeUpdate();

			System.out.println("DAO : 멤버 삭제 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// deleteMemberOfIdx(int idx)

	// deleteMemberOfTeamNum(int team_idx)
	public void deleteMemberOfTeamIdx(int team_idx) {
		try {
			conn = getConnection();
			sql = "delete from team_member where team_idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, team_idx);

			pstmt.executeUpdate();

			System.out.println("DAO : " + team_idx + " 멤버 전원 삭제 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// deleteMemberOfTeamNum(int team_idx)

	// checkSubmitMember(int team_idx)
	public int checkSubmitMember(int team_idx) {
		int result = 0;

		try {
			conn = getConnection();
			sql = "select count(*) from team_member where team_idx=? and submit=1";
			pstmt = conn.prepareStatement(sql);
			
			System.out.println(sql);

			pstmt.setInt(1, team_idx);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			System.out.println("DAO : 현재 승인완료된 참여 인원 " + result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// checkSubmitMember(int team_idx)

	// checkRepuestTeamJoin(teamMemberDTO tmdto)
	public int checkRepuestTeamJoin(teamMemberDTO tmDTO) {
		int check = -1;

		try {
			conn = getConnection();
			sql = "select * from team_member where team_idx=? and member=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, tmDTO.getTeam_idx());
			pstmt.setInt(2, tmDTO.getMember());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				check = 0;
			} else {
				check = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return check;
	}
	// checkRepuestTeamJoin(teamMemberDTO tmdto)

	// getTeamMemberList(int idx)
	public List<teamMemberDTO> getTeamMemberList(int team_idx) {
		List<teamMemberDTO> memberList = new ArrayList<teamMemberDTO>();

		try {
			conn = getConnection();
			sql = "select * from team_member where team_idx=? order by idx desc";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, team_idx);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				teamMemberDTO tmdto = new teamMemberDTO();
				tmdto.setCreate_at(rs.getString("create_at"));
				tmdto.setIdx(rs.getInt("idx"));
				tmdto.setMember(rs.getInt("member"));
				tmdto.setSubmit(rs.getInt("submit"));
				tmdto.setTeam_idx(rs.getInt("team_idx"));

				memberList.add(tmdto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return memberList;
	}

	// getTeamMemberList(int idx)

	// getSubmitmemberList(int team_idx)
	public List<teamMemberDTO> getSubmitmemberList(int team_idx) {
		List<teamMemberDTO> teamList = new ArrayList<teamMemberDTO>();

		try {
			conn = getConnection();
			sql = "select * from team_member where team_idx=? and submit=1";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, team_idx);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				teamMemberDTO tmdto = new teamMemberDTO();

				tmdto.setCreate_at(rs.getString("create_at"));
				tmdto.setIdx(rs.getInt("idx"));
				tmdto.setMember(rs.getInt("member"));
				tmdto.setSubmit(rs.getInt("submit"));
				tmdto.setTeam_idx(rs.getInt("team_idx"));

				teamList.add(tmdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return teamList;
	}
	// getSubmitmemberList(int team_idx)

	// getNoneSubmitmemberList(int team_idx)
	public List<teamMemberDTO> getNoneSubmitmemberList(int team_idx) {
		List<teamMemberDTO> teamList = new ArrayList<teamMemberDTO>();

		try {
			conn = getConnection();
			sql = "select * from team_member where team_idx=? and submit=0";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, team_idx);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				teamMemberDTO tmdto = new teamMemberDTO();

				tmdto.setCreate_at(rs.getString("create_at"));
				tmdto.setIdx(rs.getInt("idx"));
				tmdto.setMember(rs.getInt("member"));
				tmdto.setSubmit(rs.getInt("submit"));
				tmdto.setTeam_idx(rs.getInt("team_idx"));

				teamList.add(tmdto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return teamList;
	}
	// getNoneSubmitmemberList(int team_idx)

	// getMemberInfo(int idx)
	public teamMemberDTO getMemberInfo(int idx) {
		teamMemberDTO tmdto = null;

		try {
			conn = getConnection();
			sql = "select * from team_member where idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				tmdto = new teamMemberDTO();

				tmdto.setCreate_at(rs.getString("create_at"));
				tmdto.setIdx(rs.getInt("idx"));
				tmdto.setMember(rs.getInt("member"));
				tmdto.setSubmit(rs.getInt("submit"));
				tmdto.setTeam_idx(rs.getInt("team_idx"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return tmdto;
	}
	// getMemberInfo(int idx)

}
