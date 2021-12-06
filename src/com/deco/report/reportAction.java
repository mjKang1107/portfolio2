package com.deco.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.share.shareDAO;
import com.deco.user.getBody;

public class reportAction implements Action {

	private int flag = -1;
	private boolean isCnt = false;
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("신고 Action");
		
		JSONObject reqObj = getBody.getJsonObj(req);
		System.out.println("넘어온 데이터 : " + reqObj.toString());

		HttpSession session = req.getSession();
		int user_num = Integer.parseInt(session.getAttribute("user_num").toString());
		System.out.println(user_num);
		
		reportDTO rDTO = new reportDTO();
		rDTO.setUser_num(user_num);
		rDTO.setBody(reqObj);
		
		reportDAO rDAO = new reportDAO();
		flag = rDAO.insertReport(rDTO);
		
		shareDAO sDAO = new shareDAO();
		
		//후에 share 외에 다른 content_type의 신고 카운트 추가 예정
		// 1 => share 게시물 신고 카운트 +1
		switch (rDTO.getContent_type()) {
		case 1:
			isCnt = sDAO.reportCount(rDTO.getContent_num());
			break;
			
		default:
			break;
		}
		
		if(!isValid()){
			res.setStatus(400);
			return null;
		}
		
		res.setStatus(204);
		return null;
	}
	
	private boolean isValid(){
		if(flag == -1){
			return false;
		}
		if(!isCnt){
			return false;
		}
		
		return true;
	}
}
