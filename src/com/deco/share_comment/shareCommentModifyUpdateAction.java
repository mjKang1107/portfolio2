package com.deco.share_comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class shareCommentModifyUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : shareCommentModifyUpdateAction_execute() 호출");
		
		//한글처리
		req.setCharacterEncoding("utf-8");
				
		int comment_idx = Integer.parseInt(req.getParameter("comment_idx"));
		String content = req.getParameter("content");
		
		//세션처리
		HttpSession session = req.getSession();
						
		int userNum = 0;
						
		if(session.getAttribute("user_num") == null){
			resp.sendRedirect("./shareList.sh");
		} else {
			userNum = (int) session.getAttribute("user_num");
			}
		System.out.println("gd");

		
		commentDAO cDAO = new commentDAO();
		cDAO.modifyShareComment(content,comment_idx);
		
		return null;
	}

	
}
