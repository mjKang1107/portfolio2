package com.deco.share;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.report.reportDAO;

public class shareListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : shareListAction_execute() 호출");
		
		//한글처리
		req.setCharacterEncoding("utf-8");
		
		//세션처리
		HttpSession session = req.getSession();
				
		int userNum = 0;
				
		if(session.getAttribute("user_num") != null){
			userNum = (int) session.getAttribute("user_num");
		}
		
		//객체 생성
		shareDAO sDAO = new shareDAO();
		
		// 검색조건과 검색내용 가져오기
        String opt =req.getParameter("opt");
        String condition = req.getParameter("condition");
		
		int cnt = sDAO.numOfShare();
		
		String str_pageSize = req.getParameter("pageSize");
		String category = req.getParameter("category");
		
		int pageSize = 0;
		
		if (str_pageSize == null){
			pageSize = 5;
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
		   
		List shareList = null;
		if (condition != null){
			shareList = sDAO.shareSearchList(opt,condition);
		} else if (category == null) {			
			shareList = sDAO.getShareList(startRow,pageSize);
		} else if (category.equals("null")){
			shareList = sDAO.getShareList(startRow,pageSize);
		} else {
			shareList = sDAO.getShareList(startRow,pageSize, category);
		}
		
		//5개 이상 신고 게시물 필터링
//		reportDAO rDAO = new reportDAO();
//		shareList = rDAO.shareFilter(shareList);
		
		req.setAttribute("shareList", shareList);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageSize", pageSize);
		
		ActionForward forward = new ActionForward("./share/shareList.jsp",false);
		
		return forward;
	}

	
	
	
}
