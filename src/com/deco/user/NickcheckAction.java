package com.deco.user;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class NickcheckAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		String nickname = req.getParameter("nickname");
		int user_num = Integer.parseInt(req.getParameter("user_num"));
		userDAO udao = new userDAO();
		int data = udao.nickcheck(nickname, user_num);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		out.print(data);
		
		out.close();
		
		
		return null;
	}


}