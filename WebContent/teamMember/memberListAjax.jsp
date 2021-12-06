<%@page import="com.deco.team.teamDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./user/login/info.css">
<link rel="stylesheet" href="./teamMember/memberList.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DeCo - 팀프로젝트(멤버 관리)</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<%
	String team_idx = request.getParameter("idx");

	int usernum = 0;

	if(session.getAttribute("user_num") == null) {
		response.sendRedirect("./teamList.te");
	} else {
		usernum = (int) session.getAttribute("user_num");
	}
	
	if(new teamDAO().getteamView(Integer.parseInt(team_idx)).getMaster() != usernum) {
		%>
		<script type="text/javascript">
		alert("입장권한이 없습니다.");
		location.href="./teamList.te";
		</script>
		<%
	}

%>
<script type="text/javascript">
$(function(){
	$.ajax({
		url: "./printSubmitMemberList.tm",
		data: {team_idx:"<%=team_idx%>"},
		success: function(data){
			$("#submitMember").html(data);
			setTimeout(function(){isAjaxing=false;}, 1000);
		},
		async: false
	});
	
	$.ajax({
		url: "./printNoneSubmitMemberList.tm",
		data: {team_idx:"<%=team_idx%>"},
		success: function(data){
			$("#noneSubmitMember").html(data);
			setTimeout(function(){isAjaxing=false;}, 1000);
		},
		async: false
	});
	

	$(document).on('click',"[id*=member]",function(){
		$.ajax({
			url: "./memberInfo.tm",
			data: {idx:$(this).attr("id").substr(6), team_idx:"<%=team_idx%>"},
			success: function(data){
				$("#userInfo").html(data);
				setTimeout(function(){isAjaxing=false;}, 1000);
			}
		});
		
	});
	
	$(document).on('click',"[id*=joinTeam]",function(){
		$.ajax({
			url: "./joinTeam.tm",
			data: {idx:$(this).attr("id").substr(8), team_idx:"<%=team_idx%>"},
			success: function(data){
				if(data == 1) {
					alert("승인완료");
					$("#userInfo").html("");
					userReload();				
				} else {
					alert("승인가능한 인원이 가득 찼습니다.");
					$("#userInfo").html("");
					userReload();
				}
			}
		});
		
	});
	
	$(document).on('click',"[id*=outMember]",function(){
		$.ajax({
			url: "./outMember.tm",
			data: {idx:$(this).attr("id").substr(9)},
			success: function(data){
				alert("퇴출완료");
				$("#userInfo").html("");
				userReload();
			}
		});
		
	});
	
	function userReload() {
		$.ajax({
			url: "./printSubmitMemberList.tm",
			data: {team_idx:"<%=team_idx%>"},
			success: function(data){
				$("#submitMember").html(data);
				setTimeout(function(){isAjaxing=false;}, 1000);
			},
			async: false
		});
		
		
		$.ajax({
			url: "./printNoneSubmitMemberList.tm",
			data: {team_idx:"<%=team_idx%>"},
			success: function(data){
				$("#noneSubmitMember").html(data);
				setTimeout(function(){isAjaxing=false;}, 1000);
			},
			async: false
		});
	}
});
</script>
<link href="./teamMember/teamMember.css" rel="stylesheet">

</head>
<body>
<%@ include file="../../main/header.jsp" %>
<%@ include file="../../teamMember/memberMain.jsp" %>
<!-- <div class="team-memberList__TopTitle"><span>멤버관리 현황</span></div> -->
<div id="userArea">
<div class="team-memberList__Title"><span>승인유저</span></div>
<div id="submitMember" class="userArea-userList table-users"></div>
<div class="team-memberList__Title"><span>미승인유저</span></div>
<div id="noneSubmitMember" class="userArea-userList table-users"></div>
</div>
<div id="infoArea">
<div id="userInfo" class="userInfo-Wrapper"></div>
</div>

</body>
</html>