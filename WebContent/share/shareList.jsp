<%@page import="com.deco.bookmark.db.BookmarkDAO"%>
<%@page import="com.deco.share.shareDAO"%>
<%@page import="com.deco.user.userDAO"%>
<%@page import="com.deco.share.shareDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="shareHtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<!-- bootstrap -->
<link href="./share/css/list.css" rel="stylesheet">
<link href="./share/css/side.css" rel="stylesheet"> 
<link href="./share/css/button.css" rel="stylesheet"> 
<title>정보공유</title>
<link rel="stylesheet" href="./share/css/share.css">
</head>
<body class="">
<%@ include file="../../main/header.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js" charset="UTF-8"></script>

	<%
		List shareList = (ArrayList)request.getAttribute("shareList");
	
		int usernum = 0;
		if(session.getAttribute("user_num") != null) {
			usernum = (int) session.getAttribute("user_num");
		}
	
		int pageSize = (int)request.getAttribute("pageSize");
		String pageNum = (String)request.getAttribute("pageNum");
		String category = request.getParameter("category");
		
	%>
	<nav>
  <div class="menu-btn">
    <div class="line line--1"></div>
    <div class="line line--2"></div>
    <div class="line line--3"></div>
  </div>
  <div class="nav-links">
  	<a href="./shareList.sh?pageNum=<%=pageNum%>&pageSize=<%=pageSize%>" class="link">전체보기</a>
	<a href="./shareList.sh?pageNum=<%=pageNum%>&pageSize=<%=pageSize%>&category=Tips" class="link">Tips</a>
	<a href="./shareList.sh?pageNum=<%=pageNum%>&pageSize=<%=pageSize%>&category=Conference" class="link">컨퍼런스</a>
	<a href="./shareList.sh?pageNum=<%=pageNum%>&pageSize=<%=pageSize%>&category=Company" class="link">회사추천</a>
	<a href="./shareList.sh?pageNum=<%=pageNum%>&pageSize=<%=pageSize%>&category=Academy" class="link">학원추천</a>
	<a href="./shareList.sh?pageNum=<%=pageNum%>&pageSize=<%=pageSize%>&category=HowTo" class="link">How to</a>
  </div>
</nav>
<div class="inform"> 

	<script type="text/javascript">
	function changeBoardSize(){
		pageSize = document.fr.boardSize.value;
		location.href = "./shareList.sh?pageNum=<%=pageNum %>&pageSize="+pageSize;
	}
	
	function searchCheck() {
		if (document.form.condition.value == "")  {
			alert("검색어를 입력해 주세요.");
			return false;
		}
	}
	</script>
	
	<div class="wrap">
	<h1>정보공유 글보기</h1>
	<form name="fr">
		<select id="boardSize" class="pageChange" onchange="changeBoardSize()" name="changePageSize">
			<option>n개씩 보기</option>
			<option value="5">5개씩 보기</option>
			<option value="10">10개씩 보기</option>
			<option value="15">15개씩 보기</option>
			<option value="20">20개씩 보기</option>
		</select>
	</form>	
		<form name="form"
			action="./shareList.sh?pageNum=<%=pageNum%>&pageSize=<%=pageSize%>"
			method="post" onsubmit="return searchCheck()" class="search_fr">
				<select name="opt">
					<option value="0">제목</option>
					<option value="1">내용</option>
					<option value="2">제목+내용</option>
					<option value="3">작성자</option>
				</select>
			<input type="text"name="condition" id="search_fr"/>&nbsp; 
			<input type="submit" value="검색" class="write-btn" style="margin-top:0px;"/>
		</form>
		<div class="clear"></div>
		<br>
		<script type="text/javascript">
			function share_write_userCheck() {

				var user_num =<%=usernum%>;
			
			if (user_num == 0){
				if(confirm("로그인이 필요합니다. 로그인 하시겠습니까?")){
					location.href = "./login.us";
				}
			} else {
				location.href = './shareWrite.sh';
			}
		}
	</script>
	
	<div class="clear"></div>
	<table class="table table-hover">
	<thead>
		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>카테고리</th>
			<th>즐겨찾기</th>
			<th>좋아요</th> <!-- 좋아요  -->
		</tr>
		</thead>
	<%for(int i=0; i<shareList.size(); i++){ 
		shareDTO sdto = (shareDTO) shareList.get(i);

	%>
	<tbody>
		<tr>
			<td><%=sdto.getIdx() %></td>
 			<td><a href="./shareContent.sh?pageNum=<%=pageNum%>&pageSize=<%=pageSize%>&contentNum=<%=sdto.getIdx()%>&category=<%=category%>">
			<%=sdto.getTitle() %></a></td>
			<%if(sdto.getAnony()==1){%>
				<td>
				<%if(user_num == sdto.getUser_num()){ %>
					<%=new userDAO().getUserNickNameByNum(sdto.getUser_num())%>-
				<%} %>
				익명</td>
			<%} else {%>
			<td><%=new userDAO().getUserNickNameByNum(sdto.getUser_num())%></td>
			<%} %>
			<td><%=sdto.getCreate_at() %></td>
			<td><%=sdto.getRead_cnt() %></td>
			<td><%=sdto.getCategory()%></td>
			<td>
			<%
				BookmarkDAO bmDAO = new BookmarkDAO();
				int result = bmDAO.ckBookmark(user_num, sdto.getIdx(),2);
			%>
			<%if(result != 1){%>
			    <img src="./imgbm/bookmarkx.png" id="bm_img" height="30px" width="30px">
		    <%}else{ %>
			    <img src="./imgbm/bookmarko.png" id="bm_img" height="30px" width="30px">
		    <%} %>
			</td>
			<td><%=sdto.getLike_()%></td>  <!-- 좋아요  -->
		</tr>
		</tbody>
	<%} %>
	</table>
	<hr>
	
	<%
	shareDAO sdao = new shareDAO();
	
	int cnt = sdao.numOfShare();
	int currentpage = Integer.parseInt(pageNum);
	
	if(cnt != 0){
	
		int pageCount = cnt/pageSize + (cnt % pageSize == 0 ? 0 : 1);
		int pageBlock = 2;
		int startPage = ((currentpage - 1)/pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock -1;
		if(endPage > pageCount){
			endPage = pageCount;
		}
		if(startPage > pageBlock){
			%>
			<a href="./shareList.sh?pageNum=<%=startPage-pageBlock %>&pageSize=<%=pageSize%>">[이전]</a>
			<%
		}
		for(int i=startPage; i<=endPage; i++){
			%>
				<a href="./shareList.sh?pageNum=<%=i%>&pageSize=<%=pageSize%>">[<%=i %>]</a>
			<%
		}
		if(endPage < pageCount){
			%>
			<a href="./shareList.sh?pageNum=<%=startPage+pageBlock %>&pageSize=<%=pageSize%>">[다음]</a>
			<%
		}
	}
%>
<input type="button" onclick="share_write_userCheck()" value="글쓰기" class="write-btn">
<hr>
</div>
</div>
<br style="clear: both;">&nbsp;
<br>&nbsp;
<br>&nbsp;
<br>&nbsp;
<script  src="./share/script.js"></script>
</body>
</html>