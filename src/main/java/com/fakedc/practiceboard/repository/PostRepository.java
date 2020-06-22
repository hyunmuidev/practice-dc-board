package com.fakedc.practiceboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fakedc.practiceboard.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
