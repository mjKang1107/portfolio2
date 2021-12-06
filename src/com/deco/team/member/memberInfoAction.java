package com.deco.team.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.team.teamDAO;
import com.deco.user.userDAO;

public class memberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : memberInfoAction_execute() 호출");
		
		String idx = req.getParameter("idx");
		String team_idx = req.getParameter("team_idx");
		
		teamMemberDAO tmdao = new teamMemberDAO();
		teamMemberDTO tmdto = tmdao.getMemberInfo(Integer.parseInt(idx));
		req.setAttribute("tmdto", tmdto);
		req.setAttribute("memberInfo", new userDAO().getUserInfo(tmdto.getMember()));
		req.setAttribute("masterNum", new teamDAO().getteamView(Integer.parseInt(team_idx)).getMaster());
		
		return new ActionForward("./teamMember/memberInfo.jsp",false);
	}

}
