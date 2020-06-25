package com.fakedc.practiceboard.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fakedc.practiceboard.domain.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
	
	Optional<Collection<Reply>> findByPostId(Long postId);
	
}
