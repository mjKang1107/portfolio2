package com.deco.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.notice.db.noticeDAO;
import com.deco.notice.db.noticeDTO;

public class NoticeModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 전달된 파라미터값 idx저장
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 전달된 수정할 정보를 저장(DTO)
		// 폼태그에서 전달되는 정보
		// title, content, 
		noticeDTO nDTO = new noticeDTO();
		
		nDTO.setTitle(request.getParameter("title"));
		nDTO.setContent(request.getParameter("content"));
		
		// DAO 객체 -> modifyGoods(DTO);
		noticeDAO nDAO = new noticeDAO();
		nDAO.modifyContent(nDTO, idx);
		
		// 페이지 이동(./AdminGoodsList.ag)
		ActionForward forward = new ActionForward("./noticelist.nt", true);
		
		return forward;
	}

}
