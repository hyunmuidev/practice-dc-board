package com.fakedc.practiceboard.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fakedc.practiceboard.domain.Reply;
import com.fakedc.practiceboard.repository.ReplyRepository;

@Controller
@RequestMapping(value = "/reply")
public class ReplyController {
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable long id) {
		Reply reply = replyRepository.findById(id).orElseThrow(IllegalArgumentException::new);		
		return getModelAndView("reply/detail", reply);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		Reply reply = new Reply();
		reply.setPostId(1);		// TODO: 임시로 작동시키기 위해 DB에 존재하는 PostId 를 강제로 세팅함. 차후 삭제 요망
		return getModelAndView("reply/update", reply);
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable long id) {
		Reply reply = replyRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		return getModelAndView("reply/update", reply);
	}
	
	private ModelAndView getModelAndView(@NotNull String viewName, Reply reply) {
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("reply", reply);
		return mv;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateReply(Reply updateReply) {
		Reply reply;
		
		// 댓글 추가 / 수정을 id 에 값이 있는지 없는지로 판단 
		if (updateReply.getId() > 0) {
			reply = replyRepository.findById(updateReply.getId()).orElseThrow(IllegalArgumentException::new);
		} else {
			reply = updateReply;
		}
		
		reply.readyToSave(updateReply);
		
		replyRepository.save(reply);
		return "redirect:/reply/" + reply.getId();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteReply(long id) {
		replyRepository.deleteById(id);
		return "redirect:/reply/" + id;		// 삭제 후 댓글 상세 페이지로 이동함. 
											// 삭제가 되었으므로 에러가 발생한다
	}
}
