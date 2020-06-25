package com.fakedc.practiceboard.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fakedc.practiceboard.domain.Post;
import com.fakedc.practiceboard.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	/**
	 * 게시글을 가져온다
	 * 
	 * @param 	id 게시글 아이디
	 * @return 	게시글
	 */
	public Post getPost(long id) {
		return postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	// 
	/**
	 * 게시글을 추가한다
	 * 
	 * @param newPost	새 게시글
	 * @return			등록 완료된 게시글
	 */
	public Post addPost(Post newPost) {
		return postRepository.save(newPost);
	}
	
	/**
	 * 게시글을 수정한다
	 * 
	 * @param updatePost	수정된 게시글
	 * @return				수정 완료된 게시글
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
	 * @param id	게시글 아이디
	 * @return		삭제 성공 여부
	 */
	public boolean deletePost(long id) {
		postRepository.deleteById(id);
		return true;
	}
	
	/**
	 * 게시글을 추천한다
	 * 
	 * @param id	게시글 아이디
	 * @return		업데이트된 추천 갯수
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
	 * @param id	게시글 아이디
	 * @return		업데이트된 비추천 갯수
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
	 * @return 특정 게시판의 게시글
	 */
	public Collection<Post> getPosts(String boardId) {
		// TODO: 상세 로직 검토 필요
		/**
		 * 게시판을 새로 만들고, 게시글이 한개도 없으면 접근자체를 못할 수 있음. 그러므로
		 * 1. 게시판을 만들 때 더미 공지글 or 일반글을 하나 만든다
		 * 2. 글이 한개도 없는 경우를 막기 위해 글이 1개만 남았을 때는 삭제가 되면 안된다.
		 */
		return postRepository.findByBoardId(boardId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
