package com.deco.team.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class teamMemberMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : teamMemberMainAction_execute() 호출");
		
		String team_idx = req.getParameter("idx");
		
		HttpSession session = req.getSession();
		
		int user_num = 0;
		
		if(session.getAttribute("user_num") == null) {
			resp.sendRedirect("./teamList.te");
		} else {
			user_num = (int) session.getAttribute("user_num");
		}
		teamMemberDTO tmDTO = new teamMemberDTO();
		
		tmDTO.setTeam_idx(Integer.parseInt(team_idx));
		tmDTO.setMember(user_num);
		
		teamMemberDAO tmdao = new teamMemberDAO();
		
		int result = tmdao.checkMemberSubmit(tmDTO);
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		if (result == 1) {
			return new ActionForward("./teamMember/talkView.jsp", false);
		} else {
			out.print("<script>");
			out.print("alert('해당팀의 팀페이지에 입장 권한이 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
		}
		
		
	}

}
