package com.fakedc.practiceboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fakedc.practiceboard.domain.Post;
import com.fakedc.practiceboard.domain.enums.PostType;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	Page<Post> findByBoardIdAndPostType(String boardId, PostType notice, Pageable pageable);
	
}
