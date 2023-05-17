<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 로그인 일때 -->
<c:choose>
	<c:when test="${!empty sessionUser}">
		${sessionUser.nickname}님 환영합니다. <a href="../member/logoutProcess">로그아웃</a>
	</c:when>
	<c:otherwise>
		비회원으로 접근 <a href="../member/loginPage">로그인</a>
	</c:otherwise>
</c:choose>

<h1> 자유 게시판 </h1>
<br>
<table border="1">
	<tr>
		<td>글번호</td>
		<td>제목</td>
		<td>조회수</td>
		<td>작성자</td>
		<td>작성일</td>
	</tr>
	
	<c:forEach items="${list}" var="map">
		<tr>
			<td>${map.boardDto.id}</td>
			<!--  <td><a href="./${map.boardDto.id}">${map.boardDto.title}</a></td>-->
			<td><a href="./readContentPage?id=${map.boardDto.id}">${map.boardDto.title}</a></td>			
			<td>${map.boardDto.read_count}</td>
			<td>${map.memberDto.nickname}</td>
			<td>${map.boardDto.reg_date}</td>
		</tr>
	</c:forEach>

</table>
<br>

<c:if test="${!empty sessionUser}">
	<a href="./writeContentPage">글쓰기</a>
</c:if>

</body>
</html>