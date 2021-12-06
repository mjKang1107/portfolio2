package com.deco.team.talk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.user.userDAO;

public class updateTalkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M : updateTalkAction_execute() 호출");
		
		HttpSession session = req.getSession();
		
		int user_num = 0;
		if(session.getAttribute("user_num") == null){
			resp.sendRedirect("");
		} else {
			user_num = (int)session.getAttribute("user_num");
		}
		
		// 업로드한 가상 경로
		String savePath ="teamMember/talk";
		// 파일이 업로드된 경로   
		ServletContext ctx = req.getServletContext();
		String sDownloadPath = ctx.getRealPath(savePath);
		   
		System.out.println("upload 폴더의 실제주소(서버안에 있는 실제주소)" + sDownloadPath);
		
		
		String team_idx = req.getParameter("team_idx");
		String content = req.getParameter("content");
		
		String nickname = new userDAO().getUserNickNameByNum(user_num);
		
		talkDAO tdao = new talkDAO();
		talkDTO tdto = tdao.getTalkInfo(Integer.parseInt(team_idx));

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date time = new Date();

		String time1 = format1.format(time);

		FileReader fReader = new FileReader(sDownloadPath + "\\" + tdto.getFilename() + ".txt");
		BufferedReader br = new BufferedReader(fReader);
		String s;
		String str = "";
		do {
			s = br.readLine();
			if (s != null) {
				str += s;
			}
		} while (s != null);

		br.close();
		fReader.close();

		FileWriter fw = null;
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		fw = new FileWriter(sDownloadPath + "\\" + tdto.getFilename() + ".txt");

		str += "[" + nickname + "::" + content + "::" + time1 + "]";
		fw.write(str);

		fw.close();
		br1.close();
		
		return null;
	}

}
