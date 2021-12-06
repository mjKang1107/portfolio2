package com.deco.team.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.ActionForward;
import com.deco.Controller;
import com.deco.team.talk.printTalkAction;
import com.deco.team.talk.updateTalkAction;

@WebServlet("*.tm")
public class teamMemberFrontController extends Controller {

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("C : teamMemberFrontControlle_doProcess()호출");

		setInit(req, resp);
		System.out.println(command);

		/////////////////////////////////////////////////////////

		if (command.equals("/joinTeamMember.tm")) {
			System.out.println("C : /joinTeamMember.tm 호출");
			action = new joinTeamMemberAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/deleteTeamMember.tm")) {
			System.out.println("C : /deleteTeamMember.tm 호출");
			action = new deleteTeamMemberAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/submitMember.tm")) {
			System.out.println("C : /submitMember.tm 호출");
			action = new submitMemberAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/teamPage.tm")) {
			System.out.println("C : /teamPage.tm 호출");
			action = new teamMemberMainAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/printSubmitMemberList.tm")) {
			System.out.println("C : /printSubmitMemberList.tm 호출");
			action = new printSubmitMemberListAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/printNoneSubmitMemberList.tm")) {
			System.out.println("C : /printNoneSubmitMemberList.tm 호출");
			action = new printNoneSubmitMemberListAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberList.tm")) {
			System.out.println("C : /memberList.tm 호출");
			forward = new ActionForward("./teamMember/memberListAjax.jsp", false);
		} else if (command.equals("/memberInfo.tm")) {
			System.out.println("C : /memberInfo.tm 호출");
			action = new memberInfoAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/joinTeam.tm")) {
			System.out.println("C : /joinTeam.tm 호출");
			action = new joinMemberAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/outMember.tm")) {
			System.out.println("C : /outMember.tm 호출");
			action = new outMemberAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/teamMemberTalk.tm")) {
			System.out.println("C : /teamMemberTalk.tm 호출");
			action = new teamMemberTalk();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updateTalk.tm")) {
			System.out.println("C : /updateTalk.tm 호출");
			action = new updateTalkAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/printTalk.tm")) {
			System.out.println("C : /printTalkAction.tm 호출");
			action = new printTalkAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/teamMemberCalendar.tm")) {
			System.out.println("C : /teamMemberCalendar.tm 호출");
			action = new teamMemberCalendarAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/insertSchedule.tm")) {
			System.out.println("C : /insertSchedule.tm 호출");
			action = new insertScheduleAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/readSchedule.tm")) {
			System.out.println("C : /readSchedule.tm 호출");
			action = new readScheduleAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		} else if (command.equals("/updateSchedule.tm")) {
			System.out.println("C : /updateSchedule.tm 호출");
			action = new updateScheduleAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/deleteSchedule.tm")) {
			System.out.println("C : /deleteSchedule.tm 호출");
			action = new deleteScheduleAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updateDateSchedule.tm")) {
			System.out.println("C : /updateDateSchedule.tm 호출");
			action = new updateDateScheduleAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/calendarView.tm")) {
			System.out.println("C : /calendarView.tm 호출");
			action = new teamMemberCalendarViewAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		/////////////////////////////////////////////////////////

		if (forward != null) {
			if (forward.isRedirect()) {
				resp.sendRedirect(forward.getURL());
				System.out.println("C : sendRedirect() 방식, " + forward.getURL() + "페이지 이동");
			} else { // false
				System.out.println(forward.getURL());
				RequestDispatcher dis = req.getRequestDispatcher(forward.getURL());

				dis.forward(req, resp);
				System.out.println("C : forward() 방식, " + forward.getURL());
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("C : teamMemberFrontControlle_doGet()호출");
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("C : teamMemberFrontControlle_doPost()호출");
		doProcess(req, resp);
	}

}
