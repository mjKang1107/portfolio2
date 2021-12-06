package com.deco.team.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class updateScheduleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		System.out.println("M : updateScheduleAction_execute() 호출");
		
		HttpSession session = req.getSession();
		int user_num = 0;
		if(session.getAttribute("user_num") != null) {
			user_num = (int) session.getAttribute("user_num");
		}
		calendarDTO cdto = new calendarDTO();
		
		cdto.setIdx(Integer.parseInt(req.getParameter("idx")));
		cdto.setAllday(Boolean.parseBoolean(req.getParameter("allDay")));
		cdto.setDescription(req.getParameter("description"));
		cdto.setEnd(req.getParameter("end"));
		cdto.setStart(req.getParameter("start"));
		cdto.setBackgroundcolor(req.getParameter("backgroundColor"));
		cdto.setTitle(req.getParameter("title"));
		cdto.setType(req.getParameter("type"));
		cdto.setUser_idx(user_num);
		
		calendarDAO cdao = new calendarDAO();
		int result = cdao.updateSchedule(cdto);
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.print(result);
		
		out.close();
		
		return null;
	}

}
