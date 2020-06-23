package com.fakedc.practiceboard.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fakedc.practiceboard.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	public Optional<Collection<Post>> findByBoardId(String boardId);
	
}
