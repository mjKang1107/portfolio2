<%@page import="com.sun.xml.internal.bind.v2.runtime.Location"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./user/myPage/layout.css">
<link rel="stylesheet" href="./user/login/delete.css">
<title>Deco</title>
<script type="text/javascript">
function cancel(){
		history.back();
	}
</script>
<script>
	function check(){
		if(document.fr.email.value == ""){
			alert("이메일을 입력해주세요.");
			document.fr.email.focus();
			return false;
		}
		if(document.fr.pw.value == ""){
			alert("비밀번호를 입력해주세요.");
			document.fr.pw.focus();
			return false;
		}
	}
</script>
</head>
<body>
<%@ include file="../../main/header.jsp" %>
<%
	if(session.getAttribute("user_num") == null){
		%>
		<script type="text/javascript">
		alert("권한이 없습니다.");
		history.back();
		</script>
		<%
	}
	uDAO = new userDAO();
	int admin_Auth = uDAO.getAdminByNum(user_num);
	if(admin_Auth == 2){
		response.sendRedirect("./cancel.us");
		return;
	}
%>
	<main class="myPageWrapper">
	
	<%@ include file="../../user/myPageNav.jsp" %>
	<div style="margin:0 auto;">
	<h2>회원 탈퇴</h2>
	<h4>회원탈퇴후 한달간 정상적으로 이용 가능하시며 한달후 탈퇴 처리가 진행됩니다.</h4>
	<form action="./DeleteAction.us" method="post" name="fr" onsubmit="return check();" class="deleteForm">
		<input type="email" name="email" placeholder="이메일을 입력하세요." style="text-align:center"><br>
		<br>
		<input type="password" name="pw" placeholder="비밀번호를 입력하세요." style="text-align:center"><br>
		<br>
		<input type="submit" value="탈퇴하기">
		<input type="button" value="취소" onclick="return cancel();">
	</form>
	</div>

	</main>
</body>
</html>