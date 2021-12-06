package com.deco.notice.action;

import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.notice.db.noticeDAO;
import com.deco.notice.db.noticeDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// 파일업로드
		// upload 폴더 생성
		//request.getRealPath("/upload");
		ServletContext ctx = request.getServletContext();
		String realpath = ctx.getRealPath("/notice/upload");
		
		int maxSize = 5 * 1024 * 1024;
		
		MultipartRequest multi
			= new MultipartRequest(
					request,
					realpath,
					maxSize,
					"UTF-8",
					new DefaultFileRenamePolicy()
				);
		
		System.out.println("M : 파일 업로드 완료!");
		
		// 전달된 정보 (파라미터 저장)
		noticeDTO nDTO = new noticeDTO();
		int user_num = Integer.parseInt(multi.getParameter("user_num"));
		nDTO.setUser_num(user_num);
		nDTO.setTitle(multi.getParameter("title"));
		nDTO.setContent(multi.getParameter("content"));
		
		// 서버에 업로드된 파일이름을 저장
		nDTO.setFile(multi.getFilesystemName("file"));
		
		noticeDAO nDAO = new noticeDAO();
		nDAO.insertNotice(nDTO);
		
		// 페이지 이동
		ActionForward forward = new ActionForward("./noticelist.nt", true);

		return forward;
	}

}
