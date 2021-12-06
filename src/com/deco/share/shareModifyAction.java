package com.deco.share;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class shareModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : shareModifyAction_execute() 호출");
		
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
		
		//글번호에 해당하는 글 가져오기
	 	req.setAttribute("shareContent", new shareDAO().getShare(Integer.parseInt(idx)));
		
	 	ActionForward forward = new ActionForward("./share/shareModify.jsp",false);

		return forward;
	}

}
