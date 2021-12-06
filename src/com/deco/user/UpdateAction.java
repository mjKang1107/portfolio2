package com.deco.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class UpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		int user_num = Integer.parseInt(req.getParameter("user_num"));		
		ActionForward forward = new ActionForward();
		
		userDAO udao = new userDAO();
		userDTO udto = new userDTO();
		
		
		udto.setPw(req.getParameter("pw"));
		udto.setName(req.getParameter("name"));
		udto.setNickname(req.getParameter("nickname"));
		udto.setAddr(req.getParameter("addr"));
		udto.setPhone(req.getParameter("phone"));
		udto.setMajor(req.getParameter("major"));
		 
		userDAO lodao = new userDAO();
		int check = lodao.update(udto, user_num);
		
		System.out.println(check);
		
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		if(check == 0){
			
			out.print("<script>");
			out.print("alert('비밀번호 오류!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
			
		}else if(check == -1){
			out.print("<script>");
			out.print("alert('잘못된 접근입니다.');");
			out.print("location.href='./login.us';");
			out.print("</script>");
			out.close();
			
			return null;
		}
		out.print("<script>");
		out.print("alert('수정완료!');");
		out.print("location.href='./main.us';");
		out.print("</script>");
		out.close();
		
		return null;
	}

	
}
