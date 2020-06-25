package com.fakedc.practiceboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fakedc.practiceboard.domain.Post;
import com.fakedc.practiceboard.domain.enums.BoardFilterType;
import com.fakedc.practiceboard.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	/**
	 * 게시글을 가져온다
	 * 
	 * @param id 게시글 아이디
	 * @return 게시글
	 */
	public Post getPost(long id) {
		return postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	//
	/**
	 * 게시글을 추가한다
	 * 
	 * @param newPost 새 게시글
	 * @return 등록 완료된 게시글
	 */
	public Post addPost(Post newPost) {
		return postRepository.save(newPost);
	}

	/**
	 * 게시글을 수정한다
	 * 
	 * @param updatePost 수정된 게시글
	 * @return 수정 완료된 게시글
	 */
	public Post updatePost(Post updatePost) {
		Post post = postRepository.findById(updatePost.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		post.readyToSave(updatePost);
		return postRepository.save(post);
	}

	/**
	 * 게시글을 삭제한다
	 * 
	 * @param id 게시글 아이디
	 * @return 삭제 성공 여부
	 */
	public boolean deletePost(long id) {
		postRepository.deleteById(id);
		return true;
	}

	/**
	 * 게시글을 추천한다
	 * 
	 * @param id 게시글 아이디
	 * @return 업데이트된 추천 갯수
	 */
	public int recommendPost(long id) {
		Post post = getPost(id);
		post.setRecommendCount(post.getRecommendCount() + 1);
		postRepository.save(post);
		return post.getRecommendCount();
	}

	/**
	 * 게시글을 비추천한다
	 * 
	 * @param id 게시글 아이디
	 * @return 업데이트된 비추천 갯수
	 */
	public int unrecommendPost(long id) {
		Post post = getPost(id);
		post.setUnrecommendCount(post.getUnrecommendCount() + 1);
		postRepository.save(post);
		return post.getUnrecommendCount();
	}

	/**
	 * 특정 게시판의 게시글들을 가져온다
	 * 
	 * @param boardId 게시판 아이디
	 * @param filterType 검색 타입
	 * @param keyword 검색 키워드
	 * @param page 페이지 정보
	 * @return
	 */
	public Page<Post> getPosts(String boardId, BoardFilterType filterType, String keyword, Pageable page) {
		
		// 제목+내용 아니면 가장 간단한 방법, Query of Example 사용
//		ExampleMatcher postExampleMatcher = ExampleMatcher.matching()
//				.withIgnorePaths("id", "password", "viewCount", "recommendCount", "unrecommendCount", "createdDateTime",
//						"updatedDateTime")
//				.withMatcher("createdBy", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
//				.withMatcher("content", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//				.withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//				.withMatcher("boardId", ExampleMatcher.GenericPropertyMatchers.exact());
//
//		Example<Post> example = Example.of(Post.from(boardId, filterType, keyword), postExampleMatcher);
//
//		return postRepository.findAll(example, PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(Direction.DESC, "id")));
		
		Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(Direction.DESC, "id"));
		
		if (filterType.equals(BoardFilterType.TITLE)) {
			return postRepository.findByBoardIdAndTitleContaining(boardId, keyword, pageable);
		}

		if (filterType.equals(BoardFilterType.CONTENT)) {
			return postRepository.findByBoardIdAndContentContaining(boardId, keyword, pageable);
		}
		
		if (filterType.equals(BoardFilterType.TITLE_CONTENT)) {
			return postRepository.findByBoardIdAndTitleContainingOrContentContaining(boardId, keyword, keyword, pageable);
		}

		if (filterType.equals(BoardFilterType.WRITER)) {
			return postRepository.findByBoardIdAndCreatedBy(boardId, keyword, pageable);
		}
		
		return postRepository.findByBoardIdAndTitleContainingOrContentContainingOrCreatedBy(boardId, keyword, keyword, keyword, pageable);
	}

	/**
	 * 게시글의 조회수를 증가시킨다
	 * 
	 * @param id 게시글 아이디
	 */
	public void raiseViewCount(long id) {
		Post post = getPost(id);
		post.setViewCount(post.getViewCount() + 1);
		postRepository.save(post);
	}
}
