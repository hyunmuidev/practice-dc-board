package com.fakedc.practiceboard.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fakedc.practiceboard.domain.Post;
import com.fakedc.practiceboard.service.BoardService;
import com.fakedc.practiceboard.service.PostService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "/{boardId}")
	public ModelAndView getPosts(@PathVariable String boardId) {
		ModelAndView mv = new ModelAndView("board/posts");
		Collection<Post> posts = postService.getPosts(boardId);
		mv.addObject("posts", posts);
		return mv;
	}
	
}
