<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.deco.team.teamDAO"%>
<%@page import="com.deco.user.userDAO"%>
<%@page import="com.deco.team.teamDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deco</title>

<link href="./team/teamList.css" rel="stylesheet">

</head>
<body>
<%
		userDAO udao = new userDAO();
		teamDAO tdao = new teamDAO();
		List teamList = (ArrayList)request.getAttribute("teamList");
		
	
	  int pageSize = (int)request.getAttribute("pageSize");
      String pageNum = (String)request.getAttribute("pageNum");
       
      int user_num = 0;
      
      if(session.getAttribute("user_num") != null) {
         user_num = (int) session.getAttribute("user_num");
      }
      
%>
<form name="form" action="./teamList.te?pageNum=<%=pageNum %>&pageSize=<%=pageSize %>" method="post" onsubmit="return searchCheck()">
	<select name="opt">
                <option value="0">프로젝트 제목</option>
                <option value="1">프로젝트 내용</option>
                <option value="2">프로젝트 지역</option>
            </select>
            <input type="text" size="20" name="condition"/>&nbsp;
            <input type="submit" value="검색"/>
	</form><br>
	<form name="fr" >
		<select id="boardSize" onchange="changeBoardSize()" name="changePageSize">
			<option>n개씩 보기</option>
			<option value="5">5개씩 보기</option>
			<option value="10">10개씩 보기</option>
			<option value="15">15개씩 보기</option>
			<option value="20">20개씩 보기</option>
		</select>
	</form>
	<br>
<script type="text/javascript">

function searchCheck() {
	if (document.form.condition.value == "")  {
		alert("검색어를 입력해 주세요.");
		return false;
	}
}

function changeBoardSize(){
	pageSize = document.fr.boardSize.value;
	location.href = "./teamList.te?pageNum=<%=pageNum %>&pageSize="+pageSize;
}

</script>
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
<%
 
   
   int cnt = tdao.numOfTeam();
   int currentpage = Integer.parseInt(pageNum);
   
   if(cnt != 0){
   
      int pageCount = cnt/pageSize + (cnt % pageSize == 0 ? 0 : 1);
      int pageBlock = 5;
      int startPage = ((currentpage - 1)/pageBlock) * pageBlock + 1;
      int endPage = startPage + pageBlock -1;
      if(endPage > pageCount){
         endPage = pageCount;
      }
      if(startPage > pageBlock){
         %>
         <a href="./teamList.te?pageNum=<%=startPage-pageBlock %>&pageSize=<%=pageSize%>" class="a1">[이전]</a>
         <%
      }
      for(int i=startPage; i<=endPage; i++){
         %>
            <a href="./teamList.te?pageNum=<%=i%>&pageSize=<%=pageSize%>" class="a3">[<%=i %>]</a>
         <%
      }
      if(endPage < pageCount){
         %>
         <a href="./teamList.te?pageNum=<%=startPage+pageBlock %>&pageSize=<%=pageSize%>" class="a4">[다음]</a>
         <%
      }
   }
%>
	<br>
	<br>
	<a href="./main.us" class="a2">메인페이지</a>
	</div>
	</center>
</div>

</body>
</html>