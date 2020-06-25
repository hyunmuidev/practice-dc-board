package com.fakedc.practiceboard.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JspViewHelper {

	private JspViewHelper() {
	}

	public static String parseLocalDateTime(LocalDateTime dt, String pattern) {
		return dt.format(DateTimeFormatter.ofPattern(pattern));
	}

}
