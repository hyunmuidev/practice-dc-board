package com.fakedc.practiceboard.domain;

import java.io.Serializable;

import com.fakedc.practiceboard.domain.enums.PostActionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostHistoryId implements Serializable {

	private static final long serialVersionUID = 5976089876050556569L;
	
	private String jSessionId;
	private long postId;	
	private PostActionType action;
	
}
