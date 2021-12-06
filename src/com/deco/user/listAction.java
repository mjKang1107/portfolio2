package com.deco.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class listAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Integer user_num = Integer.parseInt(req.getParameter("user_num"));
		ActionForward forward = new ActionForward();
		
		userDAO loDAO = new userDAO();
		userDTO udto = loDAO.info(user_num);
		
		req.setAttribute("udto", udto);
		
		forward = new ActionForward("./user/login/update.jsp", false);
		
		// 현재 updateAction 페이지 리스트 페이지로 수정후 updateAction페이지 생성하여 정보 수정하게하는 객체 생성하여 정보수정
		
		
		return forward;
	}
}
