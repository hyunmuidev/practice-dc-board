package com.fakedc.practiceboard.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fakedc.practiceboard.domain.GlobalVariables;
import com.fakedc.practiceboard.domain.Post;
import com.fakedc.practiceboard.domain.enums.BoardFilterType;
import com.fakedc.practiceboard.domain.viewmodel.SearchBoardFilter;
import com.fakedc.practiceboard.service.BoardService;
import com.fakedc.practiceboard.service.PostService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	public static final String DEFAULT_PAGE_SIZE = "30";
	
	@Autowired
	private BoardService boardService;

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{boardId}")
	public ModelAndView getPosts(@PathVariable String boardId,
			@RequestParam(required = false, defaultValue = GlobalVariables.DEFAULT_BOARD_FILTER_TYPE) BoardFilterType filterType, 
			@RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(required = false, defaultValue = GlobalVariables.DEFAULT_PAGE_INDEX) int pageIndex,
			@RequestParam(required = false, defaultValue = GlobalVariables.DEFAULT_PAGE_SIZE) int pageSize) {
		
		SearchBoardFilter filter = new SearchBoardFilter(boardId, filterType, keyword, pageIndex, pageSize);
		ModelAndView mv = new ModelAndView("board/posts");
		int allPostCount = postService.getAllPostCount(filter);
		Collection<Post> posts = postService.getPosts(filter);
		
		filter.setAllCount(allPostCount);
		mv.addObject("boardId", boardId);
		mv.addObject("posts", posts);
		mv.addObject("searchBoardFilter", filter);
		return mv;
	}

}
