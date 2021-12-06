package com.deco.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;


public class teamListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : teamListAction_execute() 호출");
		
		//한글처리
		req.setCharacterEncoding("utf-8");
		
		//세션처리
		HttpSession session = req.getSession();
				
		int userNum = 0;
				
		if(session.getAttribute("user_num") != null){
			userNum = (int) session.getAttribute("user_num");
		}
		
		teamDAO tdao = new teamDAO();
		
		// 검색조건과 검색내용 가져오기
        String opt =req.getParameter("opt");
        String condition = req.getParameter("condition");
		
		//팀생성 글 개수 보기 
		int cnt =  tdao.numOfTeam();
		
		String str_pageSize = req.getParameter("pageSize");
		
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
		
		//팀 리스트 보기
		List teamList = null;
		
		if (condition != null){
			teamList = tdao.teamSearchList(opt,condition);
		}else{
			teamList = tdao.teamList(startRow,pageSize);
		}
		
		req.setAttribute("teamList", teamList);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageSize", pageSize);
		
		System.out.println(pageSize);
		
		ActionForward forward = new ActionForward();
		forward = new ActionForward("./team/teamList.jsp", false);
		
		return forward;
	}

	
}
