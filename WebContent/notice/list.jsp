<%@page import="com.deco.bookmark.db.BookmarkDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.deco.user.userDAO"%>
<%@page import="com.deco.notice.db.noticeDTO"%>
<%@page import="com.deco.notice.db.noticeDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link href="./css/notice/list.css" rel="stylesheet">
<!-- bootstrap -->
<title>Insert title here</title>
</head>
<body>

	<%@ include file="../../main/header.jsp" %>
<div id="wrap">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js" charset="UTF-8"></script>
	<!-- <h1>WebContent/board/list.jsp</h1> -->

	
	<h1>공지사항</h1>
	<%
		if(session.getAttribute("user_num") != null) {
			user_num = (int) session.getAttribute("user_num");
		}
		/* List shareList = (ArrayList)request.getAttribute("shareList"); */
		List boardList = (ArrayList)request.getAttribute("noticeList");
		
		int pageSize = (int)request.getAttribute("pageSize");
		String pageNum = (String)request.getAttribute("pageNum");
		int cnt = (int)request.getAttribute("cnt");
		int currentPage = (int)request.getAttribute("currentPage");
		
		userDAO usDAO = new userDAO();
		String nickName = usDAO.getUserNickNameByNum(user_num);
		
		int adminCheck = usDAO.getAdminByNum(user_num);
	%>
	<%-- <h2> ITWILL 게시판 글목록 [총 : <%= cnt %>개] </h2>
	
	<h3><%= nickName %>님 환영합니다~!</h3> --%>

	<%if(adminCheck == 1){ %>
	<input id="write_btn" type="button" onclick="location.href='./noticeform.nt?user_num=<%=user_num%>'" value="글쓰기">
	<%-- <h3><a href="./noticeform.nt?user_num=<%=user_num%>">공지글쓰기</a></h3> --%>
	<%} %>

	<script type="text/javascript">
	/* 몇개씩 목록보기 */
		var pageNum = 1;
	
		function PageChange(){
	      pageSize = document.fr.pageChange.value;
	      location.href = "noticelist.nt?pageNum=" + pageNum + "&pageSize="+ pageSize;
	   }
	
	/* 게시판 검색 */
	function searchCheck() {
		if (document.form.condition.value == "")  {
			alert("검색어를 입력해 주세요.");
			return false;
		}
	}
	</script>
	
	<form name="fr">
	<select name="pageChange" id="pageChange" onchange="PageChange()">
		<option value="">몇개씩 보기</option>
		<option value="5">5개씩 보기</option>
		<option value="10">10개씩 보기</option>
		<option value="15">15개씩 보기</option>
		<option value="20">20개씩 보기</option>
	</select>
	</form>
	
	<br>
	
	<form name="form" class="search_fr" action="./noticelist.nt?pageNum=<%=pageNum %>&pageSize=<%=pageSize %>" method="post" onsubmit="return searchCheck()">
	<select name="opt">
                <option value="0">제목</option>
                <option value="1">내용</option>
                <option value="2">제목+내용</option>
                <option value="3">작성자</option>
    </select>
            <input type="text" size="20" name="condition"/>&nbsp;
            <input type="submit" value="검색"/>
	</form>
	<!--  -->
	<div class="clear"></div>
	
	<br>
	<table class="table table-hover">
		<thead>
		<tr>
			<th scope="col">글번호</th>
			<!-- <th scope="col">작성자</th> -->
			<th scope="col">닉네임</th>
			<th scope="col">제목</th>
			<th scope="col">작성일</th>
			<th scope="col">조회수</th>
			<th scope="col">즐겨찾기</th>
		</tr>
		</thead>
		<% 
		for(int i=0; i<boardList.size(); i++){
				noticeDTO nDTO = (noticeDTO)boardList.get(i);	
		%>
		<tbody>
		<tr>
			<td><%=nDTO.getIdx() %></td>
			<%-- <td><%=nDTO.getUser_num() %></td> --%>
			<td><%=usDAO.getUserNickNameByNum(nDTO.getUser_num()) %></td>
			<td>
			<a class="list_title" href="noticecontent.nt?idx=<%=nDTO.getIdx()%>&user_num=<%=user_num%>&pageNum=<%=pageNum%>&cnt=<%=cnt%>"><%=nDTO.getTitle()%></a>
			</td>
			<%-- <td><%=nDTO.getContent() %></td> --%>
			<td><%=nDTO.getCreate_at() %></td>
			<td><%=nDTO.getCount() %></td>
			<td>
			<%
				BookmarkDAO bmDAO = new BookmarkDAO();
				int result = bmDAO.ckBookmark(user_num, nDTO.getIdx(),1);
			%>
			<%if(result != 1){%>
			    <img src="./imgbm/bookmarkx.png" id="bm_img" height="30px" width="30px">
		    <%}else{ %>
			    <img src="./imgbm/bookmarko.png" id="bm_img" height="30px" width="30px">
		    <%} %>
			</td>
		</tr>
		</tbody>
		<% 
		}
		%>
	</table>
	
	<hr>
	<%
		//////////////////////////////////////////////////////////////////////
		// 페이지 처리 - 하단부 페이지 링크
		
		if(cnt != 0){	// 글이 있을때 표시
			// 전체 페이지수 계산
			// ex) 총 50개 -> 한 페이지당 10개씩 출력, 5개
			//	      총 57개 -> 한 페이지당 10개씩 출력, 6개
			int pageCount = cnt / pageSize+(cnt % pageSize == 0? 0 : 1);
			
			// 한 화면에 보여줄 페이지 번호의 개수 (페이지 블럭)
			int pageBlock = 5;
			
			// 페이지 블럭의 시작페이지 번호
			// ex) 1~10페이지 : 1, 11~20페이지 : 11, 21~30페이지 : 21
			int startPage = ((currentPage-1)/pageBlock) * pageBlock+1;
			
			// 페이지 블럭의 끝페이지 번호
			int endPage = startPage + pageBlock-1;
			
			if(endPage > pageCount){
				endPage = pageCount;
			}
			
			// 이전 (해당 페이지블럭의 첫번째 페이지 호출)
			if(startPage > pageBlock){
				%>
				<a href="noticelist.nt?pageNum=<%= startPage-pageBlock %>&pageSize=<%=pageSize%>">[이전]</a>
				<%
			}
			
			
			// 숫자 1....5
			for(int i=startPage; i<=endPage;i++){
				%>
				<a href="noticelist.nt?pageNum=<%=i %>&pageSize=<%=pageSize%>">[<%= i %>]</a>
				<%
			}
			
			// 다음 (기존의 페이지 블럭보다 페이지의 수가 많을때)
			if(endPage < pageCount){
				%>
				<a href="noticelist.nt?pageNum=<%= startPage+pageBlock %>&pageSize=<%=pageSize%>">[다음]</a>
				<%
			}
		}
		//////////////////////////////////////////////////////////////////////
	%>
	<hr>
	
</div>
</body>
</html>