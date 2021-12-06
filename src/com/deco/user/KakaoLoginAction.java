package com.deco.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.deco.Action;
import com.deco.ActionForward;

public class KakaoLoginAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		KakaoLogin kakao = new KakaoLogin();
		
		String code = req.getParameter("code");
		
		String body = kakao.getToken(code);
		
		//JSON형식으로 변환
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(body);
		
		String resData = kakao.sendGet("https://kapi.kakao.com/v2/user/me", (String)jsonObj.get("access_token"));
		
		JSONObject dataObj = (JSONObject) jsonParser.parse(resData);
		
		if(dataObj.get("kakao_account") == null){
			ValueException(res,"이메일 동의해주세요!");
			return null;
		}
		
		JSONObject accountObj = (JSONObject) jsonParser.parse(dataObj.get("kakao_account").toString());
		JSONObject profileObj = (JSONObject) jsonParser.parse(accountObj.get("profile").toString());
		
		String userEmail = (String)accountObj.get("email");
		String nickname = (String)profileObj.get("nickname");
		
		System.out.println(userEmail+" : "+nickname);
		
		 if(userEmail != null){
				
				//해당 유저가 가입된 이메일인지 확인
				userDAO uDAO = new userDAO();
				boolean exists = uDAO.searchUserEmail(userEmail);
				
				//없을 때는 회원가입
				if(!exists){
					userDTO uDTO = new userDTO();
					uDTO.setEmail(userEmail);
					uDTO.setName(nickname);
					
					//not null colums 더미값 셋팅
					uDTO.setNickname("없음");
					uDTO.setPw("@k");
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
				
				int setDataState = uDAO.getAdminByEmail(userEmail);
				
				setSession(req, userEmail);
				
				if(setDataState == -10){
					return new ActionForward("./SocialJoin.us",true);
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
	
	public void setSession(HttpServletRequest req, String userEmail){
		userDAO uDAO = new userDAO();
		int userNum = uDAO.getUserNumByEmail(userEmail);
		
		HttpSession session = req.getSession();
		session.setAttribute("user_num", userNum);
	}
}
