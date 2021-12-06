package com.deco.report;

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

public class reportDAO {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	
	private Connection getConnection(){
		try {
			//Context 객체를 생성 (프로젝트 정보를 가지고있는객체)
			Context initCTX = new InitialContext();
			// DB연동 정보를 불러오기(context.xml)
			DataSource ds =
			(DataSource) initCTX.lookup("java:comp/env/jdbc/deco");
			
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
	public void closeDB(){
		try {
			if(rs != null){ rs.close(); }
			if(pstmt != null){ pstmt.close();}
			if(conn != null){ conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//getReportCount
	/**
	 * @param reportDTO
	 * @return 해당 reportDTO의 게시물타입(content_type)과 게시물번호(content_num)의 게시물 신고 개수를 구한다.
	 */
	public int getReportCount(reportDTO rDTO){
		
		int cnt = 0;
		try {
			conn = getConnection();
			sql = "select count(*) "
					+ "from report "
					+ "where content_num=? and content_type=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rDTO.getContent_num());
			pstmt.setInt(2, rDTO.getContent_type());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return cnt;
	}
	//getReportCount
	
	//getReportCount
	/**
	 * @param reportDTO
	 * @return 해당 게시물타입(content_type)과 게시물번호(content_num)의 게시물 신고 개수를 구한다.
	 */
	public int getReportCount(int contentType, int contentNum){
		
		int cnt = 0;
		try {
			conn = getConnection();
			sql = "select count(*) "
					+ "from report "
					+ "where content_num=? and content_type=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, contentNum);
			pstmt.setInt(2, contentType);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return cnt;
	}
	//getReportCount
	
	//getOverReport
	/**
	 * @param 신고당한 개수
	 * @return 신고당한 개수 이상의 게시물(shareDTO)들을 반환한다.
	 */
	public List<shareDTO> getOverReportShare(int countNum){
		List<shareDTO> contentList = null;

		try {
			
			conn = getConnection();
			sql = "select * "
					+ "from share "
					+ "where idx in ("
								   + "select content_num "
								   + "from ("
								   		 + "select content_num, count(*) as cnt "
								   		 + "from report "
								   		 + "group by content_num "
								   		 + "having cnt >= ?"
								   		 + ") A"
								   + ")";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, countNum);
			rs = pstmt.executeQuery();
			
			contentList = new ArrayList<shareDTO>();
			
			while(rs.next()){
				
				shareDTO sDTO = new shareDTO();
				
				sDTO.setAnony(rs.getInt("anony"));
				sDTO.setCategory(rs.getString("category"));
				sDTO.setContent(rs.getString("content"));
				sDTO.setCreate_at(rs.getString("create_at"));
				sDTO.setFile(rs.getString("file"));
				sDTO.setIdx(rs.getInt("idx"));
				sDTO.setLike_(rs.getInt("like"));
				sDTO.setRead_cnt(rs.getInt("read_cnt"));
				sDTO.setTag(rs.getString("tag"));
				sDTO.setTitle(rs.getString("title"));
				sDTO.setUser_num(rs.getInt("user_num"));
				
				contentList.add(sDTO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return contentList;
	}
	//getOverReport
	
	//insertReport
	/**
	 * @return 정상 1, 비정상 -1
	*/
	public int insertReport(reportDTO rDTO){
		int flag = -1;
	
		try {
			conn = getConnection();
			
			int idx = 0;
			
			sql = "select max(idx) from report";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println(rs.getInt(1));
				idx = rs.getInt(1)+1;
			}
			
			sql = "insert into report (idx, user_num, content_num, re_type"
					+ ", content_type, re_comment, re_date) "
					+ "values(?,?,?,?,?,?,now())";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setInt(2, rDTO.getUser_num());
			pstmt.setInt(3, rDTO.getContent_num());
			pstmt.setInt(4, rDTO.getRe_type());
			pstmt.setInt(5, rDTO.getContent_type());
			pstmt.setString(6, rDTO.getRe_comment());
			
			pstmt.executeUpdate();
			flag = 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return flag;
	}
	//insertReport
	
	//isSendReport
	public boolean isSendReport(reportDTO rDTO){
		boolean result = false;
		
		try {
			conn = getConnection();
			sql = "select idx from report where user_num=? and content_num=? and content_type=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rDTO.getUser_num());
			pstmt.setInt(2, rDTO.getContent_num());
			pstmt.setInt(3, rDTO.getContent_type());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return result;
	}
	//isSendReport
	
	//shareFilter
	public List shareFilter(List shareList){
		List resultList = new ArrayList();
		
		try {
			conn = getConnection();
			
			shareDTO sDTO = null;
			String str = "";
			
			for(int i = 0; i < shareList.size(); i++){
				sDTO = (shareDTO)shareList.get(i);
				str += sDTO.getIdx()+(shareList.size() - 1 <= i ? "":",");
			}
			
			sql = "select * "
					+ "from share "
					+ "where idx in ("+str+") "
						+ "and idx not in ("
							+ "select idx from ("
								+ "select content_num idx, count(*) from(select * from report where content_type=1"
								+ ") c "
							+ "group by content_num having count(*) >= 5"
							+ ") f"
						+ ") order by idx desc";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				sDTO = new shareDTO();

				sDTO.setAnony(rs.getInt("anony"));
				sDTO.setCategory(rs.getString("category"));
				sDTO.setContent(rs.getString("content"));
				sDTO.setCreate_at(rs.getString("create_at"));
				sDTO.setFile(rs.getString("file"));
				sDTO.setIdx(rs.getInt("idx"));
				sDTO.setLike_(rs.getInt("like_"));
				sDTO.setRead_cnt(rs.getInt("read_cnt"));
				sDTO.setTag(rs.getString("tag"));
				sDTO.setTitle(rs.getString("title"));
				sDTO.setUser_num(rs.getInt("user_num"));
				sDTO.setRepo_cnt(rs.getInt("repo_cnt"));

				resultList.add(sDTO);
			}
			System.out.println("결과 ===========>" + resultList);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return resultList;
	}
	//shareFilter
}
