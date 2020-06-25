package com.fakedc.practiceboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fakedc.practiceboard.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	public Page<Post> findByBoardIdAndTitleContainingOrContentContainingOrCreatedBy(String boardId, String title, String content, String createdBy, Pageable pageable);
	
	public Page<Post> findByBoardIdAndTitleContaining(String boardId, String title, Pageable pageable);
	
	public Page<Post> findByBoardIdAndContentContaining(String boardId, String content, Pageable pageable);
	
	public Page<Post> findByBoardIdAndTitleContainingOrContentContaining(String boardId, String title, String content, Pageable pageable);
	
	public Page<Post> findByBoardIdAndCreatedBy(String boardId, String createdBy, Pageable pageable);
	
}
