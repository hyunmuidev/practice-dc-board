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
		// TODO: 추천 미구현
		return 0;
	}
	
	/**
	 * 게시글을 비추천한다
	 * 
	 * @param id	게시글 아이디
	 * @return		업데이트된 비추천 갯수
	 */
	public int unrecommendPost(long id) {
		// TODO: 비추천 미구현
		return 0;
	}

	/**
	 * 특정 게시판의 게시글들을 가져온다
	 * 
	 * @param boardId 게시판 아이디
	 * @return 특정 게시판의 게시글
	 */
	public Collection<Post> getPosts(String boardId) {
		return postRepository.findByBoardId(boardId);
	}
	
}
