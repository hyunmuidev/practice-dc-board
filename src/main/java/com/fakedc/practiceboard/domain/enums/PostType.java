package com.fakedc.practiceboard.domain.enums;

public enum PostType {
	NORMAL("일반"),
	NOTICE("공지");
	
	private String name;
	
	private PostType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
