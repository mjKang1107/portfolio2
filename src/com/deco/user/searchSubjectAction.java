package com.deco.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.deco.Action;
import com.deco.ActionForward;

public class searchSubjectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String nowSearchWord = getBody.getReqData(req);

		//DB 호출
		subjectDAO subDAO = new subjectDAO();
		JSONArray result = subDAO.searchSubject(nowSearchWord);
		
		req.setAttribute("SearchResult", result);
		return new ActionForward("./user/join/resultSubjectSearch.jsp", false);
	}

}
