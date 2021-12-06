package com.deco.bookmark.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.bookmark.db.BookmarkDAO;
import com.deco.bookmark.db.BookmarkDTO;

public class bmCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("C : bmCheckAction_execute()호출");
		
		BookmarkDTO bmDTO = new BookmarkDTO();
		bmDTO.setUser_num(Integer.parseInt(req.getParameter("user_num")));
		bmDTO.setContent_num(Integer.parseInt(req.getParameter("content_num")));
		
		BookmarkDAO bmDAO = new BookmarkDAO();
		
		int result = bmDAO.checkBookmark(bmDTO);
		
		return null;
	}

}
