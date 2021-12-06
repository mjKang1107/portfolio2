package com.deco.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.ActionForward;
import com.deco.Controller;

@WebServlet("*.repo")
public class reportController extends Controller {
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse res) throws Exception{
		setInit(req, res);
		
		System.out.println(command);
		
		if(command.equals("/reportAction.repo")){
			action = new reportAction();
			forward = action.execute(req, res);
		
		}else if(command.equals("/haveUserReport.repo")){
			action = new haveUserReport();
			forward = action.execute(req, res);
		}
		
		render(forward,req,res);
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
