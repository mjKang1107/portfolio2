<%@page import="java.util.List"%>
<%@page import="com.deco.user.userDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./user/myPage/joinTeamList.css">
<link rel="stylesheet" href="./user/myPage/layout.css">
</head>
<body>
	<%@ include file="../../main/header.jsp" %>
	
	<main class="myPageWrapper">
		<%@ include file="../../user/myPageNav.jsp" %>
		
		<%uDAO = new userDAO(); %>
		
		<div class="userTeamList-Wrapper">
			<div class="userTeamList-JoinedList">
			<div class="userTeamList__title"><span>가입된 리스트</span></div>
			
			<div class="TeamList-Wrapper">
			<c:forEach items="${TeamList[0] }" var="joinTeam">
				<a href="./teamView.te?idx=${joinTeam.idx }">
					<div class="userTeamList-JoinedList__item myPage-TeamCard">
						<div class="userTeamList-TeamCard__item-title"><span>${joinTeam.title }</span></div>
						<c:set var="masterID" value="${joinTeam.master }" />
						
						<%int masterID = Integer.parseInt(pageContext.getAttribute("masterID").toString());
						pageContext.setAttribute("masterName", uDAO.getUserNickNameByNum(masterID));
						%>
						
						<div class="userTeamList-TeamCard__item-master">
							<span class="masterHead">마스터</span>
							<span class="masterTail">${masterName }</span>
						</div>
							
						<div class="userTeamList-TeamCard__item-curPersonnel"></div>
					</div>
				</a>
			</c:forEach>
			</div>
			</div>
			
			<div class="userTeamList-WaitingList">
			<div class="userTeamList__title"><span>승인 대기중 리스트</span></div>
			<div class="TeamList-Wrapper">
				<c:forEach items="${TeamList[1] }" var="waitingTeam">
				<a href="./teamView.te?idx=${waitingTeam.idx }">
						<div class="userTeamList-WaitingList__item myPage-TeamCard">
							<div class="userTeamList-TeamCard__item-title"><span>${waitingTeam.title }</span></div>
							<c:set var="masterID" value="${waitingTeam.master }" />
							
							<%int masterID = Integer.parseInt(pageContext.getAttribute("masterID").toString());
							pageContext.setAttribute("masterName", uDAO.getUserNickNameByNum(masterID));
							%>
							
							<div class="userTeamList-TeamCard__item-master">
								<span class="masterHead">마스터</span>
								<span class="masterTail">${masterName }</span>
							</div>
								
							<div class="userTeamList-TeamCard__item-curPersonnel"></div>
						</div>
					</a>
				</c:forEach>
			</div>
			</div>
		</div>
	</main>
</body>
</html>