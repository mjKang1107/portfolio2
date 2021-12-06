package com.deco.team.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;

public class printSubmitMemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("M : printSubmitMemberListAction_execute() 호출");
		
		String team_idx = req.getParameter("team_idx");
		
		teamMemberDAO tmdao = new teamMemberDAO();
		
		req.setAttribute("submitMemberList", tmdao.getSubmitmemberList(Integer.parseInt(team_idx)));
		
		return new ActionForward("./teamMember/submitMemberList.jsp", false);
	}

}
