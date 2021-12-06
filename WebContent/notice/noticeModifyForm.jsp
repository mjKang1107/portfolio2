<%@page import="com.deco.notice.db.noticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/notice/noticeModifyForm.jsp</h1>
	
	<%
		// 저장
		noticeDTO nDTO = (noticeDTO)request.getAttribute("nDTO");
	
		int idx = Integer.parseInt(request.getParameter("idx"));
	%>
	<form action="./NoticeModifyAction.nt" method="post" name="fr">
		<input type="hidden" name="idx" value="<%=idx%>">
		제목 <input type="text" name="title" value="<%=nDTO.getTitle()%>">
		<hr>
		<input type="hidden" name="content" id="content">
		<textarea name="ir1" id="ir1" rows="10" cols="100" style="width:766px; height:412px; display:none;">
		<%=nDTO.getContent()%>
		</textarea>
	</form>
	<jsp:include page="./edit.jsp"></jsp:include>
	<hr>
	
	<input type="button" onclick="readContent();submitContents();" value="글 수정" />
	<input type="button" value="목록으로" onclick="location.href='noticelist.nt';">
	
</body>
</html>