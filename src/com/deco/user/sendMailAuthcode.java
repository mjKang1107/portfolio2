package com.deco.user;

import javax.activation.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class sendMailAuthcode {
	public static boolean sendCode(userDTO uDTO){
		boolean success = false;
		
		String sender = "DeCo_Auth@gamil.com";
		String receiver = uDTO.getEmail();
		System.out.println(uDTO.getEmail());
		String subject = "DeCo - 코드로 인증해 주세요.";
		String content = "인증코드 : " + uDTO.getEmail_auth();

		try {
			Properties properties = System.getProperties();
			properties.put("mail.smtp.starttls.enable", "true"); // gmail은 무조건 true 고정
			properties.put("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 주소
			properties.put("mail.smtp.auth", "true"); // gmail은 무조건 true 고정
			properties.put("mail.smtp.port", "587"); // gmail 포트
			Authenticator auth = new GoogleAuthentication();
			Session s = Session.getDefaultInstance(properties, auth);
			//Session s = Session.getdefultInstance(properties, auth);
			Message message = new MimeMessage(s);
			Address sender_address = new InternetAddress(sender);
			Address receiver_address = new InternetAddress(receiver);
			message.setHeader("content-type", "text/html;charset=UTF-8");
			message.setFrom(sender_address);
			message.addRecipient(Message.RecipientType.TO, receiver_address);
			message.setSubject(subject);
			message.setContent(content, "text/html;charset=UTF-8");
			message.setSentDate(new java.util.Date());
			Transport.send(message);
			
			success = true;
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}
}
