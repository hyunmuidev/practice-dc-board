package com.fakedc.practiceboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fakedc.practiceboard.domain.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
