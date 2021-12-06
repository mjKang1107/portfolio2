package com.deco.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class CancelAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		String email = req.getParameter("email");
		String pw = req.getParameter("pw");
		
		userDAO udao = new userDAO();
		int check = udao.re(email, pw);
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		if(check == 1){
			out.print("<script>");
			out.print("alert('비밀번호 오류!!!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
		if(check == -1){
			out.print("<script>");
			out.print("alert('로그인후 이용해주세요!!');");
			out.print("location.href='/ProjectDeCo/login.us';");
			out.print("</script>");
			out.close();
			
			return null;
		}
		
		//다시 일반유저 상태로 되돌림
		udao.setUserAuth(0, email);
		
		out.print("<script>");
		out.print("alert('Welcome back!!!');");
		out.print("location.href='/ProjectDeCo/main.us';");
		out.print("</script>");
		out.close();
		
		return null;
	}

	
}
