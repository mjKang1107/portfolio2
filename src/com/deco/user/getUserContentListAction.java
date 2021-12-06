package com.deco.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.deco.like.likeDAO;
import com.deco.like.likeDTO;
import com.deco.notice.db.noticeDTO;
import com.deco.share.shareDAO;
import com.deco.share.shareDTO;

public class getUserContentListAction implements Action{

	HttpSession session;
	int user_num;
	shareDAO sDAO = new shareDAO();
	userDAO uDAO = new userDAO();
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		session = req.getSession();
		
		if(session.getAttribute("user_num") == null){
			return new ActionForward("./login.us", true);
		}

		user_num = (Integer)session.getAttribute("user_num");
		
		Map<String, List> AllList = new HashMap<String, List>();
		
		List<shareDTO> likeShare = getLikeShare();
		List<shareDTO> bookShare = getBookShare();
		List<shareDTO> userWriteShare = getWriteShare();
		List<noticeDTO> noticeList = getNoticeList();

		AllList.put("likeShare", likeShare);
		AllList.put("bookShare", bookShare);
		AllList.put("userWriteShare", userWriteShare);
		AllList.put("noticeList", noticeList);
		
		req.setAttribute("AllList", AllList);
		
		return new ActionForward("./user/myPage/myList.jsp", false);
	}
	
	private List<shareDTO> getLikeShare(){
		List<shareDTO> retList = sDAO.getUserLikeList(user_num);
		System.out.println("좋아요한 게시물 => " + retList);
		return retList;
	}
	
	private List<shareDTO> getBookShare(){
		List<shareDTO> retList = sDAO.getUserBookList(user_num);
		System.out.println("즐겨찾기한 게시물 => " + retList);
		return retList;
	}
	
	private List<shareDTO> getWriteShare(){
		List<shareDTO> retList = sDAO.getUserWriteList(user_num);
		System.out.println("본인이 쓴 게시물 => " + retList);
		return retList;
	}
	
	private List<noticeDTO> getNoticeList(){
		List<noticeDTO> retList = uDAO.getAdminNotice(user_num);
		return retList;
	}
	
}
