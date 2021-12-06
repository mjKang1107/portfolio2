package com.deco.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class SocialJoin implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		userDAO uDAO = new userDAO();
		userDTO uDTO = new userDTO();
		
		HttpSession session = req.getSession();
		int user_num = (Integer)session.getAttribute("user_num");
		uDTO = uDAO.getUserInfo(user_num);
		req.setAttribute("uDTO", uDTO);
		
		return new ActionForward("./user/join/socialJoin.jsp",false);
	}

}
