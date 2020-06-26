package com.fakedc.practiceboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fakedc.practiceboard.domain.PostHistory;
import com.fakedc.practiceboard.domain.PostHistoryId;

@Repository
public interface PostHistoryRepository extends JpaRepository<PostHistory, PostHistoryId> {

	
	
}
