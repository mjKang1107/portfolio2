package com.deco.like;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.share.shareDTO;
import com.deco.user.getBody;

public class likeAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
			
		int user_num = 0;
				
		if(session.getAttribute("user_num") != null){
			user_num = (int) session.getAttribute("user_num");
		}
		
		String reqData = getBody.readBody(request);
		System.out.println(reqData);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject reqObj = (JSONObject)jsonParser.parse(reqData);
		
		//System.out.println("content_num : " + reqObj.get("content_num"));
		
		int content_num = Integer.parseInt(reqObj.get("content_num").toString());
		int content_type = Integer.parseInt(reqObj.get("content_type").toString());
		
		likeDTO lDTO = new likeDTO();
		
		lDTO.setUser_num(user_num);
		lDTO.setContent_num(content_num);
		lDTO.setContent_type(content_type);
		
		System.out.println(user_num);
		
		likeDAO lDAO = new likeDAO();
		int result = lDAO.checkLike(lDTO);
	
		if(result == 0){
			lDAO.addLike(lDTO);
		}
	
		ActionForward forward = new ActionForward("./shareList.sh", true);
			
		
		return forward;
	}

	
	
	
}
