package com.fakedc.practiceboard.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.fakedc.practiceboard.domain.Post;
import com.fakedc.practiceboard.repository.PostRepository;

@Controller
@RequestMapping(value = "/post")
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));		
		return getModelAndView("post/detail", post);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		Post post = new Post();
		post.setBoardId("ktrolster");	// TODO: 임시로 작동시키기 위해 DB에 존재하는 BoardId 를 강제로 세팅함. 차후 삭제 요망
		return getModelAndView("post/update", post);
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return getModelAndView("post/update", post);
	}
	
	private ModelAndView getModelAndView(@NotNull String viewName, Post post) {
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("post", post);
		return mv;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePost(Post updatePost) {
		Post post;
		
		// 댓글 추가 / 수정을 id 에 값이 있는지 없는지로 판단 
		if (updatePost.getId() > 0) {
			post = postRepository.findById(updatePost.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		} else {
			post = updatePost;
		}
		
		post.readyToSave(updatePost);
		
		postRepository.save(post);
		return "redirect:/post/" + post.getId();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePost(long id) {
		postRepository.deleteById(id);
		return "redirect:/post/" + id;		// 삭제 후 댓글 상세 페이지로 이동함. 
											// 삭제가 되었으므로 에러가 발생한다
	}
}
