<%@page import="com.deco.user.userDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="./share/css/write.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>정보 공유 - 글쓰기</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- <script src="share/js/jquery-3.6.0.js"></script> -->
<script type="text/javascript">

$(document).ready(function($) {
	$('.tag').on('click',function(){
		if ($('.tag:checked').length > 5) {
			alert("관련 태그는 5개 까지 체크 가능합니다.");
			return false;
		}
	});
});

function shareWriteCheck() {
	document.fr.content.value = oEditor.getIR();
	
	var tags = document.querySelectorAll(".tag");
	var cnt = 0;
	for(var i = 0; i < tags.length;i++){
		if(tags[i].checked){
			cnt++;
		}
	}
	
	if(document.fr.title.value == ""){
		alert("제목을 작성해주세요.");
		document.fr.title.focus();
		return false;
	}
	
	if(document.fr.category.value == ""){
		alert("글을 작성하실 게시판을 선택해주세요.");
		document.fr.category.focus();
		return false;
	}
	
	if(document.fr.content.value == "" || document.fr.content.value == "<p><br></p>"){
		alert("내용을 작성해 주세요.");
		return false;
	}
	
	if (cnt == 0) {
		alert("관련 태그는 1개 이상 체크 해주셔야 합니다.");
		return false;
	}
	
	var chk = grecaptcha.getResponse(); 
    if(chk.length == 0) {
       alert("로봇이 아닙니다 인증을 진행해주세요!");
       return false; 
    }
	
}
</script>
</head>
<body>
<%@ include file="../../main/header.jsp" %>
		
	<%
	
		int userNum = 0;
	
		if(session.getAttribute("user_num") == null){
			response.sendRedirect("./shareList.sh");
		} else {
			userNum = (int) session.getAttribute("user_num");
		}
	
		userDAO udao = new userDAO();
		String nickname = udao.getUserNickNameByNum(userNum);
	%>
	
		<div id="write">
		<div id="smart_editor2">
		<form action="./shareWriteAction.sh" method="post" onsubmit="return shareWriteCheck()" name="fr" enctype="multipart/form-data" id="fr">
		<!-- 닉네임 -->
		닉네임 : <input type="text" id="nickname" name="nickname" value="<%=nickname%>"readonly>
		<input type="radio" name="anony" value="0" checked="checked">공개
		<input type="radio" name="anony" value="1">비공개
		<hr id="cutBar">
		<!-- 게시판 분류 -->
		
		<!-- 제목 -->	  		
		<input type="text" name="title" placeholder="제목을 입력하세요">
		<select name='category'>
  			<option value='' selected>게시판을 선택하세요</option>
  			<option value='Tips'>Tips</option>
 			<option value='Conference'>컨퍼런스</option>
  			<option value='Company'>회사추천</option>
  			<option value='Academy'>학원추천</option>
  			<option value='HowTo'>How to</option>  			
		</select><hr id="cutBar">
				
		<div class="tag">
		5개 이하로 체크하세요<i class="fas fa-check-square"></i><br>
		<input type="hidden" name="tagCheck" value="0">
		<!-- 최대 5개까지 체크가능 -->
		<input type="checkbox" name="tag" class="tag" value="AI/머신러닝">AI/머신러닝
		<input type="checkbox" name="tag" class="tag" value="AMstack">AMstack
    	<input type="checkbox" name="tag" class="tag" value="Angluar">Angluar
    	<input type="checkbox" name="tag" class="tag" value="Array method">Array method
    	<input type="checkbox" name="tag" class="tag" value="Assembly Script">Assembly Script
    	<input type="checkbox" name="tag" class="tag" value="Bootstrap5">Bootstrap5
    	<input type="checkbox" name="tag" class="tag" value="C++">C++
    	<input type="checkbox" name="tag" class="tag" value="CMS">CMS
    	<input type="checkbox" name="tag" class="tag" value="Context API">Context API
    	<input type="checkbox" name="tag" class="tag" value="CSS">CSS<br>
    	<input type="checkbox" name="tag" class="tag" value="CSS Framwork">CSS Framwork
    	<input type="checkbox" name="tag" class="tag" value="DOM">DOM
    	<input type="checkbox" name="tag" class="tag" value="Electron">Electron
    	<input type="checkbox" name="tag" class="tag" value="Fetch API">Fetch API
    	<input type="checkbox" name="tag" class="tag" value="Flex box">Flex box
    	<input type="checkbox" name="tag" class="tag" value="Flutter">Flutter
    	<input type="checkbox" name="tag" class="tag" value="Git">Git
    	<input type="checkbox" name="tag" class="tag" value="Grid">Grid
    	<input type="checkbox" name="tag" class="tag" value="HTML">HTML
    	<input type="checkbox" name="tag" class="tag" value="HTTP">HTTP
    	<input type="checkbox" name="tag" class="tag" value="HTTPS">HTTPS
    	<input type="checkbox" name="tag" class="tag" value="Ionic">Ionic
    	<input type="checkbox" name="tag" class="tag" value="JAVA">JAVA<br>
    	<input type="checkbox" name="tag" class="tag" value="Javascript">Javascript
    	<input type="checkbox" name="tag" class="tag" value="JSON">JSON
    	<input type="checkbox" name="tag" class="tag" value="Kotilin">Kotilin
    	<input type="checkbox" name="tag" class="tag" value="Materialize">Materialize
    	<input type="checkbox" name="tag" class="tag" value="Media query">Media query
    	<input type="checkbox" name="tag" class="tag" value="Native">Native
    	<input type="checkbox" name="tag" class="tag" value="NgRx">NgRx
    	<input type="checkbox" name="tag" class="tag" value="NW">NW
    	<input type="checkbox" name="tag" class="tag" value="Objective-C">Objective-C
    	<input type="checkbox" name="tag" class="tag" value="OS">OS
    	<input type="checkbox" name="tag" class="tag" value="PWA">PWA
    	<input type="checkbox" name="tag" class="tag" value="Python">Python<br>
    	<input type="checkbox" name="tag" class="tag" value="React">React
    	<input type="checkbox" name="tag" class="tag" value="React Native">React Native
    	<input type="checkbox" name="tag" class="tag" value="Redux">Redux
    	<input type="checkbox" name="tag" class="tag" value="Rust complie">Rust complie
    	<input type="checkbox" name="tag" class="tag" value="SASS">SASS
    	<input type="checkbox" name="tag" class="tag" value="Service Workers">Service Workers
    	<input type="checkbox" name="tag" class="tag" value="Shared Service">Shared Service
    	<input type="checkbox" name="tag" class="tag" value="SSG(site generator)">SSG(site generator)<br>
    	<input type="checkbox" name="tag" class="tag" value="Svelte">Svelte
    	<input type="checkbox" name="tag" class="tag" value="Switft">Switft
    	<input type="checkbox" name="tag" class="tag" value="Tailwind">Tailwind
    	<input type="checkbox" name="tag" class="tag" value="Tkinter">Tkinter
    	<input type="checkbox" name="tag" class="tag" value="Vue">Vue
    	<input type="checkbox" name="tag" class="tag" value="Vuex">Vuex
    	<input type="checkbox" name="tag" class="tag" value="Web Assembly">Web Assembly
    	<input type="checkbox" name="tag" class="tag" value="Xamarin">Xamarin
    	<input type="checkbox" name="tag" class="tag" value="디자인 패턴">디자인 패턴
    	<input type="checkbox" name="tag" class="tag" value="알고리즘">알고리즘
    	<input type="checkbox" name="tag" class="tag" value="음성인식 ">음성인식
		</div>
		<input type="hidden" name="content">
		<jsp:include page="edit.jsp"></jsp:include>
		
		<!-- 파일첨부 -->
		<div class="filebox">
		<i class="fas fa-file-upload"></i>
		<!-- <label for="ex_file">업로드</label> -->
		<input type="file" name="filename" id="ex_file">
		</div>
		<br>
		<!-- 로봇이 아닙니다 체크하기 -->
		<div class="g-recaptcha" id="g-recaptcha" data-sitekey="6LdQ1zEbAAAAAOzJAHtwDc8LTdr2vNQffqV-K15l" 	
		data-callback="recaptcha"></div>
		<!-- https://dedeweb.tistory.com/34 -->
		<br>
		<div id="buttons">
		<input type="submit" value="등록">
		<input type="reset" value="취소"> 
		<input type="button" value="목록으로" onclick="location.href='./shareList.sh'">
		</div>
		</form>
		</div>
	</div>
</body>
</html>