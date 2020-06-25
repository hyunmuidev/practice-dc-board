<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fakedc.practiceboard.utils.JspViewHelper"%>
<%@ page
	import="com.fakedc.practiceboard.domain.viewmodel.SearchBoardFilter"%>
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
						<c:if test="${ posts.size > 10 }">
							<sp-form:form cssClass="input-group" action="/board/${ boardId }"
								method="get" modelAttribute="searchBoardFilter">
								<sp-form:select path="filterType"
									cssClass="input-group-prepend custom-select">
									<sp-form:options items="${ allBoardFilterTypes }"
										itemLabel="name" />
								</sp-form:select>
								<sp-form:input path="keyword" cssClass="form-control" />
								<div class="input-group-append">
									<button class="btn btn-primary" type="submit">검색</button>
								</div>

								<input type="hidden" name="currentPageIndex"
									value="${ posts.number }" />
								<input type="hidden" name="pagingSize" value="${ posts.size }" />
							</sp-form:form>
						</c:if>
					</div>
					<div class="col-3 d-flex justify-content-end">
						<sp-form:form cssClass="btn-group" action="/board/${ boardId }"
							method="get" modelAttribute="searchBoardFilter"
							name="form_page_size">
							<select name="pagingSize"
								class="input-group-prepend custom-select"
								onchange="form_page_size.submit()">
								<c:forEach var="value"
									items="${ SearchBoardFilter.TYPEOF_PAGE_SIZE }">
									<option value="${ value }"
										${ posts.size == value ? "selected" : "" }>${ value }</option>
								</c:forEach>
							</select>

							<input type="hidden" name="currentPage" value="1" />
							<sp-form:hidden path="filterType" />
							<sp-form:hidden path="keyword" />
						</sp-form:form>
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
							<c:choose>
								<c:when test="${ posts.totalElements > 0 }">
									<c:forEach var="post" items="${ posts.content }">
										<tr>
											<th scope="row">${ post.id }</th>
											<td>${ post.postType.getName() }</td>
											<td class="text-left"><a href="/post/${ post.id }">
													${ post.title } </a></td>
											<td>${ post.createdBy }</td>
											<td>${ JspViewHelper.parseLocalDateTime(post.createdDateTime, "yyyy.MM.dd") }</td>
											<td>${ post.viewCount }</td>
											<td>${ post.recommendCount }</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<th colspan="7">No Data</th>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<div class="row">
						<div class="offset-2 col-8 d-flex justify-content-center">
							<nav aria-label="...">
								<ul class="pagination">
									<c:choose>
										<c:when test="${ !posts.hasPrevious() }">
											<li class="page-item disabled"><a class="page-link"
												href="#" aria-disabled="true">이전</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="/board/${ boardId }?${ JspViewHelper.getUrlPageableParams(posts.previousPageable()) }&${ searchBoardFilter.urlParams }"
												aria-disabled="true">이전</a></li>
										</c:otherwise>
									</c:choose>
									<c:if test="${ posts.totalPages > 0 }">
										<c:forEach begin="0" end="${ posts.totalPages - 1 }"
											varStatus="status">
											<li
												class='page-item ${ posts.number == status.current ? "active" : "" }'><a
												class="page-link"
												href="/board/${ boardId }?${ JspViewHelper.getUrlPageableParams(status.current + 1, posts.size) }&${ searchBoardFilter.urlParams }">${ status.current + 1 }</a>
											</li>
										</c:forEach>
									</c:if>
									<c:choose>
										<c:when test="${ !posts.hasNext() }">
											<li class="page-item disabled"><a class="page-link"
												href="#" aria-disabled="true">다음</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="/board/${ boardId }?${ JspViewHelper.getUrlPageableParams(posts.nextPageable()) }&${ searchBoardFilter.urlParams }"
												aria-disabled="true">다음</a></li>
										</c:otherwise>
									</c:choose>
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
						<sp-form:form cssClass="input-group" action="/board/${ boardId }"
							method="get" modelAttribute="searchBoardFilter">
							<sp-form:select path="filterType"
								cssClass="input-group-prepend custom-select">
								<sp-form:options items="${ allBoardFilterTypes }"
									itemLabel="name" />
							</sp-form:select>
							<sp-form:input path="keyword" cssClass="form-control" />
							<div class="input-group-append">
								<button class="btn btn-primary" type="submit">검색</button>
							</div>

							<input type="hidden" name="currentPageIndex"
								value="${ posts.number }" />
							<input type="hidden" name="pagingSize" value="${ posts.size }" />
						</sp-form:form>
					</div>
				</div>
				<div class="col-3 d-flex justify-content-end"></div>
			</div>
		</div>
	</main>
	<footer> </footer>
</body>

</html>