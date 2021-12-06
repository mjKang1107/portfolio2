
<%@page import="com.deco.bookmark.db.BookmarkDAO"%>
<%@page import="com.deco.like.likeDAO"%>
<%@page import="com.deco.like.likeDTO"%>
<%@page import="com.deco.user.userDTO"%>
<%@page import="com.deco.share_comment.commentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.deco.share.shareDAO"%>
<%@page import="com.deco.user.userDAO"%>
<%@page import="com.deco.share.shareDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link href="./share/css/list.css" rel="stylesheet">
<link href="./share/css/button.css" rel="stylesheet">
<title>Insert title here</title>
 <!-- jquery 준비 시작 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- jquery 준비 끝 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="./report/modal.css">
</head>
<body>
<%@ include file="../../main/header.jsp" %>
<div class="wrap">

<%
request.setCharacterEncoding("utf-8");

shareDTO sDTO =(shareDTO) request.getAttribute("shareContent");

String pageSize = request.getParameter("pageSize");
String pageNum = request.getParameter("pageNum");
String category = request.getParameter("category");
%>

<%if(new shareDAO().preContentNum(sDTO.getIdx(), category) != 0){ %>
<button value="이전글" onclick="location.href='./shareContent.sh?pageNum=<%=pageNum %>&pageSize=<%=pageSize%>&contentNum=<%=new shareDAO().preContentNum(sDTO.getIdx(), category)%>&category=<%=category%>'" style="margin-bottom: 20px;"><i class="fas fa-arrow-left"></i>이전글</button>
<%} 
if(new shareDAO().postContentNum(sDTO.getIdx(), category) != 0){ %>
<button value="다음글" onclick="location.href='./shareContent.sh?pageNum=<%=pageNum %>&pageSize=<%=pageSize%>&contentNum=<%=new shareDAO().postContentNum(sDTO.getIdx(), category)%>&category=<%=category%>'" style="margin-bottom: 20px;">다음글<i class="fas fa-arrow-right"></i></button>
<%} %>

<!-- 즐겨찾기 -->
<script type="text/javascript" src="./bookmark/bookmark.js"></script>
	<%
			BookmarkDAO bmDAO = new BookmarkDAO();
			int check = bmDAO.ckBookmark(user_num, sDTO.getIdx(),2);
	%>
	
	<%if(check != 1){%>
    	<input type="button" value="☆" id="bmox" onclick="bookmark(${user_num},<%=sDTO.getIdx()%>,2)">
    <%}else{ %>
    	<input type="button" value="★" id="bmox" onclick="bookmark(${user_num},<%=sDTO.getIdx()%>,2)">
    <%} %>
<!-- 즐겨찾기 -->

<table border="1" class="table table-bordered">
	<tr>
		<td>글번호</td>
		<td><%=sDTO.getIdx()%></td>
		<td>카테고리</td>
		<td><%=sDTO.getCategory()%></td>
		<td>조회수</td>	
		<td><%=sDTO.getRead_cnt()%></td>
	</tr>
	<tr>
		<td>태그</td>
		<td colspan="5"><%=sDTO.getTag()%></td>
	</tr>
	<tr>	
		<td>작성자</td>
		<%if(sDTO.getAnony()==1){%>
		<td><%if(user_num == sDTO.getUser_num()){ %>
		<%=new userDAO().getUserNickNameByNum(sDTO.getUser_num())%>-
		<%} %>
		익명</td>
		<%} else {%>
		<td><%=new userDAO().getUserNickNameByNum(sDTO.getUser_num())%></td>
		<%} %>
		<td>작성일</td>
		<td colspan="3"><%=sDTO.getCreate_at()%></td>
		
	</tr>
	<tr>	
		<td>제목</td>
		<td colspan="5"><%=sDTO.getTitle()%></td>
	</tr>
  
  
	<tr>	
		<td>첨부파일</td>
		<%if(sDTO.getFile()!=null){%>	
		<td colspan="5" ><i class="fas fa-file-download"></i><a href="filedown.sh?realPath=upload&file=<%=sDTO.getFile()%>"><%=sDTO.getFile() %></a></td>
		<%} else {%>
		<td colspan="5" >없음</td>
		<%} %>
	</tr>
	
	<tr>
		<td>글내용</td>
		<td colspan="5"><%=sDTO.getContent()%></td>
	</tr>
</table>

<!-- 좋아요 시작 -->
	<%
		likeDTO lDTO = new likeDTO();
		likeDAO lDAO = new likeDAO();
		lDTO.setUser_num(user_num);
		lDTO.setContent_num(sDTO.getIdx());
		lDTO.setContent_type(1);
		int result = lDAO.checkLike(lDTO);		

%>
	<input id="user_num" type="hidden" name="user_num" value="<%=user_num%>">
	<input id="content_num" type="hidden" name="content_num" value="<%=sDTO.getIdx()%>">
	<input id="content_type" type="hidden" name="content_type" value="1">
	<input id="likeResult" type="hidden" value="<%=result%>">
<!-- 좋아요 끝 -->	

<input type="button" value="목록으로" onclick="location.href='./shareList.sh?pageNum=<%=pageNum %>&pageSize=<%=pageSize%>&category=<%=category%>';" class="write-btn">

<%if(user_num == sDTO.getUser_num()){ %>
<input type="button" value="수정하기" onclick="location.href='./shareContentModify.sh?pageNum=<%=pageNum %>&pageSize=<%=pageSize%>&contentNum=<%=sDTO.getIdx()%>&category=<%=category%>';" class="write-btn">
<input type="button" value="삭제하기" onclick="location.href='./shareContentDelete.sh?pageNum=<%=pageNum %>&pageSize=<%=pageSize%>&contentNum=<%=sDTO.getIdx()%>&category=<%=category%>';" class="write-btn">
<%} %>
<div class="btnwrap">
	<button id="likeBtn" class="btn"></button><br><br>
	<div id="reportBtnContainer"></div>
</div>

<!-- 댓글 -->
<form action="" name="commentListfr" >
<table id="commentListfr">
	<thead>
	<tr>
		<td class="commentIdx_share">글번호</td>
		<td class="commentUser_share">작성자</td>
		<td class="commentContent_share">내용</td>
		<td class="commentDate_share">작성날짜</td>
		<td class="commentOption_share">수정/삭제</td>
	</tr>
	</thead>
<%
	List commentList = (ArrayList) request.getAttribute("commentList");

	
	for(int i=0; i<commentList.size(); i++){
		commentDTO cDTO = (commentDTO) commentList.get(i);
		%>
		
		<script type="text/javascript">

		//jquery 시작
		$(function() {
		    // 댓글 수정
		     $("#cm<%=i%>").on("click",function() {
		    	 if(document.getElementById("cc<%=i%>").readOnly){
		    			document.getElementById("cc<%=i%>").readOnly=false;
		    			document.getElementById("cc<%=i%>").focus();
		    		}else{
		    			document.getElementById("cc<%=i%>").readOnly=true;	
					    $.ajax({
					    	url:"./shareCommentModifyUpdateAction.sh",
					        type:"post",
					        data:{"content":$("#cc<%=i%>").val(), "comment_idx":$("#ci<%=i%>").val()},
					        success:function(data){
					          	location.reload();
					         } 
					   });
		    		}
		   });
		});
		</script>
		<tbody>
	<tr>
		<td><%=commentList.size()-i %></td>
		<td><%=new userDAO().getUserNickNameByNum(cDTO.getUser_num())%></td>
		<td><input type="text" value="<%=cDTO.getContent() %>" readonly id="cc<%=i%>" class="cc"
		style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
		<td><%=cDTO.getCreate_at() %></td>
		<%
		if(user_num == cDTO.getUser_num()){ %>
		<td>
		<input type="button" value="삭제" onclick="location.href='./shareCommentDeleteAction.sh?comment_idx=<%=cDTO.getComment_idx()%>&pageNum=<%=pageNum %>&pageSize=<%=pageSize%>&contentNum=<%=sDTO.getIdx()%>&category=<%=category %>'" class="write-btn">
		<input type="button" value="수정" id="cm<%=i%>" class="write-btn"> 
		<input type="hidden" value="<%=cDTO.getComment_idx() %>" id="ci<%=i%>"/>
		</td>
		
		<%} %>
<% } %>
</tr>
</tbody>
</table>
</form>
<script type="text/javascript">

	function insertCommentCheck() {
		var user_num = <%=user_num%>;
		if(user_num == 0){
			if(confirm("로그인이 필요합니다. 로그인페이지로 가시겠습니까?")) {
				location.href = "./login.us";
				return false;
			} else {
				return false;				
			}
		}
		if(document.commentfr.comment.value == ""){
			alert("댓글을 작성해 주세요.");
			document.commentfr.comment.focus();
			return false;
		}
	}

</script>
<br>
<form action="./shareCommentAction.sh?pageNum=<%=pageNum %>&pageSize=<%=pageSize%>&contentNum=<%=sDTO.getIdx()%>&category=<%=category %>"
		 method="post" onsubmit="return insertCommentCheck()" name="commentfr">
 <textarea placeholder="Leave a comment here" class="form-control" id="comment" name="comment" rows="5" cols="60" style="resize: none;"></textarea>		
 <br>
 <input type="submit" value="등록하기">
 <input type="reset" value="취소">
</form>
</div>
<script type="module" src="./report/js/reportMain.js"></script>
<script type="text/javascript" src="./like/js/likeFunc.js"></script>
<br style="clear: both;">&nbsp;
<br>&nbsp;
<br>&nbsp;
<br>&nbsp;
<br>&nbsp;
</body>
</html>