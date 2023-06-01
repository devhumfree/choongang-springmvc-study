<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<script>

	const boardId = new URLSearchParams(location.search).get("id")

	function ajaxTemplete() {
		
		const xhr = new XMLHttpRequest()
		
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200) {
				const response = JSON.parse(xhr.responseText)
				// js 작업
			}
		}
		
		//get
		xhr.open("get", "요청 url파라미터=값")
		xhr.send();
		
		//post
		xhr.open("post", "요청 url")
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
		xhr.send("파라미터 = 값")
		
	}
	
	let mySessionId = null
	
	function getSessionId() {
		const xhr = new XMLHttpRequest()
		
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200) {
				const response = JSON.parse(xhr.responseText)
				// js 작업
				if(response.result == "success") {
					mySessionId = response.id
				}
			}
		}
		
		//get
		xhr.open("get", "../member/getMyId", false) // 딱 여기만 쓰세요. false 동기식 호출 권장되지 않음
		xhr.send();
	}
		
	function refreshTotalLikeCount() {
		const xhr = new XMLHttpRequest()
		
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200) {
				const response = JSON.parse(xhr.responseText)
				// js 작업
				const totalLikeCountBox = document.getElementById("totalLikeCountBox")
				totalLikeCountBox.innerText = response.count
			}
		}
		
		
		//get
		xhr.open("get", "../getTotalLikeCount?boardId=" + boardId)
		xhr.send();
	}
	
	function toggleLike() {
		if(mySessionId == null) {
			if(confirm("로그인을 하셔야 이용하실 수 있습니다. 로그인 하시겠습니까")) {
				location.href = "../member/loginPage";
			}
			return;
		}
		
		const xhr = new XMLHttpRequest()
		
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200) {
				const response = JSON.parse(xhr.responseText)
				// js 작업
				refreshTotalLikeCount()
				refreshMyHeart() 
				
			}
		}
		
		//get
		xhr.open("get", "./toggleLike?boardId="+boardId)
		xhr.send();
		
	}
	
	function refreshMyHeart() {
		
		if(mySessionId == null) return
		
		const xhr = new XMLHttpRequest()
				
				xhr.onreadystatechange = function() {
					if(xhr.readyState == 4 && xhr.status == 200) {
						const response = JSON.parse(xhr.responseText)
						// js 작업
							const heartBox = document.getElementById("heartBox")
						if(response.isLiked) {
							heartBox.classList.remove("bi-heart")
							heartBox.classList.add("bi-heart-fill")
						}else {
							heartBox.classList.remove("bi-heart-fill")
							heartBox.classList.add("bi-heart")
							
						}
					}
				}
				
				//get
				xhr.open("get", "./isLiked?board_id="+boardId)
				xhr.send();	
		
		
	}
	
	window.addEventListener("DOMContentLoded", function() {
		getSessionId()
		refreshTotalLikeCount()
		refreshMyHeart() 
	})
</script>

</head>
<body>
	<h1>상세 글 보기</h1>
	
	제목: ${data.boardDto.title} <br>
	작성자: ${data.memberDto.nickname} <br>
	작성일: ${data.boardDto.reg_date} <br>
	조회수: ${data.boardDto.read_count}<br>
	<br>
	<c:forEach items="${data.boardImageDtoList}" var="boardImageDto">
		<img src="/uploadFiles/${boardImageDto.link}"><br>
	</c:forEach>
	
	내용: <br>
	${data.boardDto.content}<br>
	
	
	<i onclick="toggleLike()"  id="heard-box" class="fs-1 text-danger bi bi-heart"></i><span id="totalLikeCountBox"></span>
	
	<br><br>
	<a href="./mainPage">목록으로</a>
	
	<c:if test="${!empty sessionUser &&  sessionUser.id == data.memberDto.id }">
		<a href="./updatePage?id=${data.boardDto.id }">수정</a>
		<a href="./deleteProcess?id=${data.boardDto.id }">삭제</a>
	</c:if>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>