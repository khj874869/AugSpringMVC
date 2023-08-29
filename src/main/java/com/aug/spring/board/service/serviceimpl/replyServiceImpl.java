package com.aug.spring.board.service.serviceimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aug.spring.board.domain.Reply;
import com.aug.spring.board.service.ReplyService;
import com.aug.spring.board.store.replyStore;

@Service
public class replyServiceImpl implements ReplyService {
	@Autowired
	private replyStore rStore;
	@Autowired
	private SqlSession session;
	@Override
	public int insertReply(Reply reply) {
		int result = rStore.insertReply(reply,session);
		return result;
	}
	@Override
	public List<Reply> selectRelfList(Integer boardNo) {
		List<Reply> rList = rStore.selectListreply(session,boardNo);
		return rList;
	}
	@Override
	public int updateReply(Reply reply) {
		int result = rStore.updateReply(session,reply);
		return result;
	}
	@Override
	public int deleteReply(Reply reply) {
		int result = rStore.deleteReply(session,reply);
		return result;
	}
}
