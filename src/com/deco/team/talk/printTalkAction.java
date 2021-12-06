package com.deco.team.talk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;

public class printTalkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		System.out.println("M : printTalkAction_execute() 호출");
   
		// 업로드한 가상 경로
		String savePath ="teamMember/talk";
		// 파일이 업로드된 경로   
		ServletContext ctx = req.getServletContext();
		String sDownloadPath = ctx.getRealPath(savePath);
		   
		System.out.println("upload 폴더의 실제주소(서버안에 있는 실제주소)" + sDownloadPath);
		   
		
		String team_idx = req.getParameter("team_idx");
		talkDAO tdao = new talkDAO();
		talkDTO tdto = tdao.getTalkInfo(Integer.parseInt(team_idx));
		FileReader fReader = new FileReader(sDownloadPath + "\\" + tdto.getFilename() + ".txt");
		BufferedReader br = new BufferedReader(fReader);
		String s = new String("");
		String write_nickname = "";
		String content = "";
		String date = "";
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		while ((s = br.readLine()) != null) {
			
			String[] data = s.split("]");

			for(int i=0; i<data.length; i++) {
	 			String[] Info = data[i].replace("[", "").toString().split("::");
	 			
	 			write_nickname = Info[0];
				content = Info[1];
				date = Info[2];
				
				out.println("<div class='talk' id='nick"+write_nickname+"'>");
				out.println("<div class='nickname'>"+write_nickname+"</div>");
				out.println("<div class='content'>"+content+"</div>");
				out.println("<div class='date'>"+date+"</div>");
				out.println("</div>");
				out.println("<br>");
			}
		} 
		out.println("<br id='lastline'>");
		
		out.close();
		br.close();
		fReader.close();
		
		return null;
	}

}
