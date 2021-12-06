package com.deco.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class DeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		HttpSession session = req.getSession();
		
		String email = (String) req.getParameter("email");
		String pw = (String) req.getParameter("pw");
		userDAO udao = new userDAO();
		int check = udao.delete(email, pw);
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		if(check == 1){
			out.print("<script>");
			out.print("alert('비밀번호가 일치하지 않습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
		else if(check == -1){
			out.print("<script>");
			out.print("alert('존재하지 않는 회원입니다.');");
			out.print("location.href='/ProjectDeCo/login.us';");
			out.print("</script>");
			out.close();
			return null;
		}
		
		out.print("<script>");
		out.print("alert('한달뒤에 뵙겠습니다.');");
		out.print("location.href='/ProjectDeCo/main.us';");
		out.print("</script>");
		out.close();
		return null;
	}
	
	
}
