<%@page import="com.deco.user.userDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css">
<link rel="stylesheet" href="./main/style.css">
</head>
<body id="mainBodyDeco">

<%@ include file="./header.jsp" %>

<article>
<!-- 스크롤 -->
<div class="container">
  <div class="sections">
    <div class="section active" data-bgcolor="#364652">
       <h2 class="section--header">DeCo </h2>
       <h2 class="section--center">Communication <br>Cooperation<br> Completion</h2>
      <div class="section--image"><img src="./main/img/main1.png" /></div>
    </div>
    <div class="section" data-bgcolor="#d8b6ba">
      <h2 class="section--han">공유합니다.</h2> <br>
       <h3 class="section--center"> 정보 공유 게시판을 통해 개발 관련 정보를 나누며 소통합니다.</h3>
      <div class="section--image"><img src="./main/img/main2.jpg" /></div>
    </div>
    <div class="section" data-bgcolor="#EDD4B2">
      <h2 class="section--han">협력합니다.</h2>
       <h3 class="section--center">개발하고 싶은 프로젝트에 참여하고<br>도전하고 싶은 프로젝트를 구상하여 팀원을 모집합니다.   </h3>
      <div class="section--image"><img src="./main/img/main3.jpg" /></div>
    </div>
    <div class="section" data-bgcolor="#D4CBE5">
      <h2 class="section--han">완성합니다.</h2>
      <h3 class="section--center"> 지식과 경험을 공유하고 원활히 소통하여 <br> 꾸준히 성장하는 개발자를 목표로 합니다. </h3>
      <div class="section--image"><img src="./main/img/main4.jpg" /></div>
    </div>
        <div class="section" data-bgcolor="#72cbc5">
         <h2 class="section--han"> 완성형 개발자를 향한 커뮤니티 </h2>
       <h2 class="section--last"> Developer Completion </h2>
       
      <div class="section--image"><img src="./main/img/main5.jpg" /></div>
    </div>
<a href="./teamList.te" id="directTeam">팀프로젝트 바로가기</a>
  </div>
</div>


  <script  src="./main/script.js"></script>
  <!-- 스크롤 -->
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>

</article>

	
</body>
</html>