package com.deco.notice.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.bookmark.db.BookmarkDAO;
import com.deco.bookmark.db.BookmarkDTO;
import com.deco.notice.db.noticeDAO;
import com.deco.notice.db.noticeDTO;
import com.deco.share.shareDAO;
import com.deco.share_comment.commentDAO;

public class noticeContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : noticeContentAction_execute() 호출");
		
		//한글처리
		req.setCharacterEncoding("utf-8");
		
		//세션처리
		HttpSession session = req.getSession();
		int userNum = 0;
		if(session.getAttribute("user_num") != null){
			userNum = (int) session.getAttribute("user_num");
		}
		
		// 저장된 쿠키 불러오기
		Cookie[] cookieFromRequest = req.getCookies();
		String cookieValue = null;
		for(int i = 0 ; i<cookieFromRequest.length; i++) {
		// 요청정보로부터 쿠키를 가져오기
		cookieValue = cookieFromRequest[0].getValue();		
		}
		
		// 글 목록 -> 글 상세 : 글번호(idx)
		int idx = Integer.parseInt(req.getParameter("idx"));
	 	
	 	// 쿠키 세션 입력
		if (session.getAttribute(idx+":cookie") == null) {
		 	session.setAttribute(idx+":cookie", idx + ":" + cookieValue);
		} else {
			session.setAttribute(idx+":cookie ex", session.getAttribute(idx+":cookie"));
			if (!session.getAttribute(idx+":cookie").equals(idx + ":" + cookieValue)) {
			 	session.setAttribute(idx+":cookie", idx + ":" + cookieValue);
			}
		}
		
		//DAO객체 생성
		noticeDAO nDAO = new noticeDAO();
		
		//북마크 체크
		BookmarkDTO bmDTO = new BookmarkDTO();
		bmDTO.setUser_num(userNum);
		bmDTO.setContent_num(idx);
		BookmarkDAO bmDAO = new BookmarkDAO();
		int result = bmDAO.checkBookmark(bmDTO);
		//북마크 체크
		
		
		// 조회수 증가 카운트
	 	if (!session.getAttribute(idx+":cookie").equals(session.getAttribute(idx+":cookie ex"))) {
	 		nDAO.updateReadcount(idx);
		 
	 	}	
	 	//글번호에 해당하는 글 가져오기
	 	req.setAttribute("noticeContent", nDAO.getBoard(idx));
	 	// 북마크 체크 result값
	 	/*req.setAttribute("result", result);*/
		
		ActionForward forward = new ActionForward("./notice/content.jsp",false);
			
		return forward;
	}

}
