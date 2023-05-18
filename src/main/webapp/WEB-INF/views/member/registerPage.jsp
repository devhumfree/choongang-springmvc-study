<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

			<h1>회원가입</h1>
			<form action="./registerProcess" method="post">
				ID: <input type="text" name="user_id"><br>
				PW: <input type="password" name="user_pw"><br>
				nickname: <input type="text" name="nickname"><br>
				gender: 
				<input type="radio" name="gender" value="M"> 남
				<input type="radio"  name="gender" value="F"> 여<br>
				email: <input type="text" name="email"><br>
				생년월일: <input type="date" name="birth"><br>
				전화번호: <input type="text" name="phone"><br>
				<button>회원가입</button>
			</form>
			
			<a href="./loginPage">로그인 페이지로</a>

</body>
</html>