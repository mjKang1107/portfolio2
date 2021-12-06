package com.deco.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.notice.db.noticeDAO;
import com.deco.notice.db.noticeDTO;

public class NoticeModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 전달된 파라미터값 idx저장
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// DB 객체 생성 후 getGoods(idx);
		noticeDAO nDAO = new noticeDAO();
		noticeDTO nDTO = nDAO.getContent(idx);
		
		// 저장
		request.setAttribute("nDTO", nDTO);
		
		// 페이지 이동(admin_goods_modify.jsp)
		ActionForward forward = new ActionForward("./notice/editModify.jsp", false);
		
		return forward;
	}

}
