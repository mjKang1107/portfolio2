<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="com.deco.team.teamDTO"%>
<%@page import="com.deco.user.userDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deco - 팀 수정</title>
<link href="./team/css/write.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

function teamUpdateCheck() {
	document.fr.content.value= oEditor.getIR();
	
	 if(document.fr.title.value == ""){
	      alert("제목을 작성해주세요.");
	      document.fr.title.focus();
	      return false;
	   }
	   
	   if(document.fr.location.value == ""){
	      alert("지역을 선택해주세요.");
	      document.fr.location.focus();
	      return false;
	   }
	   
	   if(document.fr.content.value == "" || document.fr.content.value == "<p><br></p>"){
	      alert("내용을 작성해 주세요.");
	      return false;
	   }
	   
	   if(document.fr.limit_p.value == ""){
	    	alert("인원을 설정해주세요.");
	    	document.fr.limit_p.focus();
	    	return false;
	    }
	   
	   if(document.fr.deadline.value == ""){
	    	alert("모집기간을 설정해주세요.");
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
<%@ include file="../../main/header.jsp" %>
<body>
	<%
	
		int userNum = 0;
	
		if(session.getAttribute("user_num") == null){
			response.sendRedirect("./teamMain.te");
		} else {
			userNum = (int) session.getAttribute("user_num");
		}
	
		userDAO udao = new userDAO();
		
		teamDTO tdto = (teamDTO)request.getAttribute("tdto");
		String nickname = udao.getUserNickNameByNum(tdto.getMaster());
		
		String pageSize = request.getParameter("pageSize");
		String pageNum = request.getParameter("pageNum");
		
	%>
		<div id="write">
		<div id="smart_editor2">
		<form action="./teamModifyUpdate.te?pageNum=<%=pageNum%>&pageSize=<%=pageSize%>&idx=<%=tdto.getIdx()%>"
		 method="post" name="fr" onsubmit="return teamUpdateCheck();">
		<!-- 닉네임 -->
		방장 닉네임 : <input type="text" id="nickname" name="nickname" value="<%=nickname%>"readonly style="text-align:center">
		<hr>
		<input type="hidden" value="<%=tdto.getMaster()%>" name="master">
		<!-- 제목 -->	  		
		프로젝트 명 : <input type="text" name="title" value="<%=tdto.getTitle()%>" style="text-align:center" style="text-align:center">
		<hr id="cutBar">
		<select name='location'>
  			<option value="" selected>지역을 선택해주세요</option>
  			<option value="Seoul"
  			<%if(tdto.getLocation().equals("Seoul")){ %>	
  			selected
  			<%} %>>서울</option>
 			<option value="Busan"
 			<%if(tdto.getLocation().equals("Busan")){ %>	
  			selected
  			<%} %>>부산</option>
  			<option value="Incheon"
  			<%if(tdto.getLocation().equals("Incheon")){ %>	
  			selected
  			<%} %>>인천</option>
  			<option value="Daegu"
  			<%if(tdto.getLocation().equals("Daegu")){ %>	
  			selected
  			<%} %>>대구</option>
  			<option value="Gwangju"
  			<%if(tdto.getLocation().equals("Gwangju")){ %>	
  			selected
  			<%} %>>광주</option>  
  			<option value="Ulsan"
  			<%if(tdto.getLocation().equals("Ulsan")){ %>	
  			selected
  			<%} %>>울산</option>
  			<option value="Daejeon"
  			<%if(tdto.getLocation().equals("Daejeon")){ %>	
  			selected
  			<%} %>>대전</option>		
  			<option value="Changwon"
  			<%if(tdto.getLocation().equals("Changwon")){ %>	
  			selected
  			<%} %>>창원</option>
  			<option value="Jeju"
  			<%if(tdto.getLocation().equals("Jeju")){ %>	
  			selected
  			<%} %>>제주도</option>					
		</select>
		<hr id="cutBar">
		
		<select name='limit_p'>
  			<option value="" selected>인원을 선택해주세요</option>
  			<option value=4
  			<%if(tdto.getLimit_p().equals("4")){%>
  			selected
  			<%} %>>4명</option>
 			<option value=5
 			<%if(tdto.getLimit_p().equals("5")){%>
  			selected
  			<%} %>>5명</option>
  			<option value=6
  			<%if(tdto.getLimit_p().equals("6")){%>
  			selected
  			<%} %>>6명</option>
  			<option value=7
  			<%if(tdto.getLimit_p().equals("7")){%>
  			selected
  			<%} %>>7명</option>
  			<option value=8
  			<%if(tdto.getLimit_p().equals("8")){%>
  			selected
  			<%} %>>8명</option>  
  			<option value=9
  			<%if(tdto.getLimit_p().equals("9")){%>
  			selected
  			<%} %>>9명</option>
  			<option value=10
  			<%if(tdto.getLimit_p().equals("10")){%>
  			selected
  			<%} %>>10명</option>			
		</select><hr>
				
		모집 기한 : <input type="date" name="deadline" value="<%=tdto.getDeadline()%>">	<br>
		
		<script type="text/javascript">
	    	$(document).ready(function($) {
	    		$('#ir1').html('<%=tdto.getContent()%>');
	    	});
    	</script>
		<br>
		<input type="hidden" name="content">
		<jsp:include page="edit.jsp"></jsp:include>
		
		<div class="g-recaptcha" id="g-recaptcha" data-sitekey="6LdQ1zEbAAAAAOzJAHtwDc8LTdr2vNQffqV-K15l" 	
		data-callback="recaptcha"></div>
		<br>
		<div id="buttons">
		<input type="submit" value="수정하기">/
		<input type="reset" value="취소"> /
		<input type="button" value="수정취소" onclick="history.back()">
		</div>
		</form>
		</div>
	</div>



</body>
</html>