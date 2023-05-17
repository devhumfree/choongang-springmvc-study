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

<br>
제목 테이블, .......
<br>

<c:if test="${!empty sessionUser}">
	<a href="./writeContentPage">글쓰기</a>
</c:if>

</body>
</html>