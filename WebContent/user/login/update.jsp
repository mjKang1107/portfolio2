<%@page import="com.deco.user.userDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deco - 회원정보수정</title>
<link rel="stylesheet" href="./user/login/update.css">
<link rel="stylesheet" href="./user/myPage/layout.css">

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="./user/join/js/addrAPI.js"></script>
<script type="module" src="./user/join/js/phoneHypen.js"></script>
<script type="text/javascript">
$(function(){
	$(".jj").on("keyup", function(){
		var t = $(".jj").val();
		if(t.length<1){
			$(".jj").css("color","red");
		}else{
			$.ajax({
				url:"./NickcheckAction.us",
				type:"post",
				data:{"nickname":$(".jj").val(),"user_num":$(".us").val()},
				success:function(data){
					console.log(data);
					if(data == 2){
						$(".ttx").val("1");
						$(".jj").css("color","green");
					}else if(data == 1){
						$(".ttx").val("0");
						$(".jj").css("color","red");
					}else{
						$(".ttx").val("1");
						$(".jj").css("color","green");
					}
				}
			});
		}
	});
});
</script>


<script type="text/javascript">
function check(){
	if(document.fr.pw.value==""){
		alert("비밀번호를 입력해주세요.");
		document.fr.pw.focus();
		return false;
	}
	if(document.fr.name.value == ""){
		alert("이름을 입력해주세요.");
		document.fr.name.focus();
		return false;
	}
	if(document.fr.nickname.value == ""){
		alert("닉네임을 입력해주세요");
		document.fr.nickname.focus();
		return false;
	}
	if(document.fr.addr.value == ""){
		alert("주소를 입력해주세요.");
		document.fr.addr.focus();
		return false;
	}
	if(document.fr.phone.value == ""){
		alert("전화번호를 입력해주세요.");
		document.fr.phone.focus();
		return false;
	}
	if(document.fr.major.value == ""){
		alert("전공분야를 입력해주세요.");
		document.fr.major.focus();
		return false;
	}
	if((document.fr.idDumplication.value) == "0"){
		alert("중복된 닉네임 입니다.");
		document.fr.nickname.focus();
		return false;
	 }
}
</script>
</head>
<body>

	<%@ include file="../../main/header.jsp" %>
	<%
		request.setCharacterEncoding("UTF-8");
		userDTO udto = (userDTO) request.getAttribute("udto");
	%>
	<main class="myPageWrapper">

	<%@ include file="../../user/myPageNav.jsp" %>
	<form action="./UpdateAction.us" method="post" onsubmit="return check();" name="fr" class ="updateUserForm">
		<div class="userForm-TitleWrapper">
			<h2 class="Title">User Update</h2>
		</div>
		<input type="hidden" name="user_num" value="<%=user_num %>" class="us">
		<input type="hidden" name="idDumplication" value="1" class="ttx">

		<div class="userForm__inputData">
			<label for="pwInput">비밀번호</label>
			<input id="pwInput" type="password" name="pw" placeholder="현재 비밀번호를 입력해주세요." style="text-align:center" size="30">
		</div>
		<div class="userForm__inputData">
			<label for="emailInput">이름</label>
			<input id="emailInput" type="text" name="name" value="<%=udto.getName() %>" style="text-align:center" size="15">
		</div>
		
		<div class="userForm__inputData">
			<label for="nickInput">닉네임</label> 
			<input id="nickInput" type="text" name="nickname" value="<%=udto.getNickname() %>" style="text-align:center" size="15" class="jj">
		</div>
		<div class="userForm__inputData">

		</div>
		<div class="userForm__inputData">
			<label for="addrBtn">주소</label>  
			<input type="text" name="addr" id="addr" value="<%=udto.getAddr() %>" style="text-align:center" size="70">
			<button id="addrBtn" onclick="return callAddress()">주소찾기</button>
		</div>
		
		<div class="userForm__inputData">
			<label for="emailInput">전화번호</label> 
			<input type="text" name="phone" id="phone" value="<%=udto.getPhone() %>" style="text-align:center" size="40" maxlength="13">
		</div>
		<div class="userForm__inputData">
			<label for="emailInput">전공분야</label> 
			<input type="text" name="major" value="<%=udto.getMajor() %>" size="40">
		</div>
		<div class="userForm__inputData">
			<label for="emailInput">관심분야</label>
			<input type="text" name="inter" value="<%=udto.getInter() %>" size="40">
		</div>

		<input type="submit" value="수정하기">
		<input type="button" value="취소하기" onclick="history.back();">
	</form>
	</main>
</body>
</html>