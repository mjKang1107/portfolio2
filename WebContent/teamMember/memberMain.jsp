<%@page import="com.deco.team.teamDTO"%>
<%@page import="com.deco.team.teamDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./teamMember/teamMember.css" rel="stylesheet">
</head>
<body>
	<%
		String idx = request.getParameter("idx");

		int userN = 0;	
		if(session.getAttribute("user_num") == null) {
			response.sendRedirect("./teamList.te");
		} else {
			userN = (int) session.getAttribute("user_num");
		}
		
		teamDAO tdaoM = new teamDAO();
		teamDTO tdtoM = tdaoM.getteamView(Integer.parseInt(idx));
	%>
	<div id="subNav">
	<ul>
	<li><a href="./teamView.te?idx=<%=idx %>">팀 뷰가기</a></li>
	
	<li><a href="./teamMemberTalk.tm?idx=<%=idx%>">팀 채팅방</a></li>
	
	<li><a href="./teamMemberCalendar.tm?idx=<%=idx%>">일정 공유</a></li>
	
	<%if(userN == tdtoM.getMaster()) { %>
	<li><a href="./memberList.tm?idx=<%=idx%>">멤버 관리</a></li>	
	<%} %>
	</ul>
	</div>


</body>
</html>