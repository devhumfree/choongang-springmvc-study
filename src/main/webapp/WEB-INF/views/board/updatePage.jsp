<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시글 수정</h1>

<form action="./updateContentProcess" method="post">
	작성자: ${sessionUser.nickname}<br>
	제목: <input type="text" name="title"  value="${data.boardDto.title}"><br>
	내용: <br>
	<textarea rows="10" cols="60" name="content">${data.boardDto.content}</textarea>
	<input type="hidden" name="id"  value="${data.boardDto.id}">
	<br>
	<button>글 수정</button>
</form> 

</body>
</html>