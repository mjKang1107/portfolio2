package com.deco.bookmark.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.bookmark.db.BookmarkDAO;
import com.deco.bookmark.db.BookmarkDTO;

public class bkAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("C : bkAddAction_execute()호출");
		
		BookmarkDTO bmDTO = new BookmarkDTO();
		bmDTO.setUser_num(Integer.parseInt(request.getParameter("user_num")));
		bmDTO.setContent_num(Integer.parseInt(request.getParameter("content_num")));
		bmDTO.setType(Integer.parseInt(request.getParameter("type")));
		
		BookmarkDAO bmDAO = new BookmarkDAO();
		
		int result = bmDAO.checkBookmark(bmDTO);
		
		if(result == 1){
			bmDAO.deleteBookmark(bmDTO);
		}else if(result == 0){
			bmDAO.addBookmark(bmDTO);
		}
		
		request.setAttribute("result", result);

		return null;
	}

}
