package com.fakedc.practiceboard.controller;

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
import com.fakedc.practiceboard.domain.viewmodel.SearchBoardFilter;
import com.fakedc.practiceboard.service.PostService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	public static final String DEFAULT_PAGE_SIZE = "30";
	
//	@Autowired
//	private BoardService boardService;

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{boardId}")
	public ModelAndView getPosts(@PathVariable String boardId,
			@RequestParam(required = false, defaultValue = GlobalVariables.DEFAULT_BOARD_FILTER_TYPE) BoardFilterType filterType, 
			@RequestParam(required = false, defaultValue = "") String keyword,
			Pageable page) {
		
		SearchBoardFilter filter = new SearchBoardFilter(filterType, keyword);
		ModelAndView mv = new ModelAndView("board/posts");
		Page<Post> posts = postService.getPosts(boardId, filterType, keyword, page);
		
		mv.addObject("boardId", boardId);
		mv.addObject("posts", posts);
		mv.addObject("searchBoardFilter", filter);
		return mv;
	}

}
