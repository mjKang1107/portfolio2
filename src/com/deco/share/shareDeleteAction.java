package com.deco.share;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.share_comment.commentDAO;

public class shareDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : shareDeleteAction_execute() 호출");
		
		//한글처리
		req.setCharacterEncoding("utf-8");
		
		//세션처리
		HttpSession session = req.getSession();
		
		int userNum = 0;
		
		if(session.getAttribute("user_num") == null){
			resp.sendRedirect("./shareList.sh");
			return null;
		} else {
			userNum = (int) session.getAttribute("user_num");
		}
		
		String idx = req.getParameter("contentNum");
		String pageNum = req.getParameter("pageNum");
		String pageSize = req.getParameter("pageSize");
		String category = req.getParameter("category");
		
		shareDAO sDAO = new shareDAO();
		
		if(userNum == sDAO.getWriteUserNum(Integer.parseInt(idx))) {
			
			commentDAO cDAO = new commentDAO();
			cDAO.deleteCommentAll(Integer.parseInt(idx));
			
			sDAO.deleteShareContent(idx);
		}
		
		ActionForward forward = new ActionForward("./shareList.sh?pageNum="+pageNum+"&pageSize="+pageSize+"&category="+category, true);
		
		return forward;
	}

}
