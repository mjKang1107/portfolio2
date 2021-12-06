package com.deco.bookmark.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.ActionForward;
import com.deco.Controller;

@WebServlet("*.bm")
public class bookmarkFrontController extends Controller{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : bookmarkFrontController_Process() 호출");
		setInit(request, response);
		/********************************** 1.페이지 주소 파싱 ********************************************/
		System.out.println(command);
		System.out.println("C : 1. 페이지 주소 파싱 완료");
		/********************************** 1.페이지 주소 파싱 ********************************************/
		
		
		/********************************** 2.페이지 주소 매핑(연결) ********************************************/

		
/*		if(command.equals("/Main.nt")){
			forward = new ActionForward("./notice/main.jsp", false);
		}

		//////////////////////////////////////////////////////
		
		else if(command.equals("/noticemodify.nt")){
			action = new NoticeModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/

		if(command.equals("/bkAddAction.bm")){
			action = new bkAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/bkDeleteAction.bm")){
			action = new bkDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*else if(command.equals("/addBookmark.bm")){
			int idx = Integer.parseInt(request.getParameter("idx"));
			response.getWriter().write();
			return;
		}*/
		else if(command.equals("/addBookmark.bm")){
			action = new bkAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/bmCheck.bm")){
			action = new bmCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println("C : 2. 페이지 주소 매핑 완료");
		/********************************** 2.페이지 주소 매핑(연결) ********************************************/
		
		
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
		System.out.println("C : bookmarkFrontController_doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : bookmarkFrontController_doPost() 호출");
		doProcess(request, response);
	}
	
}
