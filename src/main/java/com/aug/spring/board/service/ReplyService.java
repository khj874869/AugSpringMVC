package com.aug.spring.board.service;

import java.util.List;

import com.aug.spring.board.domain.Reply;

public interface ReplyService {

	int insertReply(Reply reply);

	List<Reply> selectRelfList(Integer boardNo);

	int updateReply(Reply reply);

}
