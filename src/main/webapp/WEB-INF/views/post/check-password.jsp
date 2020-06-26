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
  <title>KT ROLSTER Gallery - 비밀번호 확인</title>

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
    <h1>KT ROLSTER Gallery</h1>
  </header>
  <main class="container">
    <div class="d-flex justify-content-center align-content-center" style="margin-top: 8rem">
      <div class="card w-75">
        <h4 class="card-header">게시글 비밀번호 확인</h4>
        <div class="card-body">
          <h5 class="card-title">글 비밀번호를 입력하세요</h5>
          <form method="post">
            <div>
              <input type="password" name="password" class='form-control ${ errorMessage != null ? "is-invalid" : "" }' required="required">
              <div class="invalid-feedback">
              	${ errorMessage }
              </div>
            </div>
            <div class="button-area mt-3 row">
              <div class="col-lg-6 col-12 pt-3">
                <button type="submit" class="btn btn-lg btn-block btn-primary">확인</button>
              </div>
              <div class="col-lg-6 col-12 pt-3">
              	<a class="btn btn-lg btn-block btn-secondary" href="/post/${ postId }">
              		뒤로가기
              	</a>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </main>
  <footer>
  </footer>
</body>

</html>