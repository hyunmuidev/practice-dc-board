package com.fakedc.practiceboard.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fakedc.practiceboard.domain.Reply;
import com.fakedc.practiceboard.repository.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	/**
	 * 댓글을 가져온다
	 * 
	 * @param id 댓글 아이디
	 * @return 댓글
	 */
	public Reply getReply(long id) {
		return replyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	/**
	 * 댓글 리스트를 불러온다
	 * 
	 * @param postId 게시글 아이디
	 * @return 댓글 리스트
	 */
	public Collection<Reply> getReplies(long postId) {
		return replyRepository.findByPostId(postId).orElseGet(Collections::emptyList);
	}
	
	/**
	 * 댓글을 등록한다
	 * 
	 * @param newReply 등록할 댓글
	 * @return 등록 완료된 댓글
	 */
	public Reply addReply(Reply newReply) {
		return replyRepository.save(newReply);
	}
	 
	/**
	 * 댓글을 수정한다
	 * 
	 * @param updateReply 수정할 댓글
	 * @return 수정 완료된 댓글
	 */
	public Reply updateReply(Reply updateReply) {
		Reply reply = replyRepository.findById(updateReply.getId()).orElseThrow(IllegalArgumentException::new);
		reply.readyToSave(updateReply);
		return replyRepository.save(reply);
	}
	
	/**
	 * 댓글을 삭제한다
	 * 
	 * @param id 삭제할 댓글 아이디
	 * @return 삭제 성공 여부
	 */
	public boolean deleteReply(long id) {
		replyRepository.deleteById(id);
		return true;
	}
	
}
