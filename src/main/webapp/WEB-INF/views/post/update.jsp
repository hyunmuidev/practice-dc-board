<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<style>
form {
	width: 500px;
}

label {
	float: left;
	width: 25%;
	line-height: 20px;
}

input {
	float: left;
	width: 70%;
	height: 20px;
}

button {
	width: 25%;
	height: 30px;
}

div {
	padding-bottom: 12px;
}

div>* {
	margin-bottom: 10px;
}

.button-area {
	clear: both;
	margin-top: 10px;
	text-align: center;
}
</style>
</head>
<body>
	<form action="/post/update" method="post">
		<div>
			<label>제목</label> <input type="text" name="title" value="${ post.title }" />
		</div>
		<div>
			<label>작성자</label> <input type="text" name="createdBy" value="${ post.createdBy }" />
		</div>
		<div>
			<label>내용</label> <input type="text" name="content" value="${ post.content }" />
		</div>
		<div>
			<label>비밀번호</label> <input type="text" name="password" value="${ post.password }" />
		</div>

		<input type="hidden" name="id" value="${ post.id }" />
		<input type="hidden" name="boardId" value="${ post.boardId }" />
		
		<div class="button-area">
			<button type="submit">제출</button>
		</div>
	</form>
</body>
</html>