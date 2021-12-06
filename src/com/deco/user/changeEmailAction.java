package com.deco.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;

public class changeEmailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		userDTO uDTO = new userDTO();
		uDTO.setEmail(getBody.getReqData(req));
		uDTO.setEmail_auth(new RandomCode().getCode(6));
		
		new Thread(){

			@Override
			public void run() {
				sendMailAuthcode.sendCode(uDTO);
			}
		}.start();

		userDAO uDAO = new userDAO();
		
		//1은 정상 발급, -1은 에러
		int flag = uDAO.changeEamilCode(uDTO);
		
		if(flag == -1){
			res.setStatus(400);
			return null;
		}
		
		res.setStatus(200);
		return null;
	}
	
}
