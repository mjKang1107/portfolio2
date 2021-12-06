package com.deco.team.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.team.teamDAO;

public class joinMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		System.out.println("M : joinMemberAction_execute() 호출");
		
		int idx = Integer.parseInt(req.getParameter("idx"));
		int team_idx = Integer.parseInt(req.getParameter("team_idx"));
		
		int limit_p = Integer.parseInt(new teamDAO().getteamView(team_idx).getLimit_p());
		
		teamMemberDAO tmdao = new teamMemberDAO();
		int submitMember = tmdao.checkSubmitMember(team_idx);
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		
		if(limit_p > submitMember){
			tmdao.joinMember(idx);
			out.print("1");
			out.close();
		} else {
			out.print("0");
			out.close();
		}
		
		
		return null;
	}

}
