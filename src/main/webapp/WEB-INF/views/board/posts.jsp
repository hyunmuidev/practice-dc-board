<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fakedc.practiceboard.utils.JspViewHelper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sp-form"
	uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>KT ROLSTER Gallery</title>

<link href="/css/base.css" rel="stylesheet" />
<link href="/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.min.css">

<script src="/lib/jquery/jquery-3.5.1.min.js"></script>
<script src="/lib/bootstrap/js/popper.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
	<header class="container">
		<h1>KT ROLSTER Gallery</h1>
	</header>
	<main class="container">
		<div class="card">
			<div class="card-header">
				<ul class="nav nav-pills card-header-pills">
					<li class="nav-item"><a class="nav-link active" href="#">전체글</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">일반</a></li>
					<li class="nav-item"><a class="nav-link" href="#">공지</a></li>
				</ul>
			</div>
			<div class="card-body">
				<div class="search-filter row mb-3">
					<div class="offset-3 col-6">
						<div class="input-group">
							<div class="input-group-prepend btn-group">
								<button class="btn btn-outline-secondary dropdown-toggle"
									type="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">제목+내용</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">전체</a> <a
										class="dropdown-item" href="#">제목</a> <a class="dropdown-item"
										href="#">내용</a> <a class="dropdown-item" href="#">글쓴이</a> <a
										class="dropdown-item" href="#">제목+내용</a>
								</div>
							</div>
							<input type="text" class="form-control" />
							<div class="input-group-append">
								<button class="btn btn-primary" type="button">검색</button>
							</div>
						</div>
					</div>
					<div class="col-3 d-flex justify-content-end">
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">10</button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">10</a> <a
									class="dropdown-item" href="#">30</a> <a class="dropdown-item"
									href="#">50</a>
							</div>
						</div>
					</div>
				</div>
				<div class="post-list">
					<table class="table text-center">
						<thead class="thead-dark text-center">
							<tr>
								<th scope="col">#</th>
								<th scope="col">말머리</th>
								<th scope="col">제목</th>
								<th scope="col">글쓴이</th>
								<th scope="col">작성일</th>
								<th scope="col">조회</th>
								<th scope="col">추천</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="post" items="${ posts }">
								<tr>
									<th scope="row">${ post.id }</th>
									<td>${ post.postType.getName() }</td>
									<td class="text-left"><a href="/post/${ post.id }"> ${ post.title }
									</a></td>
									<td>${ post.createdBy }</td>
									<td>${ JspViewHelper.parseLocalDateTime(post.createdDateTime, "yyyy.MM.dd") }</td>
									<td>${ post.viewCount }</td>
									<td>${ post.recommendCount }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="row">
						<div class="offset-2 col-8 d-flex justify-content-center">
							<nav aria-label="...">
								<ul class="pagination">
									<li class="page-item disabled"><a class="page-link"
										href="#" tabindex="-1" aria-disabled="true">Previous</a></li>
									<li class="page-item"><a class="page-link" href="#">1</a></li>
									<li class="page-item active" aria-current="page"><a
										class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
									</li>
									<li class="page-item"><a class="page-link" href="#">3</a></li>
									<li class="page-item"><a class="page-link" href="#">Next</a>
									</li>
								</ul>
							</nav>
						</div>
						<div class="col-2 d-flex justify-content-end">
							<a href="/post/add">
								<button class="btn btn-lg btn-success" type="button">글쓰기</button>
							</a>
						</div>
					</div>
				</div>
				<div class="search-filter row mb-3">
					<div class="offset-3 col-6">
						<div class="input-group">
							<div class="input-group-prepend">
								<button class="btn btn-outline-secondary dropdown-toggle"
									type="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">제목+내용</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">전체</a> <a
										class="dropdown-item" href="#">제목</a> <a class="dropdown-item"
										href="#">내용</a> <a class="dropdown-item" href="#">글쓴이</a> <a
										class="dropdown-item" href="#">제목+내용</a>
								</div>
							</div>
							<input type="text" class="form-control"
								aria-label="Text input with dropdown button">
							<div class="input-group-append">
								<button class="btn btn-primary" type="button">검색</button>
							</div>
						</div>
					</div>
					<div class="col-3 d-flex justify-content-end"></div>
				</div>
			</div>
		</div>
	</main>
	<footer> </footer>
</body>

</html>