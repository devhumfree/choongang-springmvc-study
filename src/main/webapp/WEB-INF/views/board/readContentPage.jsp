<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>상세 글 보기</h1>

제목: ${data.boardDto.title} <br>
작성자: ${data.memberDto.nickname} <br>
작성일: ${data.boardDto.reg_date} <br>
조회수: ${data.boardDto.read_count}<br>
내용: <br>
${data.boardDto.content}<br>

<br><br>
<a href="./mainPage">목록으로</a>

<c:if test="${!empty sessionUser &&  sessionUser.id == data.memberDto.id }">
	<a href="./updatePage?id=${data.boardDto.id }">수정</a>
	<a href="./deleteProcess?id=${data.boardDto.id }">삭제</a>
</c:if>

</body>
</html>