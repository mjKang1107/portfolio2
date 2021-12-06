package com.deco.share_comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class shareCommentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : shareCommentAction_execute() 호출");
		
		//한글처리
		req.setCharacterEncoding("utf-8");
				
		//세션처리
		HttpSession session = req.getSession();
		
		String pageNum = req.getParameter("pageNum");
		String pageSize = req.getParameter("pageSize");
		String category = req.getParameter("category");
		String idx = req.getParameter("contentNum");
						
		int userNum = 0;
						
		if(session.getAttribute("user_num") == null){
			resp.sendRedirect("./shareList.sh");
		} else {
			userNum = (int) session.getAttribute("user_num");
		}
		
		commentDTO cDTO = new commentDTO();
		
		cDTO.setShare_idx(Integer.parseInt(req.getParameter("contentNum")));
		cDTO.setUser_num(userNum);
		cDTO.setContent(req.getParameter("comment"));
		
		commentDAO cDAO = new commentDAO();
		cDAO.insertShareComment(cDTO);
		
		return new ActionForward("./shareContent.sh?pageNum="+pageNum+"&pageSize="+pageSize+"&contentNum="+idx+"&category="+category, true);
	}

	
	
}
