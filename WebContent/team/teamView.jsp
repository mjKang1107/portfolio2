<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page import="com.deco.team.comment.Team_commentDAO"%>
<%@page import="com.deco.team.comment.Team_commentDTO"%>
<%@page import="java.io.Console"%>
<%@page import="com.deco.team.member.teamMemberDAO"%>
<%@page import="com.deco.user.userDAO"%>
<%@page import="com.deco.team.teamDAO"%>
<%@page import="com.deco.team.teamDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./team/css/teamView.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>Deco - 팀뷰</title>
</head>
<body>
<div id="page1"></div>
<%@ include file="../../main/header.jsp" %>
<div class="wrap">
<center>
	<div id="up"><a href="#page1"><img src="https://img.icons8.com/color/48/000000/circled-up-2--v2.png"/></a></div>
	<div id="cont"><a href="#content"><img src="https://img.icons8.com/color/48/000000/circled-down-2--v2.png"/></a></div>
<%
	/* int user_num = 0; */
		if(session.getAttribute("user_num")!=null){
			user_num = (int) session.getAttribute("user_num");
		}

	String idx = request.getParameter("idx");
	userDAO udao = new userDAO();
	teamDTO tdto = (teamDTO) request.getAttribute("tdto");
	String masternick = udao.getUserNickNameByNum(tdto.getMaster());
	int check = (int) request.getAttribute("check");
	
	int limit_p = Integer.parseInt(tdto.getLimit_p());
	int checkSubmitMember = new teamMemberDAO().checkSubmitMember(Integer.parseInt(idx));
	int team_idx = Integer.parseInt(request.getParameter("idx"));
	String nickname = udao.getUserNickNameByNum(user_num);
	
	
	
	
%>
<script type="text/javascript">

function dropteam(){
	if(confirm("정말 프로젝트를 취소하시겠습니까?")){
		
	}else{
		return false;
	}
}

</script>
	
	<div class="main">
 	<h2>Project <br>
 	<%=tdto.getTitle() %></h2><br>
 	<b>PM. <%=masternick %></b>
 	<br>
 	<b><%=tdto.getLocation() %></b><br>
 	
 	<%if(checkSubmitMember >= limit_p){
 	check = 0;
 	%>
 	<b style="color: red">모집 인원이 마감되었습니다.</b>
 	
 	<%}else{ %>
 	<b>모집 현황 : <%=checkSubmitMember %> / <%=limit_p %></b>
 	
 	<%} %>
 	<br><br>
 	<% if(check == 1){ %>
 	<b>모집 마감일 : <%=tdto.getDeadline() %></b><br>
 	<% }else{ %>
 		<b style="color: red">모집 기간이 지났습니다.</b>
 	<%} %>
 	<div id="DecotextContentView">
	<h4><%=tdto.getContent() %></h4>
 	</div>
 	</div>
 	<br>
	<% if(session.getAttribute("user_num")!=null){ %>
		<div class="hi">
		<nav class="nav">
			<% if(check == 1){ %>
				<a href="./joinTeamMember.tm?idx=<%=idx%>" class="nav-item" active-color="blue">참여하기</a>
				<a href="./deleteTeamMember.tm?idx=<%=idx %>" class="nav-item" active-color="green">팀탈퇴</a>
			<% } %>
			    <a href="./teamPage.tm?idx=<%=idx%>" class="nav-item" active-color="blue">팀</a>
			<%
			}
	
	 		if(session.getAttribute("user_num")==null){
	 			
	 		}else if(tdto.getMaster() == (int)session.getAttribute("user_num")){
	 %>
	   <a href="./teamModify.te?idx=<%=idx%>" class="nav-item" active-color="red">팀정보수정</a>
	   <a href="./deleteTeamAction.te?idx=<%=tdto.getIdx() %>" class="nav-item" active-color="rebeccapurple" onclick="return dropteam()">팀삭제</a>
	   <span class="nav-indicator"></span>
	   
	   </div>
	 <%} %>
	 </nav>

	 <br>
	 <br>
	 <nav class="nav">
	 <a href="./teamList.te" class="nav-item" active-color="orange">목록으로</a>
	 </nav>
	 <br>
	 <br>
	 
	 
	 <br>
	 <br>
	<!-- 댓글 -->
	
	<script type="text/javascript">
		// 댓글 달기 ajax
		$(function(){
			$(this).off("click").on("click", ".oh",function(){
				
			
				if(document.fr.content.value == ""){
					alert("댓글을 작성해주세요.");
					document.fr.content.focus();
					return false;
				}else{
					$.ajax({
						url:"./Team_commentAction.te",
						type:"post",
						data:{"team_idx":<%=team_idx %>, "user_num":<%=user_num %>, "content":$("#content").val(), "secret":document.fr.secret.value},
						success:function(data){
							location.reload();
						}
					});
				}
			});
		});
		// 댓글 달기 ajax
	</script>
	
<div class="table">


	
<%
	List teamCommentList = (ArrayList) request.getAttribute("teamCommentList");
	
	for(int i=0; i<teamCommentList.size(); i++){
		Team_commentDTO tcdto = (Team_commentDTO) teamCommentList.get(i);
		String commentnick = udao.getUserNickNameByNum(tcdto.getUser_num());
		%>
		
		
		
		
		
		<script type="text/javascript">
		// 댓글 수정버튼 이벤트 처리
		$(function(){
			var t ="<form name='fr<%=i%>' action=''>";
				if(<%=tcdto.getSecret() %>==1){
				t +="공개 <input type='radio' value='1' name='ret<%=i %>' checked> / 비공개 <input type='radio' value='0' name='ret<%=i %>'>";
				}else{
				t +="공개 <input type='radio' value='1' name='ret<%=i %>' > / 비공개 <input type='radio' value='0' name='ret<%=i %>' checked>";
				}
				t +="<br><br><input type='text' id='re<%=i %>' name='content2' placeholder='수정할내용을 입력해주세요.' size='40'' style='text-align:center'>&nbsp";
				<%-- t +="<button id='remove<%=i %>'>수정하기</button>"; --%>
				t += "<input type='button' value='수정하기' id='remove<%=i %>'>"
				t +="</form>";
			$("#god<%=i %>").on("click",function(){
				$(".hid<%=i %>").html(t);
				$(".hid<%=i %>").fadeToggle(t);
			});
		});
		// 댓글 수정버튼 이벤트 처리
		
		//댓글 수정 ajax
		$(function(){
			$(document).on("click", "#remove<%=i %>",function(){
				
			if(document.fr<%=i%>.content2.value == ""){
				alert("수정하실 댓글의 내용을 입력해주세요.");
				return false;
			}else{
				$.ajax({
					url:"./Team_commentUpdateAction.te",
					type:"post",
					data:{"idx":<%=tcdto.getIdx() %>, "content":$("#re<%=i %>").val(), "secret":document.fr<%=i%>.ret<%=i %>.value},
					success:function(data){
						location.reload();
					}
				});
				}
			});
			
		});
		//댓글 수정 ajax
		
		// 댓글 삭제 ajax
		$(function(){
			$("#my<%=i %>").on("click",function(){
				
			if(confirm("삭제하시겠습니까?")){
				$.ajax({
					url:"./Team_commentdeleteAction.te",
					type:"post",
					data:{"idx":<%=tcdto.getIdx() %>},
					success:function(check){
						if(check == 1){
						location.reload();
						}else if(check == 0){
							alert("본인이 단 글이 아닙니다.");
						}else{
							alert("잘못된 접근입니다.");
						}
						}
					});
				}else{
					return false;
				}
			});
		});
		// 댓글 삭제 ajax
		
	</script> 
		<!-- 댓글 -->
		<div class="balloon_03" style="text-align:center;">
		<b>By.</b> <%=commentnick %> &nbsp&nbsp&nbsp
		
		<b>
		<%if(tcdto.getSecret() == 1 ||tdto.getMaster()== user_num || tcdto.getUser_num()== user_num){ %>
		<%=tcdto.getContent() %>
		<%}else{ %>
		비공개 글입니다.
		<%} %>
		</b>
		<br>
		<%=tcdto.getCreate_at() %><br><br>
		<%if(tcdto.getUser_num()==user_num){ %>
				<label>
					<button id="god<%=i %>">수정</button>
				</label>
				&nbsp&nbsp&nbsp 
					<button id="my<%=i %>" >삭제</button>
				<%}else{ %>
					
				<%} %>
				</div>
					<div class="hid<%=i%>" style="display:none;"></div>
<%} %>	
		<!-- /댓글 -->
			
		
		
		</div>
		<br>
				<% if(session.getAttribute("user_num")!=null){ %>
	 	<form name="fr" onsubmit="return false;">
			<div class="rdio">
			 	공개 <input type="radio" checked="checked" name="secret" value="1" id="sec">
			 	비공개 <input type="radio" name="secret" value="0" id="sec">
		 	</div>
	 	<br>
	 	<label>
	 		<input type="text"  name="content" id="content" placeholder="궁금한점을 작성해주세요." size="50" style="text-align:center" maxlength="100"/>
	 	</label>
	 	<br>
	 	<br>
	 	
	 	<br>
	 		<button id="red" type="button" class="oh">등록</button>
	 	</form>
	 	<%} %>
	
</center>
</div>
</body>
</html>