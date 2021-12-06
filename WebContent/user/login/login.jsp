<%@page import="com.deco.user.userDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./user/login/login.css">
<link rel="stylesheet" href="./user/form.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deco</title>
<script type="text/javascript">
function check(){
	if(document.fr.email.value == ""){
		alert("이메일을 입력하세요");
		document.fr.email.focus();
		return false;
	}
	if(document.fr.pw.value == ""){
		alert("비밀번호를 입력하세요");
		document.fr.pw.focus();
		return false;
	}
}
</script>
</head>
<body>
<%
if(session.getAttribute("user_num") != null){
	response.sendRedirect("./main.us");
	return ;
}
%>
<%
	String referer = (String) request.getHeader("REFERER");
%>
<%@ include file="../../main/header.jsp" %>

<main class="userMain">
 <form action="./LoginAction.us" class="userForm loginForm" name="fr" method="post" onsubmit="return check();">
	<div class="userForm-TitleWrapper">
		<h2 class="Title">Login</h2>
	</div>
 	<input type="hidden" name="referer" value="<%=referer %>">
		<input type="email" name="email" class="loginForm__emailInput" size="20" placeholder="Insert Your Email." size="50" style="text-align:center"><br>
		<input type="password" name="pw" class="loginForm__pwInput" size="20" placeholder="Insert Your Password." size="50" style="text-align:center"><br> 
	<input type="submit" class="userForm__submit" value="Login">
 	<div class="memoIdWrapper">
		 <input type="checkbox" name="memoId" id="memoId" value="true">
		 <label for="memoId">아이디 기억하기</label>
	 </div>
	<div class="loginFrom-socialWrapper">
		<div>
		<a id="kakaoLogin" href="./kakaoLoginStart.us">
		  <img
		  src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
		  width="222" />
	   </a>
	</div>
	<div>
		<a href="./githubStart.us" class="github-login">
			<i class="fab fa-github"></i> 깃허브로 로그인
		</a>
	</div>
	</div>
 </form>
 <div class="reqJoin" >
 	<a href="join.us">혹시 회원이 아니신가요? &rarr;</a>
</div> 
</main>
 <script src="http://code.jquery.com/jquery-latest.min.js"></script>
 <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
 <script type="module" src="./user/login/js/memoId.js"></script>
</body>
</html>