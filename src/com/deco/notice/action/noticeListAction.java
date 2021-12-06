package com.deco.notice.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.notice.db.noticeDAO;
import com.deco.share.shareDAO;

public class noticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : noticeListAction_execute() 호출");
		
		//한글처리
		req.setCharacterEncoding("utf-8");
		
		//세션처리
		HttpSession session = req.getSession();
				
		int userNum = 0;
		if(session.getAttribute("user_num") != null){
			userNum = (int) session.getAttribute("user_num");
		}
		
		//객체 생성
		/*shareDAO sDAO = new shareDAO();*/
		noticeDAO nDAO = new noticeDAO();
		
		// 검색조건과 검색내용 가져오기
        String opt =req.getParameter("opt");
        String condition = req.getParameter("condition");
		
		/*int cnt = sDAO.numOfShare();*/
		int cnt = nDAO.getBoardCount();
		
		String str_pageSize = req.getParameter("pageSize");
		/*String category = req.getParameter("category");*/
		
		int pageSize = 0;
		
		if (str_pageSize == null){
			pageSize = 10;
		} else {
			pageSize = Integer.parseInt(str_pageSize);
		}
		
		String pageNum = req.getParameter("pageNum");
		
		if(pageNum == null){
		   pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage*pageSize;
		   
		List noticeList = null;
		if (condition != null){
			/*shareList = sDAO.shareSearchList(opt,condition);*/
			noticeList = nDAO.noticeSearchList(opt, condition);
		} /*else if (category == null) {			
			shareList = sDAO.getShareList(startRow,pageSize);
		} else if (category.equals("null")){
			shareList = sDAO.getShareList(startRow,pageSize);} */
		else {
			/*shareList = sDAO.getShareList(startRow,pageSize);*/
			noticeList = nDAO.getBoardList(startRow, pageSize);
		}
		
		/*req.setAttribute("shareList", shareList);*/
		req.setAttribute("noticeList", noticeList);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("cnt", cnt);
		req.setAttribute("currentPage", currentPage);
		
		ActionForward forward = new ActionForward("./notice/list.jsp",false);
		
		return forward;
	}

}
