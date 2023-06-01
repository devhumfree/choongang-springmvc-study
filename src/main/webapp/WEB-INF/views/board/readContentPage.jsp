<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<script>

	const boardId = new URLSearchParams(location.search).get("id");

	function ajaxTemplete(){
		
		const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				const response = JSON.parse(xhr.responseText);
				// js 작업..
			}
		}
		
		//get
		xhr.open("get", "요청 url?파라메터=값");
		xhr.send();
		
		//post
		xhr.open("post", "요청 url");
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded"); 
		xhr.send("파라메터=값");
	}

	let mySessionId = null;

	function getSessionId(){
		const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				const response = JSON.parse(xhr.responseText);
				// js 작업..
				if(response.result == "success"){
					mySessionId = response.id; 
				}
			}
		}
		
		//get
		xhr.open("get", "../member/getMyId", false); // 딱 여기만 쓰세요...false 동기식 호출..! 권장되지 않음
		xhr.send();		
	}

	function refreshTotalLikeCount(){
		const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				const response = JSON.parse(xhr.responseText);
				// js 작업..
				const totalLikeCountBox = document.getElementById("totalLikeCount");
				totalLikeCountBox.innerText = response.count;
			}
		}
		
		
		
		//get
		xhr.open("get", "./getTotalLikeCount?boardId=" + boardId);
		xhr.send();		
	}
	
	function toggleLike(){
		if(mySessionId == null){
			if(confirm("로그인을 하셔야 이용하실 수 있습니다. 로그인 하시겠습니까?")){
				location.href = "../member/loginPage";
			}
			
			return;
		}
		
		const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				const response = JSON.parse(xhr.responseText);
				// js 작업..
				refreshTotalLikeCount();
				refreshMyHeart();
			}
		}
		
		//get
		xhr.open("get", "./toggleLike?board_id=" + boardId);
		xhr.send();
		
	}
	
	function refreshMyHeart(){
		
		if(mySessionId == null) return;
		
		
		const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				const response = JSON.parse(xhr.responseText);
				// js 렌더링... 작업..
				const heartBox = document.getElementById("heartBox");
				
				if(response.isLiked){
					heartBox.classList.remove("bi-heart");
					heartBox.classList.add("bi-heart-fill");
				}else{
					heartBox.classList.remove("bi-heart-fill");
					heartBox.classList.add("bi-heart");
				}
			}
		}
		
		//get
		xhr.open("get", "./isLiked?board_id=" + boardId);
		xhr.send();
		
	}
	
	function registerComment() {
		
		if(!mySessionId) {
			// 로그인 안되어 있음
			alert("로그인 안되어있음")
			return;
		}
		
		const commentTextBox = document.getElementById("commentTextBox")
		const commentTextValue = commentTextBox.value
	
		const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				const response = JSON.parse(xhr.responseText);
				// js 작업..
				commentTextBox.value = "";
				reloadCommentList()
			}
		}
		
		//post
		xhr.open("post", "./registerComment");
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded"); 
		xhr.send("board_id="+boardId + "&comment_text=" + commentTextValue);
		
	}
	
	function reloadCommentList() {
		const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				const response = JSON.parse(xhr.responseText);
				// js 작업..
				
				document.getElementById("commentListBox").innerHTML = "";
				
				/* console.log(response) */
				for(data of response.commentList) {
					const row1 = document.createElement("div")
					row1.classList.add("row")
					row1.classList.add("commentOuter")
					row1.setAttribute("commentId",data.commentDto.id )
					
					
					const colCommentBox = document.createElement("div")
					colCommentBox.classList.add("commentBox")
					colCommentBox.classList.add("col-8")
					colCommentBox.classList.add("bg-primary")
					
					colCommentBox.innerText = data.commentDto.comment_text // 데이터 세팅
					row1.appendChild(colCommentBox)
					
					const colNickname=document.createElement("div")
					colNickname.classList.add("col-2")
					colNickname.classList.add("col-secondary")
					colNickname.innerText = data.memberDto.nickname
					row1.appendChild(colCommentBox)
					
					if(mySessionId != null && data.commentDto.member_id) {
						const colDelete = document.createElement("div")
						colDelete.classList.add("col-1")
						colDelete.innerText="삭제"
						colDelete.setAttribute("onclick", "deleteComment("+data.commentDto.id+")")
						row1.appendChild(colDelete)
						
						const colUpdate = document.createElement("div")
						colUpdate.classList.add("col-1")
						colUpdate.innerText="수정"
						colUpdate.setAttribute("onclick", "changeInputForUpdateComment(this)")
						row1.appendChild(colUpdate)
					}
					
					document.getElementById("commentListBox").appendChild(row1)
				}
			}
		}
		
		//get
		xhr.open("get", "./getCommentList?boardId=" + boardId);
		xhr.send();
		
	}
	
	function deleteComment(id) {
		const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				const response = JSON.parse(xhr.responseText);
				// js 작업..
				reloadCommentList()
			}
		}
		
		//get
		xhr.open("get", "./deleteComment?id="+id);
		xhr.send();
	}
	
	function changeInputForUpdateComment(targetElement){
		const commentOuter = targetElement.closest(".commentOuter")
		
		const commentBox = commentOuter.getElementsByClassName("commentBox")[0]
		const tempValue = commentBox.innerText
		commentBox.innerHTML = ""
		
		const inputTextComment = document.createElement("input")
		inputTextComment.type = "text"
		//inputTextComment.classList.add("form-control")
		inputTextComment.classList.add("commentInputBox")
		inputTextComment.value = tempValue
		commentBox.appendChild(inputTextComment)
		
		const inputButtonUpdate = document.createElement("button")
		inputButtonUpdate.innerText = "수정 적용"
		inputButtonUpdate.setAttribute("onclick","updateComment(this)")
		commentBox.appendChild(inputButtonUpdate)
	}
	
	function updateComment(targetElement) {
		const commentOuter = targetElement.closest(".commentOuter")
		
		const commentId = commentOuter.getAttribute("commentId")
		const commentText = commentOuter.querySelector(".commentInputBox").value
		
const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				const response = JSON.parse(xhr.responseText);
				// js 작업..
				reloadCommentList()
			}
		}
		
		//post
		xhr.open("post", "./updateComment");
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded"); 
		xhr.send("id="+commentId + "&comment_text=" +commentText);
	}
	

	
	window.addEventListener("DOMContentLoaded", function(){
		//사실상 시작 시점...
		getSessionId();
		refreshTotalLikeCount();
		refreshMyHeart();
		reloadCommentList();
		
	});
</script>


</head>
<body>
<h1>상세 글보기</h1>

제목: ${data.boardDto.title }<br>
작성자: ${data.memberDto.nickname }<br>
작성일: ${data.boardDto.reg_date }<br>
조회수: ${data.boardDto.read_count }<br>

<br>
<c:forEach items="${data.boardImageDtoList}" var="boardImageDto">
	<img src="/uploadFiles/${boardImageDto.link }"><br>
</c:forEach>




내용: <br>
${data.boardDto.content }<br>

<i id="heartBox" onclick="toggleLike()" class="fs-1 text-danger bi bi-heart"></i><span id="totalLikeCount">3</span>
<div class="container">
	<div class="row">
		<div class="col-7">
			<textarea id="commentTextBox" class="form-controll"></textarea>
		</div>
		<div class="col d-grid">
			<input onclick="registerComment()" class="btn btn-primary" type="button" value="댓글 작성" />
		</div>
	</div>
	<div class="row">
		<div id="commentListBox" class="col">
			<div class="row commentOuter">
				<div class="commentBox col-8 bg-primary">내용</div>
				<div class="col bg-secondary">닉네임</div>
				<div class="col bg-success">삭제</div>
				<div class="col bg-warning">수정</div>
			</div>
		</div>
	</div>
</div>
  
<br><br>
<a href="./mainPage">목록으로</a>

<c:if test="${!empty sessionUser && sessionUser.id == data.memberDto.id }">
	<a href="./updatePage?id=${data.boardDto.id }">수정</a>
	<a href="./deleteProcess?id=${data.boardDto.id }">삭제</a>
</c:if>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>