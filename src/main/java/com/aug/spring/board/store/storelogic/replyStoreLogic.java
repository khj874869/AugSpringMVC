package com.aug.spring.board.store.storelogic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aug.spring.board.domain.Reply;
import com.aug.spring.board.store.replyStore;

@Repository
public class replyStoreLogic implements replyStore{

	@Override
	public int insertReply(Reply reply, SqlSession session) {
		int result = session.insert("ReplyMapper.insertReply", reply);
		return result;
	}

	@Override
	public List<Reply> selectListreply(SqlSession session, Integer boardNo) {
		List<Reply> rList = session.selectList("ReplyMapper.selectOneList",boardNo);
		return rList;
	}

	@Override
	public int updateReply(SqlSession session, Reply reply) {
		int result = session.update("ReplyMapper.updateReply", reply);
		return result;
	}


}
