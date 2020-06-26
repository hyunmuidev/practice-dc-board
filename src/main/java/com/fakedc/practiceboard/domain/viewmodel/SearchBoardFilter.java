package com.fakedc.practiceboard.domain.viewmodel;

import com.fakedc.practiceboard.domain.enums.BoardFilterType;
import com.fakedc.practiceboard.domain.enums.PostType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBoardFilter {

	private BoardFilterType filterType;

	private String keyword;

	private PostType postType;

	public SearchBoardFilter(BoardFilterType filterType, String keyword, PostType postType) {
		this.filterType = filterType;
		this.keyword = keyword;
		this.postType = postType;
	}

	public BoardFilterType[] getAllBoardFilterTypes() {
		return BoardFilterType.values();
	}

	public String getUrlParams() {
		return "filterType=" + filterType.toString() + "&=" + keyword + "&postType=" + postType;
	}

}
