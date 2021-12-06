package com.deco.team.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.team.teamDAO;

public class deleteTeamMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : deleteTeamMemberAction_execute() 호출");
		
		HttpSession session = req.getSession();
		
		ActionForward forward = null;
		
		int user_num = 0;
		if(session.getAttribute("user_num") == null) {
			forward = new ActionForward("./teamMain.te", true);
			return forward;
		} else {
			user_num = (int) session.getAttribute("user_num");
		}
		
		teamMemberDTO tmDTO = new teamMemberDTO();
		
		tmDTO.setMember(user_num);
		tmDTO.setTeam_idx(Integer.parseInt(req.getParameter("idx")));
		
		teamMemberDAO tmDAO = new teamMemberDAO();
		
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();

		if (tmDAO.deleteMember(tmDTO) == -1) {
			out.print("<script>");
			out.print("alert('정상적으로 탈퇴과정이 이루어 지지 않았습니다. 관리자에게 문의해 주세요.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();

			return null;
		
		}else if (tmDAO.deleteMember(tmDTO) == 0) {
			out.print("<script>");
			out.print("alert('신청이 되어 있지 않습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();

			return null;
		} else {
			out.print("<script>");
			out.print("alert('탈퇴가 완료 되었습니다.');");
			out.print("location.href='./teamMain.te';");
			out.print("</script>");
			out.close();

			return null;
		}
	}
}
