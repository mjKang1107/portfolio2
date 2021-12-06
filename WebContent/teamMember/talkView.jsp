<%@page import="java.util.ArrayList"%>
<%@page import="com.deco.team.member.teamMemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.deco.user.userDAO"%>
<%@page import="com.deco.team.talk.talkDTO"%>
<%@page import="com.deco.team.talk.talkDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<%
	int usernum = 0;
	
	if(session.getAttribute("user_num") == null) {
		response.sendRedirect("./teamList.te");
	} else {
		usernum = (int) session.getAttribute("user_num");
	}

	String nickname = new userDAO().getUserNickNameByNum(usernum);

	String team_idx = request.getParameter("idx");

	talkDAO tdao = new talkDAO();
	talkDTO tdto = tdao.getTalkInfo(Integer.parseInt(team_idx));
%>

<style type="text/css">
#talkContent {
	margin: 0;
	padding: 0 3%;
	width: 40%;
	height: 700px;
	background: linear-gradient(to bottom left, skyblue, pink);
	overflow: scroll;
	overflow-x: hidden;
	clear: both;
	float: left;
}

[id=nick<%=nickname%>] {
	text-align: right;
}

.nickname {
	color: #800080;
}

.content {
	background-color: #FFF56E;
	width: 15%;
	height: auto;
	display: inline-block;
}

hr {
	clear: both;
}

#boardController {
	clear: both;
	float: right;
}

#googleSearch {
	float: right;
}

#talkInfo {
	float: left;
}

#teamContent {
	margin: 0 auto;
}

#boradArea {
	box-sizing: border-box;
}

</style>
</head>
<body>
<%@ include file="../../main/header.jsp" %>
<%@ include file="../../teamMember/memberMain.jsp" %>
	<script type="text/javascript">
	
	$(function(){

		$("#content").focus();
		
		$.ajax({
		    url: "./printTalk.tm",
		    data: { team_idx: <%=team_idx%>},
		    success: function(data){
		    	$("#talkContent").html(data);
		    	$("#talkContent").scrollTop(100000);
		    }
		});
		setInterval(function () {
			$.ajax({
			    url: "./printTalk.tm",
			    data: { team_idx: <%=team_idx%>},
			    success: function(data){
			    	$("#talkContent").html(data);
			    	$("#talkContent").scrollTop(100000);
			    }
			});
         }, 5000);
        
	});
	
	function writeContent() {
		var nickname = document.getElementById("nickname").value;
		var content = document.getElementById("content").value;
		
		if(content != ""){
			$(function(){
				$.ajax({
				    url: "./updateTalk.tm?team_idx=<%=team_idx%>",
				    data: {nickname: nickname, content:content},
				    method: "POST",
				    success: function(data){
				    	document.getElementById("content").value = "";
				    	$.ajax({
						    url: "./printTalk.tm",
						    data: { team_idx: <%=team_idx%>},
						    success: function(data){
						    	$("#talkContent").html(data);
						    	$("#talkContent").scrollTop(100000);
						    }
						});
				    }
				});
				
			});			
		}
	}
	
	function enterKey() {
		if(window.event.keyCode == 13){
			writeContent();
		}
	}

	</script>
	<div id="talkInfo">
	닉네임 : <input type="text" id="nickname" value="<%=nickname%>" readonly>
	내용 : <input type="text" id="content" onkeyup="enterKey()">
	<input type="button" value="전송" onclick="writeContent()">
	</div>


	<div id="boardController">
	<%	
		List<teamMemberDTO> tmList = (ArrayList<teamMemberDTO>) request.getAttribute("teamMemberList");
		
	%>
	
	
	<a href="./calendarView.tm?idx=<%=team_idx%>" target="boradArea">일정관리</a>
	<a href="https://map.naver.com/" target="boradArea">지도보기</a>

	
	</div>
	
	<hr>
	<div id="teamContent">
		<div id="talkContent"></div>
	
		<iframe src="./calendarView.tm?idx=<%=team_idx%>" width="54%" height="700" id="boradArea" name="boradArea"></iframe>	
		
	</div>
	<hr>
	

</body>
</html>