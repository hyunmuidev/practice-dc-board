package com.fakedc.practiceboard.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fakedc.practiceboard.domain.enums.BoardFilterType;
import com.fakedc.practiceboard.domain.enums.PostType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "post")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@ToString
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private String boardId;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private String createdBy;

	@Column
	private String password;

	@Column
	@Enumerated(EnumType.STRING)
	private PostType postType;

	@Column
	private int viewCount;

	@Column
	private int recommendCount;

	@Column
	private int unrecommendCount;

	@Column(name = "created_datetime")
	private LocalDateTime createdDateTime;

	@Column(name = "updated_datetime")
	private LocalDateTime updatedDateTime;

	public void readyToSave(Post updatePost) {
		this.content = updatePost.content;
		this.title = updatePost.title;
		this.password = updatePost.password;
		this.updatedDateTime = LocalDateTime.now();
		this.postType = updatePost.postType;
	}

	public static Post from(String boardId, BoardFilterType filterType, String keyword) {
		Post post = new Post();
		post.boardId = boardId;

		if (filterType.equals(BoardFilterType.TITLE) || filterType.equals(BoardFilterType.TITLE_CONTENT)
				|| filterType.equals(BoardFilterType.ALL)) {
			post.title = keyword;
		}

		if (filterType.equals(BoardFilterType.CONTENT) || filterType.equals(BoardFilterType.TITLE_CONTENT)
				|| filterType.equals(BoardFilterType.ALL)) {
			post.content = keyword;
		}

		if (filterType.equals(BoardFilterType.WRITER) || filterType.equals(BoardFilterType.ALL)) {
			post.createdBy = keyword;
		}

		return post;
	}

}
