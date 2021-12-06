package com.deco.team.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		System.out.println("M : MemberListAction_execute() 호출");
		
		String team_idx = req.getParameter("idx");
		
		teamMemberDAO tmdao = new teamMemberDAO();
		
		req.setAttribute("memberList", tmdao.getTeamMemberList(Integer.parseInt(team_idx)));
		
		return new ActionForward("./teamMember/memberList.jsp",false);
	}

}
