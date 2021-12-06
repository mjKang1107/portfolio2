package com.deco.team.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.deco.Action;
import com.deco.ActionForward;

public class readScheduleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		System.out.println("M : readScheduleAction_execute() 호출");
		
		calendarDTO cdto = new calendarDTO();
		
		cdto.setTeam_idx(Integer.parseInt(req.getParameter("team_idx")));
		cdto.setStart(req.getParameter("startDate"));
		cdto.setEnd(req.getParameter("endDate"));
		
		
		calendarDAO cdao = new calendarDAO();
		JSONArray scheduleList = cdao.readSchedule(cdto);
		
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(scheduleList);
		
		out.close();
		
		return null;
	}

}
