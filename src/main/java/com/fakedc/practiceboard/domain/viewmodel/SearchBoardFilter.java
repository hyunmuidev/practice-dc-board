package com.fakedc.practiceboard.domain.viewmodel;

import com.fakedc.practiceboard.domain.enums.BoardFilterType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBoardFilter {
	
	private String boardId;
	
	private BoardFilterType filterType;
	
	private String keyword;
	
	private int pageIndex;
	
	private int pageSize;
	
	private int allCount;
	
	public SearchBoardFilter(String boardId, BoardFilterType filterType, String keyword, int pageIndex, int pageSize) {
		this.boardId = boardId;
		this.filterType = filterType;
		this.keyword = keyword;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public BoardFilterType[] getAllBoardFilterTypes() {
		return BoardFilterType.values();
	}
	
	public int getOffset() {
		return (this.pageIndex - 1) * this.pageSize;
	}
	
	public int getLastIndex() {
		return allCount / this.pageSize;
	}
	
	public String getUrlParams() {
		return getUrlParams(this.pageIndex);
	}
	
	public String getUrlParams(int pageIndex) {
		return String.format("%s?filterType=%s&keyword=%s&pageIndex=%d&pageSize=%d", boardId, filterType.toString(), keyword, pageIndex, this.pageSize);
	}
	
	public static int[] TYPEOF_PAGE_SIZE = {3, 10, 30, 50};
	
}
