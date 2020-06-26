<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fakedc.practiceboard.utils.JspViewHelper"%>
<%@ page import="com.fakedc.practiceboard.domain.enums.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sp-form"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>KT ROLSTER Gallery - 게시글 읽기</title>

<link href="/css/base.css" rel="stylesheet" />
<link href="/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.min.css">

<script src="/lib/jquery/jquery-3.5.1.min.js"></script>
<script src="/lib/bootstrap/js/popper.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-util.js"></script>
</head>

<body>
	<header class="container">
		<h1>KT ROLSTER Gallery - 게시글</h1>
	</header>
	<main class="container">
		<div class="card mb-3">
			<div class="card-header">
				<div class="row">
					<div class="col-9">
						<h4>
							<i class="fa fa-book" aria-hidden="true"></i> [${ post.postType.getName() }]
							${ post.title }
						</h4>
						<span>${ post.createdBy }</span> | <span>${ JspViewHelper.parseLocalDateTime(post.updatedDateTime, "yyyy.MM.dd hh:mm:ss") }</span>
					</div>
					<div class="col-3 d-flex justify-content-end align-items-center">
						조회 ${ post.viewCount } |&nbsp; <a href="#recommend">추천 ${ post.recommendCount }</a>&nbsp;|&nbsp;
						<a href="#replies">댓글 ${ replies.size() }</a>
					</div>
				</div>
			</div>
			<div class="card-body">
				<div class="post-content">${ post.content.replaceAll("\\n", "<br />") }</div>
				<div id="recommend"
					class="post-recommend d-flex justify-content-center">
					<sp-form:form action="/post/recommend" method="post"
						modelAttribute="post">
						<button type="submit" class="btn btn-primary post-recommend-up">
							<h5>
								<i class="fa fa-thumbs-up" aria-hidden="true"></i>
							</h5>
							<div>${ post.recommendCount }</div>
						</button>
						<sp-form:hidden path="id" />
					</sp-form:form>
					<sp-form:form action="/post/unrecommend" method="post"
						modelAttribute="post">
						<button type="submit"
							class="btn btn-secondary post-recommend-down">
							<h5>
								<i class="fa fa-thumbs-down" aria-hidden="true"></i>
							</h5>
							<div>${ post.unrecommendCount }</div>
						</button>
						<sp-form:hidden path="id" />
					</sp-form:form>
				</div>
			</div>
		</div>
		<div class="post-command row mb-3">
			<div class="col-6">
				<a href="/board/${ post.boardId }?${ JspViewHelper.getUrlBoardFilterParams(searchBoardFilter, pageable) }" class="btn btn-lg btn-primary">목록</a>
			</div>
			<div class="col-6 d-flex justify-content-end">
				<a class="btn btn-lg btn-success ml-3"
					href="/post/check-password/${ post.id }">수정</a>
				<sp-form:form action="/post/delete" method="post"
					modelAttribute="post">
					<button type="submit" class="btn btn-lg btn-danger ml-3">삭제</button>
					<sp-form:hidden path="id" />
				</sp-form:form>
			</div>
		</div>
		<div class="reply-area">
			<h6 id="replies">전체 리플 ${ replies.size() }개</h6>
			<hr />
			<ul>
				<c:forEach var="reply" items="${ replies }">
					<li class="row">
						<div class="col-2">${ reply.createdBy }</div>
						<div class="col-6">${ reply.content }</div>
						<div class="col-2">${ JspViewHelper.parseLocalDateTime(reply.updatedDateTime, "yyyy.MM.dd hh:mm:ss") }</div>
						<!-- TODO: 수정 / 삭제 링크 구현 필요 (with password check) -->
						<div
							class="reply-command col-2 d-flex justify-content-end align-content-center">
							<button class="btn btn-primary">
								<i class="fa fa-edit" aria-hidden="true"></i>
							</button>
							<button class="btn btn-secondary">
								<i class="fa fa-trash" aria-hidden="true"></i>
							</button>
						</div>
					</li>
				</c:forEach>
			</ul>
			<form action="/reply/add" class="reply-add row mb-3" method="post"
				name="reply_add_form">
				<div class="col-3">
					<input type="text" class="form-control form-control-lg"
						name="createdBy" placeholder="작성자" /> <input type="password"
						class="form-control form-control-lg" name="password"
						placeholder="비밀번호" />
				</div>
				<div class="col-7">
					<textarea class="form-control form-control-lg" name="content"
						rows="3" placeholder="댓글을 입력하세요"></textarea>
				</div>
				<div class="col-2">
					<button type="submit" class="btn btn-lg btn-block btn-primary">등록</button>
					<button type="button" class="btn btn-lg btn-block btn-success"
						onclick="recommendAndAddReply()">등록+추천</button>
				</div>

				<input type="hidden" name="postId" value="${ post.id }" />
			</form>
			<script>
				function recommendAndAddReply() {
					reply_add_form.action = '/reply/add?withRecommend=true'
					reply_add_form.submit();
				}
			</script>
		</div>
	</main>
	<footer> </footer>
</body>

</html>