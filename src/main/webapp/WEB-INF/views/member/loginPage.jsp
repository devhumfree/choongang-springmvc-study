<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col">
				<jsp:include page="../common/topNavi.jsp"></jsp:include>
			</div>
		</div>
	
		<div class="row mt-5">
			<div class="col"></div>
			<div class="col-6">
				<form action="./loginProcess" method="post">
				<div class="row mt-5">
					<div class="col fw-bold fs-1 text-center">로그인</div>
				</div>
				<div class="row mt-3">
					<div class="col">
						<input class="form-control" type="text" name="user_id" placeholder="아이디">
					</div>
				</div>
				<div class="row mt-2">
					<div class="col">
						<input class="form-control" type="password" name="user_pw" placeholder="비밀번호">
					</div>
				</div>
				<div class="row mt-3">
					<div class="col d-grid">
						<button class="btn btn-primary">로그인</button>
					</div>
				</div>
				<div class="row mt-2">
					<div class="col d-grid">
						<a href="./registerPage" class="btn btn-outline-primary">회원가입</a>
					</div>
				</div>
				</form>
			</div>
			<div class="col"></div>
		</div>
	</div>