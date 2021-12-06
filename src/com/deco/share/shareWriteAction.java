package com.deco.share;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class shareWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//한글처리
		req.setCharacterEncoding("utf-8");
		
		//세션제어
		HttpSession session = req.getSession();
		int user_num = 0;
		
		if(session.getAttribute("user_num") == null){
			resp.sendRedirect("./shareList.sh");
		} else {
			user_num = (int) session.getAttribute("user_num");
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
		
		shareDTO sDTO = new shareDTO();
		
		 // sDTO.setUser_num(user_num);
		 sDTO.setUser_num(user_num);
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
	     
	     //디비처리 
	     shareDAO sDAO = new shareDAO();
	     sDAO.insertShare(sDTO);
	     
	     //페이지이동
	     ActionForward forward = new ActionForward("./shareList.sh",true);
	 
		return forward;
	}

}
