package com.fakedc.practiceboard.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.fakedc.practiceboard.domain.Reply;
import com.fakedc.practiceboard.service.PostService;
import com.fakedc.practiceboard.service.ReplyService;

@Controller
@RequestMapping(value = "/reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable long id) {
		Reply reply = replyService.getReply(id);
		return getModelAndView("reply/detail", reply);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		Reply reply = new Reply();
		reply.setPostId(1); // TODO: 임시로 작동시키기 위해 DB에 존재하는 PostId 를 강제로 세팅함. 차후 삭제 요망
		return getModelAndView("reply/update", reply);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable long id) {
		Reply reply = replyService.getReply(id);
		return getModelAndView("reply/update", reply);
	}

	private ModelAndView getModelAndView(@NotNull String viewName, Reply reply) {
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("reply", reply);
		return mv;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateReply(Reply updateReply) {
		Reply reply = updateReply.getId() > 0 ? replyService.updateReply(updateReply)
				: replyService.addReply(updateReply);
		return "redirect:/reply/" + reply.getId();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteReply(long id) {
		replyService.deleteReply(id);
		return "redirect:/";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addReply(Reply addReply,
			@RequestParam(required = false, defaultValue = "false") boolean withRecommend) {
		Reply addedReply = replyService.addReply(addReply);

		if (withRecommend) {
			postService.recommendPost(addedReply.getPostId(), RequestContextHolder.currentRequestAttributes().getSessionId());
		}

		return "redirect:/post/" + addedReply.getPostId();
	}
}
