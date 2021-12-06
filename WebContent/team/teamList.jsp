<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.deco.user.userDAO"%>
<%@page import="com.deco.team.teamDTO"%>
<%@page import="com.deco.team.teamDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deco - 팀</title>
<link href="./team/css/search.css" rel="stylesheet">
<link href="./team/css/teamList.css" rel="stylesheet">
</head>
<div>
<%@ include file="../../main/header.jsp" %>
</div>

<body>
	<div class="wrap">
	<!-- 방만들기 -->
	<button onclick="location.href='./main.us';" class="main">메인으로</button>
	<button onclick="location.href='./createTeam.te';" class="room">팀꾸리기</button>
	<!-- /방만들기 -->
	<%
		userDAO udao = new userDAO();
		teamDAO tdao = new teamDAO();
		List teamList = (ArrayList) request.getAttribute("teamList");

		int pageSize = (int) request.getAttribute("pageSize");
		String pageNum = (String) request.getAttribute("pageNum");

		/* int user_num = 0; */

		if (session.getAttribute("user_num") != null) {
			user_num = (int) session.getAttribute("user_num");
		}
	%>
	<script type="text/javascript">

function searchCheck() {
	if (document.form.condition.value == "")  {
		alert("검색어를 입력해 주세요.");
		return false;
	}
}

function changeBoardSize(){
	pageSize = document.fr.boardSize.value;
	location.href = "./teamList.te?pageNum=<%=pageNum%>&pageSize="+ pageSize;
	}
	</script>

		<form name="form"
			action="./teamList.te?pageNum=<%=pageNum%>&pageSize=<%=pageSize%>"
			method="post" onsubmit="return searchCheck()">
			<div class="container">
			<select name="opt" class="finder">
				<option value="0">프로젝트 제목</option>
				<option value="1">프로젝트 내용</option>
				<option value="2">프로젝트 지역</option>
			</select>
				<div class="finder" id="coco">
					<div class="finder__outer" style="width: 390px; height: 10px;">
						<div class="finder__inner">
							<div class="finder__icon" ref="icon"></div>
							<input type="text" name="condition" class="finder__input" autocomplete="off"/>&nbsp;
							<input type="hidden" value="검색" />&nbsp;   
						</div>
					</div>
				</div>
			</div>
		</form>
		<br>
		<div id="n">
		<form name="fr" >
			<select id="boardSize" onchange="changeBoardSize()"
				name="changePageSize" class="finder">
				<option>n개씩 보기</option>
				<option value="5">5개씩 보기</option>
				<option value="10">10개씩 보기</option>
			</select>
		</form>
		</div>
		<br>
		
			<div class="cards">
	<%for (int i=0; i<teamList.size(); i++){
		teamDTO tdto = (teamDTO) teamList.get(i);
		String masternick = udao.getUserNickNameByNum(tdto.getMaster());
		%>
	<div class="card" onclick="location.href='./teamView.te?idx=<%=tdto.getIdx() %>'">
		<div class="card-face">
			<div class="card-label">
				#<%=i+1 %><br>
				title : <%=tdto.getTitle() %><br>
				master : <%=masternick %><br>
				location : <%=tdto.getLocation() %><br>
			</div>
		</div>
	</div>
	<%} %>
	<center>
<div class="a">
	 <nav class="nav">
<%
 
   
   int cnt = tdao.numOfTeam();
   int currentpage = Integer.parseInt(pageNum);
  
   if(cnt != 0){
   
      int pageCount = cnt/pageSize + (cnt % pageSize == 0 ? 0 : 1);
      int pageBlock = 3;
      int startPage = ((currentpage - 1)/pageBlock) * pageBlock + 1;
      int endPage = startPage + pageBlock -1;
      if(endPage > pageCount){
         endPage = pageCount;
      }
      if(startPage > pageBlock){
         %>
         <a href="./teamList.te?pageNum=<%=startPage-pageBlock %>&pageSize=<%=pageSize%>" class="nav-item">[이전]</a>
         <%
      }
      for(int i=startPage; i<=endPage; i++){
         %>
            <a href="./teamList.te?pageNum=<%=i%>&pageSize=<%=pageSize%>" class="nav-item"><%=i %></a>
         <%
      }
      if(endPage < pageCount){
         %>
         <a href="./teamList.te?pageNum=<%=startPage+pageBlock %>&pageSize=<%=pageSize%>" class="nav-item">[다음]</a>
         <%
      }
   }
%>
	<br>
	<br>
	</nav>
	
	
	
	</div>
	</center>
</div>


</div>
</body>
</html>