<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

function checkValueAndSubmit() {
	
	const userIdBox = document.getElementById("userId")
	const idValue = userIdBox.value
	
	const idRegEx = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{3,16}$/;

	if (!idRegEx.test(idValue)) {
	  	alert("아이디는 영소문자로 시작하며 소,대문자, 숫자로만 이용 3-16글자 여야 합니다.")
	  	userIdBox.focus()
	} else {
	}
	
	if(idValue == "") {
		alert("아이디를 입력해주세요!")
		userIdBox.focus()
		return
	}

	const userPwBox = document.getElementById("userPw")
	const userPwConfirmBox = document.getElementById("userPwConfirm")
	
	if(userPwBox.value != userPwConfirmBox.value) {
		userPwBox.value = ""
			userPwConfirmBox.value =""
		alert("비밀번호 확인을 해주세요!")
		
		userPwBox.focus()
	}
	
	const pWRegEx = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,16}$/;

	if (!pWRegEx.test(userPwBox.value)) {
	 	alert("비밀번호는 8-16자 특수문자 X")
	 	userPwBox.value = ""
	 	userPwBoxConfirmBox.value = ""
	 		
	 	userPwBox.focus()
		return
	}
	if(idChecked == false) {
		alert("아이디 중복확인을 해주세요")
		return
	}
	const frm = document.getElementById("frm")
	frm.submit()
}

	let idChecked = false
	
	function checkUserId() {
		
		const userIdValue = document.getElementById("userId").value
		
		// AJAX
		const xhr = new XMLHttpRequest()
		
		xhr.onreadystatechange = function () {
			if(xhr.readyState == 4 && xhr.status == 200){
				const response = JSON.parse(xhr.responseText)
				
				// js로 논리 코드 및 dom 조작
				// CSR 클라이언트 사이드 렌더링
				
				const idCheckAlert = document.getElementById("alertCheck")
				
				if(response.data == true) {
					idCheckAlert.style.color="tomato"
					idCheckAlert.innerText = "사용 불가능한 아이디 입니다."
					idChecked = false
				}else{
					idCheckAlert.style.color="lightgreen"
					idCheckAlert.innerText = "사용 가능한 아이디 입니다."
					idChecked = true
				}
			}
		}
		
		
		xhr.open("get", "./existsUserId?userId=" +userIdValue)
		xhr.send()
		
	}

</script>
</head>
<body>

	<h1>회원가입</h1>
	<form id="frm" action="./registerProcess" method="post">
		ID: <input type="text" id="userId"name="user_id" onblur="checkUserId()">
		<input onclick="checkUserId()" type="button" value="중복확인">
		
		<div id="alertCheck">
		
		</div>
		
		<br>
		PW: <input type="password" id="userPw" name="user_pw"><br>
		PW 확인: <input type="password" id="userPwConfirm" ><br>
		nickname: <input type="text" name="nickname"><br>
		gender: 
		<input type="radio" name="gender" value="M"> 남
		<input type="radio"  name="gender" value="F"> 여<br>
		취미:
		<c:forEach items="${ hobbyList}"  var = "hobby">
			<input name="hobby_id" value="${hobby.id }" type="checkbox">${hobby.name }
		</c:forEach>
		<br>	
		
		
		email: <input type="text" name="email"><br>
		생년월일: <input type="date" name="birth"><br>
		전화번호: <input type="text" name="phone"><br>
		<input type="button" value="회원가입" onclick="checkValueAndSubmit()"/>
	</form>
	
	<a href="./loginPage">로그인 페이지로</a>

</body>
</html>