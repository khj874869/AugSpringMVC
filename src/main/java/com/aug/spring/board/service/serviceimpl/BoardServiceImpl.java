package com.aug.spring.board.service.serviceimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aug.spring.board.domain.BoardDomain;
import com.aug.spring.board.domain.PageInfo;
import com.aug.spring.board.service.BoardService;
import com.aug.spring.board.store.BoardStore;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardStore store;
	@Autowired
	private SqlSession session;
	
	@Override
	public int writeBoard(BoardDomain board) {
		int result = store.writeBoard(session,board);
		return result;
	}

	@Override
	public int getListCount() {
		int result = store.selectListCount(session);
		return result;
	}

	@Override
	public List<BoardDomain> selectBoardList(PageInfo pInfo) {
		List<BoardDomain> bdomain=store.selectBoardList(session,pInfo);
		return bdomain;
	}

	@Override
	public BoardDomain selectBoardOne(Integer boardNo) {
		BoardDomain board= store.selectOneBoard(session,boardNo);
		return board;
	}

	@Override
	public int deleteBoard(BoardDomain board) {
		int result = store.deleteBoard(session,board);
		return result;
	}

	@Override
	public int updateboard(BoardDomain board) {
		int result = store.updateBoard(session,board);
		return result;
	}

}
