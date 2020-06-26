package com.fakedc.practiceboard.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.fakedc.practiceboard.domain.enums.PostActionType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post_history")
@IdClass(PostHistoryId.class)
@Getter
@Setter
@DynamicInsert
@NoArgsConstructor
public class PostHistory {
	
	@Id
	@Column(name = "jsession_id")
	private String jSessionId;
	
	@Id
	@Column
	private long postId;
	
	@Id
	@Column
	@Enumerated(EnumType.STRING)
	private PostActionType action;
	
	@Column(name = "created_datetime")
	private LocalDateTime createdDateTime;

	public PostHistory(String jSessionId, long postId, PostActionType action) {
		super();
		this.jSessionId = jSessionId;
		this.postId = postId;
		this.action = action;
	}
	
}
