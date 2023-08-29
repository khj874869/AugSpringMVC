package com.aug.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.aug.spring.board.domain.Reply;

public interface replyStore {

	int insertReply(Reply reply, SqlSession session);

	List<Reply> selectListreply(SqlSession session, Integer boardNo);

	int updateReply(SqlSession session, Reply reply);

	int deleteReply(SqlSession session, Reply reply);



}
