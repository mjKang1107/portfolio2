package com.deco.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class teamModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			
		//세션처리
		HttpSession session = req.getSession();
		
		int idx = Integer.parseInt(req.getParameter("idx"));
		String pageSize = req.getParameter("pageSize");
		String pageNum = req.getParameter("pageNum");
		
		
		if(session.getAttribute("user_num") != null){
			int userNum = (int) session.getAttribute("user_num");
		}
		
		teamDAO tdao = new teamDAO();
		teamDTO tdto = tdao.getteamView(idx);
		
		req.setAttribute("tdto", tdto);
		 
		ActionForward forward = new ActionForward();
		
		forward = new ActionForward("./team/teamUpdate.jsp", false);
		
		return forward;
		
		
		
		
		
		
	}

	
}
