package com.deco.team.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;

public class submitMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : submitMemberAction_execute() 호출");
		
		teamMemberDTO tmDTO = new teamMemberDTO();
		
		int team_idx = Integer.parseInt(req.getParameter("team_idx"));
		
		tmDTO.setMember(Integer.parseInt(req.getParameter("member")));
		tmDTO.setTeam_idx(team_idx);
		
		teamMemberDAO tmDAO = new teamMemberDAO();
		tmDAO.checkSubmitMember(team_idx);
		
		tmDAO.onMemberSubmit(tmDTO);
		
		return null;
	}

}
