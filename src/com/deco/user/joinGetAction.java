package com.deco.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;

public class joinGetAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = new ActionForward("./user/join/join.jsp", false);
		return forward;
	}
}
