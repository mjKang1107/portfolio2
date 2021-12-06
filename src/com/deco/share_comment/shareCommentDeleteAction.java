package com.deco.share_comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class shareCommentDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : shareCommentDeleteAction_execute() 호출");
		
		//한글처리
		req.setCharacterEncoding("utf-8");
		
		//세션처리
		HttpSession session = req.getSession();
		
		int userNum = 0;
		
		if(session.getAttribute("user_num") == null){
			resp.sendRedirect("./shareList.sh");
		} else {
			userNum = (int) session.getAttribute("user_num");
		}
		
		String idx = req.getParameter("contentNum");
		String pageNum = req.getParameter("pageNum");
		String pageSize = req.getParameter("pageSize");
		String category = req.getParameter("category");
		int comment_idx = Integer.parseInt(req.getParameter("comment_idx"));
		
		commentDAO cDAO = new commentDAO();
		cDAO.deleteShareComment(comment_idx);
		
		
		String uri = "./shareContent.sh?pageNum="+pageNum+"&pageSize="+pageSize+"&category="+category+"&contentNum="+idx;
		
		System.out.println(uri);
		
		ActionForward forward = new ActionForward(uri, true);
		
		return forward;
	}

	
}
