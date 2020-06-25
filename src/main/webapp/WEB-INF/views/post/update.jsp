<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>KT ROLSTER Gallery - 게시글 쓰기</title>

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
    <div class="card">
      <div class="card-header">
        <h3>
          <i class="fa fa-pencil" aria-hidden="true"></i>
          게시글 쓰기
        </h3>
      </div>
      <form class="card-body">
        <div class="form-group row">
          <label class="col-2 col-form-label">제목</label>
          <div class="col-sm-10">
            <input type="text" class="form-control">
          </div>
        </div>
        <div class="form-group row">
          <label class="col-2 col-form-label">말머리</label>
          <div class="col-sm-10">
            <div class="btn-group btn-group-lg btn-group-toggle w-25" data-toggle="buttons">
              <label class="btn btn-lg btn-primary active">
                <input type="radio" name="options" autocomplete="off" checked> 일반
              </label>
              <label class="btn btn-lg btn-primary">
                <input type="radio" name="options" autocomplete="off"> 공지
              </label>
            </div>
          </div>
        </div>
        <div class="form-group row">
          <label class="col-2 col-form-label">닉네임</label>
          <div class="col-sm-10">
            <input type="text" class="form-control">
          </div>
        </div>
        <div class="form-group row">
          <label class="col-2 col-form-label">Password</label>
          <div class="col-sm-10">
            <input type="password" class="form-control">
          </div>
        </div>
        <div class="form-group row">
          <label class="col-2 col-form-label">내용</label>
          <div class="col-sm-10">
            <textarea class="form-control" rows="15"></textarea>
          </div>
        </div>
        <div class="button-area d-flex justify-content-end">
          <button type="submit" class="btn btn-lg btn-success ml-3">등록</button>
          <button type="submit" class="btn btn-lg btn-secondary ml-3">취소</button>
        </div>
      </form>
    </div>
  </main>
  <footer>
  </footer>
</body>

</html>