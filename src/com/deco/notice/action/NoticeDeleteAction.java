package com.deco.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.notice.db.noticeDAO;

public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
System.out.println("M :NoticeDeleteAction_execute() 호출");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		noticeDAO nDAO = new noticeDAO();
		
		nDAO.deleteNotice(idx);;
		
		// 페이지 이동(./AdminGoodsList.ag)
		ActionForward forward = new ActionForward("./noticelist.nt", true);
	
		return forward;
	}

}
