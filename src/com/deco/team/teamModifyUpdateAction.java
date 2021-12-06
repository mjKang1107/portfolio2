package com.deco.team;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class teamModifyUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : teamModifyUpdateAction_execute() 호출");
		
		//한글처리
		req.setCharacterEncoding("utf-8");
				
		//세션처리
		HttpSession session = req.getSession();
				
		int idx = Integer.parseInt(req.getParameter("idx"));
				
		if(session.getAttribute("user_num") != null){
			int userNum = (int) session.getAttribute("user_num");
		}
		
		String pageSize = req.getParameter("pageSize");
		String pageNum = req.getParameter("pageNum");
		String location = req.getParameter("location");
		String limit_p = req.getParameter("limit_p");
		
		teamDTO tdto = new teamDTO();
		tdto.setTitle(req.getParameter("title"));
		tdto.setContent(req.getParameter("content"));
		tdto.setLocation(req.getParameter("location"));
		tdto.setLimit_p(req.getParameter("limit_p"));
		tdto.setMaster(Integer.parseInt(req.getParameter("master")));
		tdto.setDeadline(req.getParameter("deadline"));
		tdto.setIdx(idx);
		System.out.println("마스터 : "+Integer.parseInt(req.getParameter("master")));
		
		teamDAO tdao = new teamDAO();
		int check = tdao.teamUpdate(tdto);
		
		System.out.println("M : 팀내용 수정완료");


		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		if(check == 0){
		out.print("<script>");
		out.print("alert('권한이 없습니다.');");
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
		out.print("<script>");
		out.print("alert('정상적으로 수정이 완료되었습니다.');");
		out.print("location.href='./teamView.te?idx="+idx+"&pageNum="+pageNum+"&pageSize="+pageSize+"'");
		out.print("</script>");
		out.close();
		
		return null;
	}

}
