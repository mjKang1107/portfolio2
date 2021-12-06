<%@page import="com.deco.user.userDTO"%>
<%@page import="com.deco.team.member.teamMemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		teamMemberDTO tmdto = (teamMemberDTO) request.getAttribute("tmdto");
		userDTO udto = (userDTO) request.getAttribute("memberInfo");
		
		int masterNum = (int)request.getAttribute("masterNum");
		
	%>
	<div class="header"><span>User Info</span></div>
	<table cellspacing="0" class="info-table__detail">
		<tr>
			<th>이름</th>
			<td><%=udto.getName() %></td>
			<th>닉네임</th>
			<td><%=udto.getNickname() %></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td colspan="3"><%=udto.getEmail()%></td>
		</tr>
		<tr>
			<th>주소</th>
			<td colspan="3"><%=udto.getAddr()%></td>
		</tr>
		<tr>
			<th>전문분야</th>
			<td colspan="3"><%=udto.getMajor()%></td>
		</tr>
		<tr>
			<th>관심분야</th>
			<td colspan="3"><%=udto.getInter() == null ? "" : udto.getInter()%></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td colspan="3"><%=udto.getPhone()%></td>
		</tr>
	</table>
	<%if(tmdto.getSubmit() == 0){%>
		<input type="button" value="승인하기" id="joinTeam<%=tmdto.getIdx()%>">
	<%}%>
	<%if(masterNum != udto.getUser_num()){ %>
	<input type="button" value="퇴출하기" id="outMember<%=tmdto.getIdx()%>">
	<%} %>
	
</body>
</html>