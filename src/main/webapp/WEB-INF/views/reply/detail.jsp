<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reply Detail</title>
</head>
<body>
	Reply Informations <br />
	id : ${ reply.id }<br />
	postId : ${ reply.postId }<br />
	createdBy : ${ reply.createdBy }<br />
	password : ${ reply.password }<br />
	content : ${ reply.content }<br />
	replyStatus : ${ reply.replyStatus }<br />
	createdDateTime : ${ reply.createdDateTime }<br />
	updatedDateTime : ${ reply.updatedDateTime }<br />
	<br />
	<form action="/reply/delete" method="post">
		<input type="hidden" name="id" value="${ reply.id }" />
		<button type="submit">삭제</button>
	</form>
</body>
</html>