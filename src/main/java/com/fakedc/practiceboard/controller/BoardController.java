package com.fakedc.practiceboard.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fakedc.practiceboard.domain.GlobalVariables;
import com.fakedc.practiceboard.domain.Post;
import com.fakedc.practiceboard.domain.enums.BoardFilterType;
import com.fakedc.practiceboard.domain.enums.PostType;
import com.fakedc.practiceboard.domain.viewmodel.SearchBoardFilter;
import com.fakedc.practiceboard.service.PostService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{boardId}")
	public ModelAndView getPosts(@PathVariable String boardId,
			@RequestParam(required = false, defaultValue = GlobalVariables.DEFAULT_BOARD_FILTER_TYPE) BoardFilterType filterType,
			@RequestParam(required = false, defaultValue = GlobalVariables.DEFAULT_KEYWORD) String keyword,
			@RequestParam(required = false, defaultValue = GlobalVariables.DEFAULT_POST_TYPE) PostType postType,
			Pageable page) {

		final int noticeLimit = 5;
		
		ModelAndView mv = new ModelAndView("board/posts");
		List<Post> notices = postType.equals(PostType.ALL) ? postService.getNotices(boardId, noticeLimit)
				: Collections.emptyList();
		Page<Post> posts = postService.getPosts(boardId, filterType, keyword,
				postType.equals(PostType.NOTICE) ? PostType.NOTICE : PostType.NORMAL, page);

		mv.addObject("boardId", boardId);
		mv.addObject("notices", notices);
		mv.addObject("posts", posts);
		mv.addObject("searchBoardFilter", new SearchBoardFilter(filterType, keyword, postType));
		return mv;
	}

}
