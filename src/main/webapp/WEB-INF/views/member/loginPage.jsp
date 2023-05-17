<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 로그인 페이지 </h1>
<form action="./loginProcess" method="post">
	ID: <input type="text" name="user_id"><br>
	PW: <input type="password" name="user_pw"><br>
	<input type="submit" value="로그인"><br>
</form>

<a href="./registerPage">회원가입</a> <!--상대경로 -->
<a href="/finalproject/member/registerPage">회원가입</a> <!--절대경로 -->
<a href="http://localhost:8181/finalproject/member/registerPage">회원가입</a> <!--절대경로 -->

</body>
</html>