package com.deco.share;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class shareModifyUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		System.out.println("M : shareModifyUpdateAction_execute() 호출");
	
		//한글처리
		req.setCharacterEncoding("utf-8");
		
		String pageNum = req.getParameter("pageNum");
		String pageSize = req.getParameter("pageSize");
		String category = req.getParameter("category");
		
		//세션처리
		HttpSession session = req.getSession();
				
		int userNum = 0;
				
		if(session.getAttribute("user_num") == null){
			resp.sendRedirect("./shareList.sh");
			return null;
		} else {
			userNum = (int) session.getAttribute("user_num");
		}
	
		
		//파일 업로드
		ServletContext ctx = req.getServletContext();
		String realpath = ctx.getRealPath("/share/upload");
		
		int maxSize = 5 * 1024 * 1024;
		    	
		MultipartRequest multi 
		  = new MultipartRequest(
				  req,
				  realpath,
				  maxSize,
				  "UTF-8",
				  new DefaultFileRenamePolicy()				
				);
		
		System.out.println("파일 업로드 완료");
		
		String idx = multi.getParameter("contentNum");
		
		shareDTO sDTO = new shareDTO();
		
		 sDTO.setAnony(Integer.parseInt(multi.getParameter("anony")));
	     sDTO.setTitle(multi.getParameter("title"));
	   	 sDTO.setCategory(multi.getParameter("category"));
	     sDTO.setFile(multi.getFilesystemName("filename"));
	     sDTO.setContent(multi.getParameter("content"));
	     		  
	     //다중선택 배열로 받기
	     String[] tags = multi.getParameterValues("tag");
	     String tag = "";
	     for(int i=0;i<tags.length;i++){
	    	 tag += tags[i];
	    	 
	    	 if((tags.length-1) != i){
	    		 tag += ","; 
	    	 }
	     }
	     
	     System.out.println(tag);
	     
	     sDTO.setTag(tag);
	     sDTO.setIdx(Integer.parseInt(idx));
	     
	     //디비처리 
	     shareDAO sDAO = new shareDAO();
	     //글수정 메서드
	 	if(sDTO.getFile() != null){
	 		sDAO.modifyShareContentFile(sDTO);
		}
		else
		{
			System.out.println("====================================");
			sDAO.modifyShareContent(sDTO);
		}
	 	
	 	System.out.println("@@@@@@@@@@@@@@@@@@@################ " + category);
	     
	    ActionForward forward = new ActionForward("./shareContent.sh?pageNum="+pageNum+"&pageSize="+pageSize+"&contentNum="+idx+"&category="+category, true);
		
	    return forward;
		   
		
		
		
		
	}

}
