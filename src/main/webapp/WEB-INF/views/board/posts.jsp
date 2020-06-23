<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 여러개 보기</title>
</head>
<body>

<c:forEach var="post" items="${ posts }">
	<div>${ post }</div>
</c:forEach>

</body>
</html>