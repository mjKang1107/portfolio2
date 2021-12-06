<%@page import="com.deco.user.userDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./main/main.css">
</head>
<body id="mainDeco">

<%
	int user_num = -1;
	int admin_auth = -1;
	userDAO uDAO;
	String name = null;
	
	if(session.getAttribute("user_num") != null){
		user_num = (Integer)session.getAttribute("user_num");
		uDAO = new userDAO();
		name = uDAO.getUserNickNameByNum(user_num);
		admin_auth = uDAO.getAdminByNum(user_num);
		if(admin_auth == -1){
			if(request.getRequestURI().indexOf("needEmail_auth") == -1)
				response.sendRedirect("./emailAuth.us");
		} 
		if(admin_auth == -10){
			if(request.getRequestURI().indexOf("socialJoin") == -1)
				response.sendRedirect("./SocialJoin.us");
		}
	}
%>
<div id="wrapDeco">
<header>
  <div class="AlogoDeco">
      <div class="Decolink-1">
        <a class="HlogoDeco" href="./main.us" >
          <span class="thickDeco">D</span><span class="thinDeco">e</span><span class="thickDeco">C</span><span class="thinDeco">o</span>
        </a>
        <p class="tagPDeco"><a class="pMainDeco" href="./main.us">Developer Completion</a></p>    
      </div>
  </div>
	<nav-1 id="HnavDeco">
	  <ul>
		<li>
		<a class="HmenuDeco" href="./noticelist.nt">Notice</a>
		</li>
		<li>
		<a class="HmenuDeco" href="./shareList.sh">Share</a>
		</li>
		<li>
		<a class="HmenuDeco" href="./teamList.te">Project</a>
		</li>
		<li>
		<a class="HmenuDeco" href="./info.us?user_num=<%=user_num %>">My Page</a>
		</li>
	  </ul>
	</nav-1>	
	
	<div id="HsearchDeco">
		<!-- Search Google -->
	<form method=get action="http://www.google.co.kr/search" target="_blank" id="googleSearch">
		<table bgcolor="#FFFFFF">
			<tr>
				<td>
				<input type=text name=q size=25 maxlength=255 value="" />
				<!-- 구글 검색 입력 창 -->
				<input type=submit name=btnG value="Google 검색" />
				<!-- 검색 버튼 -->
				</td>
			</tr>
		</table>
	</form>
	<!-- Search Google -->
	</div>
	
	<%if(name == null && user_num == -1) {%>
	<div id="HloginDeco">
<!-- 		<a class="Hlogin" href="./login.us">login</a> /
		<a class="Hlogin" href="./login.us">join</a> -->
	<form class="userFormDeco loginFormDeco" name="frDeco">	
		<main class="userMainDeco">
	<input type="button" class="userForm__buttonDeco" value="로그인" onclick="location.href='./login.us'">
 </form>
</main>
	</div>
	<%} else{%>
	<div id="HloginDeco">
	<form class="userFormDeco loginFormDeco" name="frDeco">	
		<h2 class="HloginDeco">Welcome <%=name %></h2>
<%-- 관리자 페이지 추천 	<%if(admin_auth == 2){ %><a class="Hlogin" href="./cancel.us">회원탈퇴 취소</a><%} %> --%>
<!-- 		<a class="Hlogin" href="./userlogout.us">logout</a> -->
		<main class="userMainDeco">
		<input type="button" class="userForm__buttonDeco" value="로그아웃" onclick="location.href='./userlogout.us'">
 		</form>
		</div>
	<%} %>

	
	<!-- 회원탈퇴취소 admin_auth 값이 2인 사람만 보이게 수정 -->
</header>
</div>
<div style="clear:both;"></div>
</body>
</html>