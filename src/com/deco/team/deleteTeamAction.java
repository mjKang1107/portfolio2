package com.deco.team;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.team.comment.Team_commentDAO;
import com.deco.team.comment.Team_commentDTO;
import com.deco.team.member.teamMemberDAO;

public class deleteTeamAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		int team_idx = Integer.parseInt(req.getParameter("idx"));
		int master = (int) session.getAttribute("user_num");
		
		teamDAO tdao = new teamDAO();
		int check = tdao.deleteTeam(team_idx, master);
		
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		if(check == 0){
			out.print("<script>");
			out.print("alert('팀해체 권한이 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}else if(check == -1){
			out.print("<script>");
			out.print("alert('잘못된 접근입니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
		
		teamMemberDAO tmdao = new teamMemberDAO();
		tmdao.deleteMemberOfTeamIdx(team_idx);
		
		Team_commentDAO tcdao = new Team_commentDAO();
		tcdao.teamdeletecomment(team_idx);
		
		
		out.print("<script>");
		out.print("alert('정상적으로 해체되었습니다.');");
		out.print("location.href='./teamList.te';");
		out.print("</script>");
		out.close();
		
		return null;
	}

	
	
	
}
