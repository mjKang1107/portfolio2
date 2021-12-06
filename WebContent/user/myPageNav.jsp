<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="myPageNavigator">
	<ul>
		<li><a href="./info.us?user_num=<%=session.getAttribute("user_num") %>">정보조회</a></li>
		<li><a href="./UserContentList.us">나의 목록</a></li>
		<li><a href="./UserJoinTeam.us">마이 팀</a></li>
		<li><a href="./delete.us?user_num=<%=session.getAttribute("user_num") %>">회원탈퇴</a></li>
	</ul>
</nav>