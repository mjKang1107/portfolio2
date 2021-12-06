package com.deco.share;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.ActionForward;
import com.deco.Controller;
import com.deco.share_comment.shareCommentAction;
import com.deco.share_comment.shareCommentDeleteAction;
import com.deco.share_comment.shareCommentModifyUpdateAction;

@WebServlet("*.sh")
public class shareFrontController extends Controller {
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("C : shareFrontControlle_doProcess()호출");
		
		setInit(req, resp);
		System.out.println(command);
		
		/////////////////////////////////////////////////////////
		
		if (command.equals("/shareWrite.sh")){
			System.out.println("C : /share/shareWrite.sh 호출");
				forward = new ActionForward("./share/writeShare.jsp", false);
		} else if(command.equals("/shareWriteAction.sh")){
			System.out.println("C : /shareWriteAction.sh 호출");
			action = new shareWriteAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/shareList.sh")) {
			System.out.println("C : /shareList.sh 호출");
			action = new shareListAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/shareContent.sh")){
			System.out.println("C : /shareContent.sh 호출");
			action = new shareContentAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/shareContentDelete.sh")){
			System.out.println("C : /shareContentDelete.sh 호출");
			action = new shareDeleteAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/shareContentModify.sh")){
			System.out.println("C : /shareContentModify.sh 호출");
			action = new shareModifyAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/shareContentModifyUpdate.sh")){
			System.out.println("C : /shareContentModifyUpdate.sh 호출");
			action = new shareModifyUpdateAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}else if(command.equals("/filedown.sh")){
			forward = new ActionForward("./share/file_down.jsp",false);
			
		}else if(command.equals("/shareCommentAction.sh")){
			System.out.println("C : /shareCommentAction.sh 호출");
			action = new shareCommentAction();
			try {
			forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/shareCommentDeleteAction.sh")){
			System.out.println("C : /shareCommentDeleteAction.sh 호출");
			action = new shareCommentDeleteAction();
			try {
			forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/shareCommentModifyUpdateAction.sh")){
			System.out.println("C : /shareCommentModifyUpdateAction.sh 호출");
			action = new shareCommentModifyUpdateAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/////////////////////////////////////////////////////////
		
		if(forward != null){
			if(forward.isRedirect()){
				resp.sendRedirect(forward.getURL());
				System.out.println("C : sendRedirect() 방식, " + forward.getURL() + "페이지 이동");
			}else{ //false
				System.out.println(forward.getURL());
				RequestDispatcher dis = req.getRequestDispatcher(forward.getURL());
				
				dis.forward(req, resp);
				System.out.println("C : forward() 방식, " + forward.getURL());
			}
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("C : shareFrontControlle_doGet()호출");
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("C : shareFrontControlle_doPost()호출");
		doProcess(req, resp);
	}

	
	
}
