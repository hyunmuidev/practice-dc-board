package com.fakedc.practiceboard.controller;

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
		ModelAndView mv = new ModelAndView("reply/detail");
		Reply reply = replyRepository.findById(id).orElseThrow(IllegalArgumentException::new);		
		mv.addObject("reply", reply);
		return mv;
	}
	
}
