<%@page import="com.deco.user.userDAO"%>
<%@page import="com.deco.team.member.teamMemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
	List<teamMemberDTO> memberList = (ArrayList<teamMemberDTO>) request.getAttribute("noneSubmitMemberList");
%>
<table cellspacing="0" class="info-table__detail">
		<tr>
			<th>닉네임</th>
			<th>신청일</th>
			<th>신청상태</th>
		</tr>
		<%for(int i=0; i<memberList.size(); i++){ 
			teamMemberDTO tmdto = memberList.get(i);
		%>
		<tr id="member<%=tmdto.getIdx()%>">
			<td><%=new userDAO().getUserNickNameByNum(tmdto.getMember()) %></td>
			<td><%=tmdto.getCreate_at() %></td>
			<%if(tmdto.getSubmit() == 0){%>
				<td>미승인</td>
			<%} else { %>
				<td>승인</td>
			<%} %>
		</tr>
		<%} %>
	</table>

</body>
</html>