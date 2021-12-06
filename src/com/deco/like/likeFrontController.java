package com.deco.like;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Controller;
import com.deco.like.likeAddAction;
import com.deco.like.likeDeleteAction;

@WebServlet("*.lk")
public class likeFrontController extends Controller {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : likeFrontController_Process() 호출");
		setInit(request, response);
		/********************************** 1.페이지 주소 파싱 ********************************************/
		System.out.println(command);
		System.out.println("C : 1. 페이지 주소 파싱 완료");
	
		
		if(command.equals("/likeAddAction.lk")){
			action = new likeAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(command.equals("/likeDeleteAction.lk")){
			action = new likeDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("C : 2. 페이지 주소 매핑 완료");
		
		/********************************** 3.페이지 주소 이동 ********************************************/
		if(forward != null){	// 페이지 이동정보가 있다.
			if(forward.isRedirect()){	// true
				response.sendRedirect(forward.getURL());
				System.out.println("C : sendRedirect() 방식, " + forward.getURL() + "페이지 이동");
			}else{	// false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getURL());
				dis.forward(request, response);
				System.out.println("C : forward() 방식, " + forward.getURL() + "페이지 이동");
			}
			System.out.println("C : 3. 페이지 주소 이동 완료");
		}
		System.out.println("C : 3. 페이지 주소 이동 X (컨트롤러 이동 X)");
		/********************************** 3.페이지 주소 이동 ********************************************/
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : likeFrontController_doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : likeFrontController_doPost() 호출");
		doProcess(request, response);
		
	
	}
}