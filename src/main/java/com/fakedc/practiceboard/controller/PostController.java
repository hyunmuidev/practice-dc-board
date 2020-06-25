package com.fakedc.practiceboard.controller;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fakedc.practiceboard.domain.Post;
import com.fakedc.practiceboard.domain.Reply;
import com.fakedc.practiceboard.service.PostService;
import com.fakedc.practiceboard.service.ReplyService;

@Controller
@RequestMapping(value = "/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private ReplyService replyService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable long id) {
		postService.raiseViewCount(id);
		
		Post post = postService.getPost(id);
		Collection<Reply> replies = replyService.getReplies(id);
		return getModelAndView("post/detail", post, replies);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		Post post = new Post();
		post.setBoardId("ktrolster"); // TODO: 임시로 작동시키기 위해 DB에 존재하는 BoardId 를 강제로 세팅함. 차후 삭제 요망
		return getModelAndView("post/update", post);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable long id) {
		Post post = postService.getPost(id);
		return getModelAndView("post/update", post);
	}

	private ModelAndView getModelAndView(@NotNull String viewName, @NotNull Post post) {
		return getModelAndView(viewName, post, null);
	}

	private ModelAndView getModelAndView(@NotNull String viewName, @NotNull Post post, Collection<Reply> replies) {
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("post", post);
		if (replies != null) {
			mv.addObject("replies", replies);
		}
		return mv;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePost(Post updatePost) {
		// 댓글 추가 / 수정을 id 에 값이 있는지 없는지로 판단
		Post post = updatePost.getId() > 0 ? postService.updatePost(updatePost) : postService.addPost(updatePost);
		return "redirect:/post/" + post.getId();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePost(long id) {
		postService.deletePost(id);
		return "redirect:/"; // go to home
	}
	
	@RequestMapping(value = "/recommend", method = RequestMethod.POST)
	public String recommend(long id) {
		postService.recommendPost(id);
		return "redirect:/post/" + id;
	}
	
	@RequestMapping(value = "/unrecommend", method = RequestMethod.POST)
	public String unrecommend(long id) {
		postService.unrecommendPost(id);
		return "redirect:/post/" + id;
	}
}
