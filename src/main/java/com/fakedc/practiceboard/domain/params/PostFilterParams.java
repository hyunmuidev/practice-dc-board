package com.fakedc.practiceboard.domain.params;

import com.fakedc.practiceboard.domain.enums.BoardFilterType;
import com.fakedc.practiceboard.domain.enums.PostType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostFilterParams {

	private String boardId;
	private BoardFilterType filterType;
	private String keyword;
	private PostType postType;
	private int currentPageIndex;
	private int pageSize;
	
	public int getOffset() {
		return currentPageIndex * pageSize;
	}
	
}
