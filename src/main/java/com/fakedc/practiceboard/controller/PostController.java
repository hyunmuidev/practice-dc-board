package com.fakedc.practiceboard.controller;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.fakedc.practiceboard.domain.GlobalVariables;
import com.fakedc.practiceboard.domain.Post;
import com.fakedc.practiceboard.domain.Reply;
import com.fakedc.practiceboard.domain.enums.BoardFilterType;
import com.fakedc.practiceboard.domain.enums.PostActionType;
import com.fakedc.practiceboard.domain.enums.PostType;
import com.fakedc.practiceboard.domain.viewmodel.SearchBoardFilter;
import com.fakedc.practiceboard.service.PostService;
import com.fakedc.practiceboard.service.ReplyService;

@Controller
@RequestMapping(value = "/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private ReplyService replyService;

	private String getJSessionId() {
		return RequestContextHolder.currentRequestAttributes().getSessionId();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable long id,
			@RequestParam(required = false, defaultValue = GlobalVariables.DEFAULT_BOARD_FILTER_TYPE) BoardFilterType filterType,
			@RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(required = false, defaultValue = GlobalVariables.DEFAULT_POST_TYPE) PostType postType,
			Pageable page) {
		postService.raiseViewCount(id, getJSessionId());
		
		Post post = postService.getPost(id);
		Collection<Reply> replies = replyService.getReplies(id);
		ModelAndView mv = getModelAndView("post/detail", post, replies);
		
		mv.addObject("searchBoardFilter", new SearchBoardFilter(filterType, keyword, postType));
		mv.addObject("pageable", page);
		
		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		Post post = new Post();
		post.setBoardId("ktrolster"); // TODO: 임시로 작동시키기 위해 DB에 존재하는 BoardId 를 강제로 세팅함. 차후 삭제 요망
		return getModelAndView("post/update", post);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable long id) {
		
		if (postService.checkPostHistory(getJSessionId(), id, PostActionType.UPDATE)) {
			return new ModelAndView("redirect:/post/check-password/" + id + "?action=UPDATE");
		}
		
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
		
		if (postService.checkPostHistory(getJSessionId(), id, PostActionType.DELETE)) {
			return "redirect:/post/check-password/" + id + "?action=DELETE";
		}
		
		postService.deletePost(id);
		return "redirect:/"; // go to home
	}
//	
	@RequestMapping(value = "/recommend", method = RequestMethod.POST)
	public String recommend(long id) {
		postService.recommendPost(id, getJSessionId());
		return "redirect:/post/" + id;
	}
	
	@RequestMapping(value = "/unrecommend", method = RequestMethod.POST)
	public String unrecommend(long id) {
		postService.unrecommendPost(id, getJSessionId());
		return "redirect:/post/" + id;
	}
	
	@GetMapping(value = "/check-password/{id}")
	public ModelAndView checkPassoword(@PathVariable long id, @RequestParam PostActionType action) {
		if (!action.equals(PostActionType.UPDATE) && !action.equals(PostActionType.DELETE)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		ModelAndView mv = new ModelAndView();
		
		if (action.equals(PostActionType.UPDATE) && !postService.checkPostHistory(getJSessionId(), id, PostActionType.UPDATE)) {
			mv.setViewName("redirect:/post/update/" + id);
			return mv;
		} else if (action.equals(PostActionType.DELETE) && !postService.checkPostHistory(getJSessionId(), id, PostActionType.DELETE)) {
			mv.setViewName(deletePost(id));
			return mv;
		}
		
		mv.setViewName("post/check-password");
		mv.addObject("postId", id);
		return mv;
	}
	
	@PostMapping(value = "/check-password/{id}")
	public ModelAndView checkPasswordPost(@PathVariable long id, @RequestParam PostActionType action, String password) {
		if (!action.equals(PostActionType.UPDATE) && !action.equals(PostActionType.DELETE)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		boolean result = postService.checkPassword(id, password);
		ModelAndView mv = new ModelAndView();
		
		if (result) {
			postService.savePostHistory(getJSessionId(), id, action);
			if (action.equals(PostActionType.UPDATE)) {
				mv.setViewName("redirect:/post/update/" + id);
			} else if (action.equals(PostActionType.DELETE)) {
				mv.setViewName(deletePost(id));
			}
		} else {
			mv.setViewName("post/check-password");
			mv.addObject("errorMessage", "비밀번호가 맞지 않습니다.");
		}
		
		return mv;
	}
	
}
