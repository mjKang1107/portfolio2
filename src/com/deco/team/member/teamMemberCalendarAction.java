package com.deco.team.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class teamMemberCalendarAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		System.out.println("M : teamMemberCalendarAction_execute() 호출");
		
		HttpSession session = req.getSession();
		int user_num = 0;
		if(session.getAttribute("user_num") == null) {
			return new ActionForward("./teamList.te", true);
		}
		
		user_num = (int) session.getAttribute("user_num");
		
		String team_idx = req.getParameter("idx");
		
		teamMemberDAO tmdao = new teamMemberDAO();
		List<teamMemberDTO> teamMemberList = tmdao.getSubmitmemberList(Integer.parseInt(team_idx));
		
		req.setAttribute("teamMemberList", teamMemberList);
		
		return new ActionForward("./teamMember/fullcalendar/calendar.jsp", false);
	}

}
