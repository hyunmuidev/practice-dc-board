<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
</head>
<body>
	게시글 정보
	<br /> ${ post }
	<br />
	<br />

	<form action="/post/delete" method="post">
		<a href="/post/add">
			<button type="button">글쓰기</button>
		</a> <a href="/post/update/${ post.id }">
			<button type="button">수정</button>
		</a> <input type="hidden" name="id" value="${ post.id }" />
		<button type="submit">삭제</button>
	</form>
</body>
</html>