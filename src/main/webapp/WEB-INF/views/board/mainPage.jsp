<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
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
		
		<div class="row">
			<div class="col">
				<img alt="" src="/finalproject/resources/img/s.png" class="img-fluid">
			</div>
		</div>
		
		<form action="./mainPage" method="get">
			<div class="row mt-3"><!-- 검색 -->
				<div class="col-2">
					<select name="searchType" class="form-select">
						<option value="title" selected>제목</option>
						<option value="content">내용</option>
						<option value="nickname">작성자</option>
					</select>				
				</div>
				<div class="col-8">
					<input name="searchWord" type="text" class="form-control">
				</div>
				<div class="col-2 d-grid">
					<button class="btn btn-primary">검색</button>
				</div>
			</div>
		</form>

		<div class="row mt-2"><!-- 테이블 -->
			<div class="col">
				<table class="table table-striped table-hover">
				  <thead>
				    <tr>
				      <th scope="col">글번호</th>
				      <th scope="col">제목</th>
				      <th scope="col">조회수</th>
				      <th scope="col">작성자</th>
				      <th scope="col">작성일</th>
				    </tr>
				  </thead>
				  <tbody>
				  <c:forEach items="${list }" var="map">
				    <tr>
				      <th scope="row">${map.boardDto.id }</th>
				      <td><a href="./readContentPage?id=${map.boardDto.id }">${map.boardDto.title }</a></td>
				      <td>${map.boardDto.read_count }</td>
				      <td>${map.memberDto.nickname }</td>
				      <td><fmt:formatDate value="${map.boardDto.reg_date }" pattern="yy.MM.dd"/></td>
				    </tr>
				  </c:forEach>
				  </tbody>
				</table>			
			</div>
		</div>
		
		<div class="row"><!-- 버튼 -->
			<div class="col-6 mx-auto">
				<nav aria-label="Page navigation example">
				  <ul class="pagination mb-0">
				  <c:choose>
				  	<c:when test="${startPage <= 1}">
				   		<li class="page-item disabled"><a class="page-link" href="./mainPage?page=${startPage-1}${searchQueryString}">&lt;</a></li>
				  	</c:when>
				  	<c:otherwise>
				  		<li class="page-item"><a class="page-link" href="./mainPage?page=${startPage-1}${searchQueryString}">&lt;</a></li>
				  	</c:otherwise>
				  </c:choose>
	
				    <c:forEach begin="${startPage }" end="${endPage }" var="index">
				    	<c:choose>
				    		<c:when test="${index == currentPage }">
				    			<li class="page-item active"><a class="page-link" href="./mainPage?page=${index}${searchQueryString}">${index}</a></li>
				    		</c:when>
				    		<c:otherwise>
				    			<li class="page-item"><a class="page-link" href="./mainPage?page=${index}${searchQueryString}">${index}</a></li>
				    		</c:otherwise>
				    	</c:choose>
				    </c:forEach>
				    
				    <c:choose>
					    <c:when test="${endPage >= totalPage}">
					   		<li class="page-item disabled"><a class="page-link" href="./mainPage?page=${endPage+1}${searchQueryString}">&gt;</a></li>
					  	</c:when>
					  	<c:otherwise>
					  		<li class="page-item"><a class="page-link" href="./mainPage?page=${endPage+1}${searchQueryString}">&gt;</a></li>
					  	</c:otherwise>
				    </c:choose>
				    
				  </ul>
				</nav>
			</div>
			<div class="col-2 ms-auto d-grid">
				<c:if test="${!empty sessionUser }">
					<a href="./writeContentPage" class="btn btn-primary">글쓰기</a>
				</c:if>
			</div>
		</div>
	
	</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>