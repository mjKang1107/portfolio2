package com.deco.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.team.comment.Team_commentDAO;
import com.deco.team.comment.Team_commentDTO;
import com.deco.team.member.teamMemberDAO;

public class teamViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		int idx = Integer.parseInt(req.getParameter("idx"));
		int user_num = 0;
		if(session.getAttribute("user_num") != null){
			user_num = (int) session.getAttribute("user_num");
		}
		teamDAO tdao = new teamDAO();
		teamDTO tdto = tdao.getteamView(idx);
		
		String deadline = (String) tdto.getDeadline();
		int getidx = tdto.getIdx();
		int check = tdao.teamDeadlineCheck(deadline, getidx);
		
		
		req.setAttribute("tdto", tdto);
		req.setAttribute("check", check);
		
		//댓글 가져오기
		Team_commentDAO tcdao = new Team_commentDAO();
		req.setAttribute("teamCommentList", tcdao.teamComment_List(idx));
		
		ActionForward forward = new ActionForward();
		forward = new ActionForward("./team/teamView.jsp", false);
		return forward;
	}

	
}
