<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시글 쓰기</h1>

<!-- 만약 파일을 보내야되는 경우 encoding - enctype="multipart/form-data" 꼭 변경해야한다 무조건 POST방식!  -->
<form action="./writeContentProcess" method="post" enctype="multipart/form-data">
	작성자: ${sessionUser.nickname}<br>
	제목: <input type="text" name="title"><br>
	내용: <br>
	<textarea rows="10" cols="60" name="content"></textarea>
	<br>
	<input name="boardFiles" type="file" multiple accept="image/*">
	<br>
	<button>글 쓰기</button>
</form> 

</body>
</html>