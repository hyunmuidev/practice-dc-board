<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <h1>KT ROLSTER Gallery</h1>
  </header>
  <main class="container">
    <div class="card mb-3">
      <div class="card-header">
        <div class="row">
          <div class="col-9">
            <h4>
              <i class="fa fa-book" aria-hidden="true"></i>
              [일반] 일반적인 응원글입니다.
            </h4>
            <span>ㅇㅇ</span> | <span>2020.06.01 01:00:00</span>
          </div>
          <div class="col-3 d-flex justify-content-end align-items-center">
            조회 1234 |&nbsp;
            <a href="#">추천 20</a>&nbsp;|&nbsp;
            <a href="#replies">댓글 2</a>
          </div>
        </div>
      </div>
      <div class="card-body">
        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Blanditiis nihil porro, rem quo autem at cum
          accusantium earum debitis! Provident excepturi reiciendis hic amet, laborum sit asperiores sapiente possimus
          similique.</p>
        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Blanditiis nihil porro, rem quo autem at cum
          accusantium earum debitis! Provident excepturi reiciendis hic amet, laborum sit asperiores sapiente possimus
          similique.</p>
        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Blanditiis nihil porro, rem quo autem at cum
          accusantium earum debitis! Provident excepturi reiciendis hic amet, laborum sit asperiores sapiente possimus
          similique.</p>

        <div class="post-recommend d-flex justify-content-center">
          <button class="btn btn-primary post-recommend-up">
            <h5><i class="fa fa-thumbs-up" aria-hidden="true"></i></h5>
            <div>20</div>
          </button>
          <button class="btn btn-secondary post-recommend-down">
            <h5><i class="fa fa-thumbs-down" aria-hidden="true"></i></h5>
            <div>20</div>
          </button>
        </div>
      </div>
    </div>
    <div class="post-command d-flex justify-content-end">
      <button class="btn btn-lg btn-success ml-3">수정</button>
      <button class="btn btn-lg btn-danger ml-3">삭제</button>
    </div>
    <div class="reply-area">
      <a name="replies">전체 리플 2개</a>
      <hr />
      <ul>
        <li class="row">
          <div class="col-2">ㅇㅇ</div>
          <div class="col-6">
            대충 아무렇게나 날리는 선플1 <br />
            호호호 <br />
            여러줄짜리도 써보는중
          </div>
          <div class="col-2">2020.06.01 01:10:00</div>
          <div class="reply-command col-2 d-flex justify-content-end align-content-center">
            <button class="btn btn-primary">
              <i class="fa fa-edit" aria-hidden="true"></i>
            </button>
            <button class="btn btn-secondary">
              <i class="fa fa-trash" aria-hidden="true"></i>
            </button>
          </div>
        </li>
        <li class="row">
          <div class="col-2">ㅇㅇ</div>
          <div class="col-6">대충 아무렇게나 날리는 선플1</div>
          <div class="col-2">2020.06.01 01:10:00</div>
          <div class="reply-command col-2 d-flex justify-content-end align-content-center">
            <button class="btn btn-primary">
              <i class="fa fa-edit" aria-hidden="true"></i>
            </button>
            <button class="btn btn-secondary">
              <i class="fa fa-trash" aria-hidden="true"></i>
            </button>
          </div>
        </li>
      </ul>
      <form class="reply-add row">
        <div class="col-3">
          <input class="form-control form-control-lg" placeholder="작성자" />
          <input class="form-control form-control-lg" placeholder="비밀번호" />
        </div>
        <div class="col-7">
          <textarea class="form-control" rows="3" placeholder="댓글을 입력하세요"></textarea>
        </div>
        <div class="col-2">
          <button class="btn btn-lg btn-block btn-primary">등록</button>
          <button class="btn btn-lg btn-block btn-success">등록+추천</button>
        </div>
      </form>
    </div>
  </main>
  <footer>
  </footer>
</body>

</html>