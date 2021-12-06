package com.deco.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.ActionForward;
import com.deco.Controller;

@WebServlet("*.json")
public class dataController extends Controller{
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse res) throws Exception{
		setInit(req, res);
		System.out.println(command);
		
		if(command.equals("/SearchUserEmail.json")){
			action = new searchUserEmailAction();
			forward = action.execute(req, res);
		
		}else if(command.equals("/SearchUserNickname.json")){
			action = new searchUserNicknameAction();
			forward = action.execute(req, res);
		
		}else if(command.equals("/SearchSubject.json")){
			action = new searchSubjectAction();
			forward = action.execute(req, res);
		}
		
		render(forward, req, res);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			doProcess(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			doProcess(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
