use fake_dc;

-- post 테이블에 열 삽입
insert into post (board_id,title,content,password,post_type,created_by) 
values ('ktrolster', '하나 둘 셋! 케이티 화이팅~!', '2020 KT 롤드컵 우승', '1234', 'NORMAL', 'DC_ADMIN');

-- 결과 확인
select * from post p ;



-- reply 테이블에 열 삽입
insert into reply (post_id,created_by,password,content)
values (1, 'DC_ADMIN', '1234', '에이밍 절대 종신해!');

-- 결과 확인
select * from reply r ;