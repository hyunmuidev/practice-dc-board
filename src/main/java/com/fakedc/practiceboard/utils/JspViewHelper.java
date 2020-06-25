package com.fakedc.practiceboard.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Pageable;

public class JspViewHelper {

	private JspViewHelper() {
	}

	public static String parseLocalDateTime(LocalDateTime dt, String pattern) {
		return dt.format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static String getUrlPageableParams(Pageable pageable) {
		return getUrlPageableParams(pageable.getPageNumber() + 1, pageable.getPageSize());
	}
	
	public static String getUrlPageableParams(int currentPageIndex, int pageSize) {
		return String.format("currentPageIndex=%d&pagingSize=%d", currentPageIndex, pageSize);
	}

}
