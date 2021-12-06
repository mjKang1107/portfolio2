package com.deco.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class joinPostAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = null;
		
		req.setCharacterEncoding("utf-8");

		userDTO uDTO = new userDTO();
		uDTO.setReq(req);
		uDTO.setEmail_auth(new RandomCode().getCode(6));
		
		if(!isValid(req,uDTO)){
			ValueException(res,"정보입력이 잘못됐습니다! (서로 다른 비밀번호, 정보 입력 등..)");
			return forward; // null 반환
		}
		
		new Thread(){

			@Override
			public void run() {
				sendMailAuthcode.sendCode(uDTO);
			}
		}.start();

		userDAO uDAO = new userDAO();
		int flag = uDAO.insertUser(uDTO);
		
		//에러 발생 시,
		if(flag == -5){
			ValueException(res,"무언가 잘못됐습니다!");
			return forward; // null 반환
		}
		
		//인증할 유저 email req영역에 속성으로 저장
		//req.setAttribute("email", uDTO.getEmail());
		setSession(req, uDTO.getEmail());
		forward = new ActionForward("./emailAuth.us", true);
		return forward;
	}
	
	private boolean isValid(HttpServletRequest req, userDTO uDTO){
		int majorLen = uDTO.getMajor().split(",").length;
		System.out.println(majorLen);
		if(!req.getParameter("pw").equals(req.getParameter("pw2"))){
			return false;
		}
		if(5 < majorLen || majorLen < 1){
			return false;
		}
		
		//패스워드 공백
		if(uDTO.getPw().indexOf(32) != -1){
			return false;
		}
		
		return true;
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
	
	public void setSession(HttpServletRequest req, String userEmail){
		userDAO uDAO = new userDAO();
		int userNum = uDAO.getUserNumByEmail(userEmail);
		
		HttpSession session = req.getSession();
		session.setAttribute("user_num", userNum);
	}
}
