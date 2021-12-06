package com.deco.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;


public class UserInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("-0---------------------------wefwefwe-fwe-fw-e");
		
		HttpSession session = req.getSession();
		Integer user_num = Integer.parseInt(req.getParameter("user_num"));
		
		
		ActionForward forward = new ActionForward();
		if(user_num == null || user_num == -1){
			forward = new ActionForward("./login.us", true);
			return forward;
		}
		userDAO udao = new userDAO();
		userDTO udto = udao.info(user_num);
		
		req.setAttribute("udto", udto);
		
		forward = new ActionForward("./user/login/info.jsp", false);
		
		return forward;
	}

	
}
