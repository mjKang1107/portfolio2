package com.deco.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class userLogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
			HttpSession session = req.getSession();
			session.invalidate();
			
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			
			out.print("<script>");
			out.print("alert('로그아웃 성공!');");
			out.print("location.href='./main.us';");
			out.print("</script>");
			out.close();
			
		return null;
	}

	
	
}
