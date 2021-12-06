package com.deco.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class EmailAuthAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String code = req.getParameter("code");
		
		HttpSession session = req.getSession();
		
		int user_num = (Integer)session.getAttribute("user_num");
		
		userDAO uDAO = new userDAO();
		
		userDTO uDTO = uDAO.getUserInfo(user_num);
		int flag = uDAO.getAuthEmail(code, uDTO.getEmail());
		
		if(flag == -1){
			ValueException(res, "무언가 잘못됐습니다!");
			return null;
		}
		if(flag == 0){
			ValueException(res, "인증코드가 틀립니다!");
			return null;
		}
		
		//인증된 일반유저는 0으로 설정
		uDAO.setUserAuth(0, uDTO.getEmail());
		
		return new ActionForward("./main.us", true);
	}
	
	public void ValueException(HttpServletResponse res, String msg) throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('" + msg + "');");
		out.println("history.back()");
		out.println("</script>");
		
		out.close();
	}
	
}
