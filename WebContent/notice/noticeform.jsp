<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<h1>WebContent/notice/noticeform.jsp</h1>

<script type="text/javascript">
	function readContent() {
		   document.fr.content.value = oEditor.getIR();
		}
	</script>
	<form action="./NoticeInsertAction.nt" method="post" enctype="multipart/form-data" name="fr">
		제목 <input type="text" name="title">
		<hr>
		<input type="file" name="file">
		<hr>
		
		<input type="hidden" name="content" id="content">
		
		<!-- 원래 이름 : name="ir1" -->
		<textarea name="ir1" id="ir1" rows="10" cols="100" style="width:766px; height:412px; display:none;"></textarea>
	</form>

		<jsp:include page="./edit.jsp"/><!-- 에디터 인클루드 -->
		
<!-- <hr>

	<input type="button" onclick="submitContents();" value="글 작성" />
	<input type="button" value="목록으로" onclick="location.href='noticelist.nt';"> -->

</body>
</html>