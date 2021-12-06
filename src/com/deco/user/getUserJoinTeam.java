package com.deco.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.team.teamDTO;

public class getUserJoinTeam implements Action {

	private HttpSession session;
	private int user_num;
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		getSession(req);
		if(!isSession()){
			return new ActionForward("./login.us", true);
		}
		
		getUser_num(req);
		
		userDAO uDAO = new userDAO();
		List<List<teamDTO>> userTeamList = uDAO.getUserJoinTeam(user_num);
		
		req.setAttribute("TeamList", userTeamList);
		
		return new ActionForward("./user/myPage/joinTeamList.jsp", false);
	}
	
	private void getSession(HttpServletRequest req){
		session = req.getSession();
	}
	
	private boolean isSession() {
		if(session.getAttribute("user_num") == null){
			return false;
		}
		
		return true;
	}

	private void getUser_num(HttpServletRequest req) {
		user_num = Integer.parseInt(session.getAttribute("user_num").toString());
	}
}
