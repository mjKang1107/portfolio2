package com.deco.notice.db;

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

public class noticeDAO {

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
	
	
	//insertNotice(nDTO)
	public void insertNotice(noticeDTO nDTO){
		int num = 0;
		try {
			conn = getConnection();
			// 1. 번호 계산
			sql = "select max(idx) from notice";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				//num = rs.getInt("max(num)") + 1;
				num = rs.getInt(1) + 1;
			}else{
				num = 1;
			}
			System.out.println("DAO : 상품번호 - " + num);
			
			// 2. 글 등록
			sql = "insert into notice (idx, user_num, title, content, file, create_at, count) "
					   + "values(?,?,?,?,?,now(),0)";
			   pstmt = conn.prepareStatement(sql);
			   // ?
			   pstmt.setInt(1, num);
			   pstmt.setInt(2,nDTO.getUser_num());
			   pstmt.setString(3, nDTO.getTitle());
			   pstmt.setString(4, nDTO.getContent());
			   pstmt.setString(5, nDTO.getFile());
			   
			   // 4 sql 실행
			   pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	//insertNotice(nDTO)
	

	// updateReadcount(num)
	public void updateReadcount(int idx){
		try {
			// 1,2 디비연결
			conn = getConnection();
			// 3 sql 구문 작성(update) & pstmt 객체
			sql = "update notice set count=count+1 where idx=?";
			pstmt = conn.prepareStatement(sql);
			//	?
			pstmt.setInt(1, idx);
			// 5 sql 실행
			pstmt.executeUpdate();
			
			System.out.println(" 글 조회수 증가 완료! ");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}
	// updateReadcount(num)
	
	// getBoardCount()
	// 디비에 들어있는 글의 개수 계산후 리턴
	public int getBoardCount(){
		int cnt = 0;
		
		try {
			// 1,2 드라이버 로드, 디비연결
			conn = getConnection();
			// 3 sql 작성(select) & pstmt 객체 생성
			sql = "select count(*) from notice";
			pstmt = conn.prepareStatement(sql);
			// 4 sql 실행
			rs = pstmt.executeQuery();
			// 5 데이터 처리
			if(rs.next()){
				cnt = rs.getInt(1);	// "count(*)"
				//cnt = rs.getInt("count(*)");
			}
			System.out.println("SQL 구문 실행완료!");
			System.out.println(" 글 개수 : " + cnt + "개");
			
		} catch (Exception e) {
			System.out.println(" 게시판 글개수_에러 발생!! ");
			e.printStackTrace();
		}finally{
			// 자원해제
			closeDB();
		}
		
		return cnt;
	}
	// getBoardCount()
	
	
	// getBoardList(startRow, pageSize)
	public ArrayList getBoardList(int startRow, int pageSize){
		// DB -> Bean -> ArrayList
		// DB데이터 1row 정보를 BoardBean 저장 -> ArrayList 한칸에 저장
		
		// 게시판의 글정보를 원하는만큼 저장하는 가변길이 배열
		ArrayList boardList = new ArrayList();
		
		// 게시판 글 1개의 정보를 저장하는 객체
		noticeDTO nDTO = null;
		// BoardBean bb = new BoardBean();	// db에서 데이터가 있을때 가져다 써야하는데, 미리 객체를 만들어놓으면 공간낭비가 된다.
		
		try {
			// 1,2 드라이버로드, 디비연결
			conn = getConnection();
			// 3 sql 구문 & pstmt 객체
			// 글 정보 정렬	- order by
			//			- re_ref 값을 최신글 위쪽으로 정렬(내림차순)
			//			- re_seq 값을 사용(오름차순)
			//			- limit a,b		(a 시작행-1, b 개수)
			//			ex) 1번글 -> 0번 인덱스
			
			//sql = "select * from itwill_board ";
			sql = "select * from notice order by idx desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			
			//			?
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			
			// 4 sql 실행
			rs = pstmt.executeQuery();
			
			// 5 데이터처리
			while(rs.next()){
				// 데이터있을때 bb 객체 생성
				nDTO = new noticeDTO();
				
				// DB정보 -> BoardBean 저장
				//(idx, user_num, title, content, file, create_at, count)
				
				nDTO.setIdx(rs.getInt("idx"));
				nDTO.setUser_num(rs.getInt("user_num"));
				nDTO.setTitle(rs.getString("title"));
				nDTO.setContent(rs.getString("content"));
				nDTO.setCreate_at(rs.getDate("create_at"));
				nDTO.setCount(rs.getInt("count"));
				
				// Bean -> ArrayList 한칸에 저장
				boardList.add(nDTO);
				
			}// while 끝
			
			System.out.println(" 게시판 모든정보 저장완료! ");
			System.out.println(" 총 " + boardList.size() + "개");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return boardList;
	}
	// getBoardList(startRow, pageSize)
	
	
	// getBoard(num)
	public noticeDTO getBoard(int idx){
		noticeDTO nDTO = null;	// if(rs.next)안에 선언하면 지역변수가 되기때문에 밖에서는 사용불가!
		try {
			// 1,2 디비 연결
			conn = getConnection();
			// 3 sql 작성(select) & pstmt 객체
			sql = "select * from notice where idx=?";
			pstmt = conn.prepareStatement(sql);
			//	?
			pstmt.setInt(1, idx);
			// 4 sql 실행
			rs = pstmt.executeQuery();
			// 5 데이터 처리(bean 저장)
			if(rs.next()){
				nDTO = new noticeDTO();
				
				nDTO.setIdx(rs.getInt("idx"));
				nDTO.setUser_num(rs.getInt("user_num"));
				nDTO.setTitle(rs.getString("title"));
				nDTO.setContent(rs.getString("content"));
				nDTO.setFile(rs.getString("file"));
				nDTO.setCreate_at(rs.getDate("create_at"));
				nDTO.setCount(rs.getInt("count"));
				
			}
			System.out.println(" 글번호에 해당하는 글정보 저장완료! ");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return nDTO;
	}
	// getBoard(num)
	
	
	//deleteNotice(int idx)
	public void deleteNotice(int idx) {

		try {
			conn = getConnection();
			sql = "delete from notice where idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);
			pstmt.executeUpdate();

			System.out.println("삭제 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}
	//deleteNotice(int idx)
	
	
	//getContent(idx)
	public noticeDTO getContent(int idx){
		noticeDTO nDTO = null;
		try {
			conn = getConnection();
			sql = "select * from notice where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				nDTO = new noticeDTO();
				
				nDTO.setIdx(rs.getInt("idx"));
				nDTO.setUser_num(rs.getInt("user_num"));
				nDTO.setTitle(rs.getString("title"));
				nDTO.setContent(rs.getString("content"));
				nDTO.setFile(rs.getString("file"));
				nDTO.setCreate_at(rs.getDate("create_at"));
				nDTO.setCount(rs.getInt("count"));

			}//if
			System.out.println("DAO : 컨텐츠수정 저장완료!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return nDTO;
	}
	//getContent(idx)
	
	//modifyContent(nDTO)
	public void modifyContent(noticeDTO nDTO, int idx){
		
		try {
			conn = getConnection();
			
			sql = "update notice set title=?, content=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, nDTO.getTitle());
			pstmt.setString(2, nDTO.getContent());
			pstmt.setInt(3, idx);
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 글 수정 완료!");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	//modifyContent(nDTO)
	
	// getContentCount()
	// 글번호 최대값
	public int getMaxContent(){
		int cnt = 0;
		
		try {
			// 1,2 드라이버 로드, 디비연결
			conn = getConnection();
			// 3 sql 작성(select) & pstmt 객체 생성
			sql = "select max(idx) from notice";
			pstmt = conn.prepareStatement(sql);
			// 4 sql 실행
			rs = pstmt.executeQuery();
			// 5 데이터 처리
			if(rs.next()){
				cnt = rs.getInt(1);	// "count(*)"
				//cnt = rs.getInt("count(*)");
			}
			System.out.println("SQL 구문 실행완료!");
			System.out.println(" 글 개수 : " + cnt + "개");
			
		} catch (Exception e) {
			System.out.println(" 게시판 글개수_에러 발생!! ");
			e.printStackTrace();
		}finally{
			// 자원해제
			closeDB();
		}
		
		return cnt;
	}
	// getContentCount()
	
	// getContentCount()
	// 글번호 최대값
	public int getMinContent(){
		int cnt = 0;
		
		try {
			// 1,2 드라이버 로드, 디비연결
			conn = getConnection();
			// 3 sql 작성(select) & pstmt 객체 생성
			sql = "select min(idx) from notice";
			pstmt = conn.prepareStatement(sql);
			// 4 sql 실행
			rs = pstmt.executeQuery();
			// 5 데이터 처리
			if(rs.next()){
				cnt = rs.getInt(1);	// "count(*)"
				//cnt = rs.getInt("count(*)");
			}
			System.out.println("SQL 구문 실행완료!");
			System.out.println(" 글 개수 : " + cnt + "개");
			
		} catch (Exception e) {
			System.out.println(" 게시판 글개수_에러 발생!! ");
			e.printStackTrace();
		}finally{
			// 자원해제
			closeDB();
		}
			
			return cnt;
		}
	// getContentCount()
	
	
	// getIdxExistPre()

	public int getIdxExistPre(int idx){

		int result = 0;
		int cnt = 0;
		int idxPre = idx-1;

		try {

			conn = getConnection();

			sql = "select idx from notice where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idxPre);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result = -1;

			}else{

				sql = "select max(idx) from notice where idx<?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, idx);
				ResultSet rs2 = pstmt2.executeQuery();

				if(rs2.next()){
					cnt = rs2.getInt(1);
					result = cnt;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return result;
	}

	// getIdxExistPre()

	

	// getIdxExistNext()

		public int getIdxExistNext(int idx){

			int result = 0;
			int cnt = 0;
			int idxNext = idx+1;

			try {
				conn = getConnection();
				sql = "select idx from notice where idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idxNext);
				rs = pstmt.executeQuery();

				if(rs.next()){
					result = -1;
				}else{
					sql = "select min(idx) from notice where idx>?";
					PreparedStatement pstmt2 = conn.prepareStatement(sql);
					pstmt2.setInt(1, idx);
					ResultSet rs2 = pstmt2.executeQuery();
					if(rs2.next()){
						cnt = rs2.getInt(1);
						result = cnt;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return result;
		}
		// getIdxExistNext()
		
		
		// noticeSearchList(opt,condition)
		public List noticeSearchList(String opt, String condition) {
			noticeDTO nDTO = null;
			List noticeSearchList = new ArrayList();

			try {
				conn = getConnection();
				if (opt.equals("0")) {
					sql = "select * from notice where title like '%" + condition + "%' order by idx desc";
				} else if (opt.equals("1")) {
					sql = "select * from notice where content like '%" + condition + "%' order by idx desc";
				} else if (opt.equals("2")) {
					sql = "select * from notice where concat(title,content) like '%" + condition + "%' order by idx desc";
				} else if (opt.equals("3")) {
					sql = "select * from notice " + "where user_num = " + "(select user_num from deco.user "
							+ "where nickname like '%" + condition + "%') order by idx desc";
				}
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				System.out.println(sql);
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				pstmt = conn.prepareStatement(sql);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					nDTO = new noticeDTO();
					
					nDTO.setIdx(rs.getInt("idx"));
					nDTO.setUser_num(rs.getInt("user_num"));
					nDTO.setTitle(rs.getString("title"));
					nDTO.setContent(rs.getString("content"));
					nDTO.setFile(rs.getString("file"));
					nDTO.setCreate_at(rs.getDate("create_at"));
					nDTO.setCount(rs.getInt("count"));

					noticeSearchList.add(nDTO);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}

			return noticeSearchList;
		}
		// noticeSearchList(opt,condition)
		
	
}
