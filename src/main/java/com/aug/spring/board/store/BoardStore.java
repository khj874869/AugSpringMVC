package com.aug.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.aug.spring.board.domain.BoardDomain;
import com.aug.spring.board.domain.PageInfo;

public interface BoardStore {
	
	int writeBoard(SqlSession session, BoardDomain board);

	int selectListCount(SqlSession session);

	List<BoardDomain> selectBoardList(SqlSession session, PageInfo pInfo);

	BoardDomain selectOneBoard(SqlSession session, Integer boardNo);

}
