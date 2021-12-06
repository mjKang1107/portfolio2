<%@page import="com.deco.user.userDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/notice/main.jsp</h1>
	
	<%
		// 세션객체의 정보를 가져와서 확인
		int user_num = 0;
		if(session.getAttribute("user_num") != null) {
			user_num = (int) session.getAttribute("user_num");
		}
	
		
		userDAO usDAO = new userDAO();
		String nickName = usDAO.getUserNickNameByNum(user_num);
		
		int adminCheck = usDAO.getAdminByNum(user_num);
	
		/* if(user_num == null){
			// 로그인 x
			System.out.println("[main.jsp] : 아이디가 없음 -> 로그인페이지 이동");
			response.sendRedirect("./MemberLogin.me");
		} */
	%>
	
	<%-- <h2><%= user_num %>님 환영합니다~!</h2>
	<h2><%= nickName %>님 환영합니다~!</h2> --%>
	
	<!-- Search Google -->
	<form method=get action="http://www.google.co.kr/search" target="_blank" >
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
	
	<%if(adminCheck == -1){ %>
		<h3><a href="./noticeform.nt"> 게시판 폼 </a></h3>
	<%} %>
	
	<h3><a href="./noticelist.nt"> 게시판 리스트 </a></h3>
	
	<h3><a href="./main.us"> 메인 </a></h3>
	
</body>
</html>