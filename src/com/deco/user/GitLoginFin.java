package com.deco.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.deco.Action;
import com.deco.ActionForward;

public class GitLoginFin implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String baseURL = "https://github.com/login/oauth/access_token";
		
		GitLogin gitConfig = new GitLogin();
		String client_id = gitConfig.getCLIENT_CODE();
		String secret = gitConfig.getSECRET_CODE();
		String code = req.getParameter("code");
		
		String finURL = baseURL+"?client_id="+client_id+"&client_secret="+secret+"&code="+code;
		
		System.out.println(finURL);
		
		String PostResponse = gitConfig.sendPost(finURL);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(PostResponse);
		
		String access_token = (String)jsonObject.get("access_token"); 
		System.out.println("access_token ======> "+access_token);
		
		if(access_token != null){
			String apiURL = "https://api.github.com";
			
			JSONObject userData = (JSONObject)jsonParser.parse((String)gitConfig.sendGet(apiURL+"/user", access_token));
			
			JSONArray emailData = (JSONArray)jsonParser.parse((String)gitConfig.sendGet(apiURL+"/user/emails", access_token));
			
			System.out.println(userData);
			System.out.println(emailData);
			
			JSONObject userEmail = null;
			for(int i = 0; i < emailData.size(); i++){
				JSONObject temp = (JSONObject)emailData.get(i);
				boolean verified = (Boolean)temp.get("verified");
				boolean primary = (Boolean)temp.get("primary");
				
				if(verified && primary){
					userEmail = temp;
				}
			}
			
			if(userEmail != null){
				
				//해당 유저가 가입된 이메일인지 확인
				userDAO uDAO = new userDAO();
				boolean exists = uDAO.searchUserEmail((String)userEmail.get("email"));
				
				//없을 때는 회원가입
				if(!exists){
					userDTO uDTO = new userDTO();
					uDTO.setEmail((String)userEmail.get("email"));
					uDTO.setName((String)userData.get("login"));
					
					//not null colums 더미값 셋팅
					uDTO.setNickname("없음");
					uDTO.setPw("@g");
					uDTO.setAddr("없음");
					uDTO.setPhone("없음");
					uDTO.setMajor("없음");
					uDTO.setInter("없음");
					
					int flag = uDAO.insertSocialUser(uDTO);
					
					//에러 발생 시,
					if(flag == -5){
						ValueException(res,"무언가 잘못됐습니다!");
						return null; // null 반환
					}
					
					setSession(req, userEmail);
					
					return new ActionForward("./SocialJoin.us",true);
				}
				
				int setDataState = uDAO.getAdminByEmail((String)userEmail.get("email"));
				
				setSession(req, userEmail);
				
				if(setDataState == -10){
					return new ActionForward("./SocialJoin.us",true);
				}
				
			}else{
				return new ActionForward("./login.us", true);
			}
		}else{
			return new ActionForward("./login.us", true);
		}
		
		return new ActionForward("./main.us",true);
	}
	
	public void ValueException(HttpServletResponse res, String msg) throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('" + msg + "');");
		out.println("history.back()");
		out.println("</script>");
		
		out.close();
	}
	
	public void setSession(HttpServletRequest req, JSONObject userEmail){
		userDAO uDAO = new userDAO();
		int userNum = uDAO.getUserNumByEmail((String)userEmail.get("email"));
		
		HttpSession session = req.getSession();
		session.setAttribute("user_num", userNum);
	}
}
