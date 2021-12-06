<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>TeamProject_Project DeCo</h1>


	<h2>현재 파일은 수정하지 말것!</h2>
	
	현재 index파일은 최종 index파일로 사용하기 위함으로 테스트를 위한 index파일은 각자 해당 폴더에 제작하여 테스트 바람<br>
	각 Resources 파일에 FrontController 제작 해두었고, 매핑 및 이동해두었음 -> 주석 사이 매핑작업하여 주소 이동 작성 <br>
	info.jsp, main.jsp 페이지 (회원탈퇴, 회원탈퇴 취소) 링크 admin_auth 2인 회원 숨김처리<br>
	회원 탈퇴 시 mysql 워크벤치 설정 필요 (1. set global event_scheduler = on;) 구문1번 실행시킨후 <br>
	(2. show variables like '%event%';) 2번구문 실행시켜 event_scheduler = on 확인후 진행<br>
<%
	response.sendRedirect(request.getContextPath()+"/main.us");
 %>
</body>
</html>

