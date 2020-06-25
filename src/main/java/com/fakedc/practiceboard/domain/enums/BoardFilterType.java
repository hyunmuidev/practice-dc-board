package com.fakedc.practiceboard.domain.enums;

public enum BoardFilterType {
	ALL("전체"), TITLE("제목"), CONTENT("내용"), WRITER("글쓴이"), TITLE_CONTENT("제목+내용");

	private String name;

	private BoardFilterType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
