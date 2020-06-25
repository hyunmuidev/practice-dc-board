package com.fakedc.practiceboard.domain.viewmodel;

import com.fakedc.practiceboard.domain.enums.BoardFilterType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBoardFilter {
	
	private BoardFilterType filterType;
	
	private String keyword;
	
	public SearchBoardFilter(BoardFilterType filterType, String keyword) {
		this.filterType = filterType;
		this.keyword = keyword;
	}

	public BoardFilterType[] getAllBoardFilterTypes() {
		return BoardFilterType.values();
	}
	
	public String getUrlParams() {
		return "filterType=" + filterType.toString() + "&keyword=" + keyword;
	}
	
	public static int[] TYPEOF_PAGE_SIZE = {3, 10, 30, 50};
	
}
