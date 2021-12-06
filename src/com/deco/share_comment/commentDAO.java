package com.deco.share_comment;

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

import com.deco.share.shareDTO;

public class commentDAO {

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

	// insertShareComment(cDTO)
	public void insertShareComment(commentDTO cDTO) {

		int comment_num = 0;
		try {
			conn = getConnection();

			sql = "select max(comment_idx) from share_comment";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				comment_num = rs.getInt(1) + 1;
			}

			sql = "insert into share_comment values(?, ?, ?, ?, ?, ?, now())";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comment_num);
			pstmt.setInt(2, cDTO.getShare_idx());
			pstmt.setInt(3, cDTO.getUser_num());
			pstmt.setString(4, cDTO.getContent());
			pstmt.setInt(5, cDTO.getRe_lev());
			pstmt.setInt(6, cDTO.getRe_seq());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// insertShareComment(cDTO)

	// getCommentList()
	public List getCommentList() {
		List commentList = new ArrayList();

		commentDTO cDTO = null;
		try {
			conn = getConnection();
			sql = "select * from share_comment order by create_at desc";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				cDTO = new commentDTO();

				cDTO.setComment_idx(rs.getInt("comment_idx"));
				cDTO.setContent(rs.getString("content"));
				cDTO.setCreate_at(rs.getString("create_at"));
				cDTO.setRe_lev(rs.getInt("re_lev"));
				cDTO.setRe_seq(rs.getInt("re_seq"));
				cDTO.setShare_idx(rs.getInt("share_idx"));
				cDTO.setUser_num(rs.getInt("user_num"));

				commentList.add(cDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return commentList;
	}
	// getCommentList()

	// getCommentList()
	public List getCommentList(int share_idx) {
		List commentList = new ArrayList();

		commentDTO cDTO = null;
		try {
			conn = getConnection();
			sql = "select * from share_comment where share_idx=? order by create_at desc";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, share_idx);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				cDTO = new commentDTO();

				cDTO.setComment_idx(rs.getInt("comment_idx"));
				cDTO.setContent(rs.getString("content"));
				cDTO.setCreate_at(rs.getString("create_at"));
				cDTO.setRe_lev(rs.getInt("re_lev"));
				cDTO.setRe_seq(rs.getInt("re_seq"));
				cDTO.setShare_idx(rs.getInt("share_idx"));
				cDTO.setUser_num(rs.getInt("user_num"));

				commentList.add(cDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return commentList;
	}
	// getCommentList()

	// deleteShareComment(comment_idx)
	public void deleteShareComment(int comment_idx) {

		try {
			conn = getConnection();
			sql = "delete from share_comment where comment_idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comment_idx);

			pstmt.executeUpdate();

			System.out.println("댓글삭제 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}

	// deleteShareComment(comment_idx)

	//modifyShareComment(cDTO)
	public void modifyShareComment(String content, int comment_idx) {

		try {
			conn = getConnection();
			sql = "update share_comment set content=? where comment_idx=?";

			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, content);
			pstmt.setInt(2, comment_idx);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}
	
	//modifyShareComment(cDTO)

	// deleteCommentAll(idx)
	public void deleteCommentAll(int idx) {

		try {
			conn = getConnection();
			sql = "delete from share_comment where share_idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			pstmt.executeUpdate();

			System.out.println("댓글삭제 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// deleteCommentAll(idx)
	
	

	
	
}
