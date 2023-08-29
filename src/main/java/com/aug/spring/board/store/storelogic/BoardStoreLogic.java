package com.aug.spring.board.store.storelogic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.aug.spring.board.domain.BoardDomain;
import com.aug.spring.board.domain.PageInfo;
import com.aug.spring.board.store.BoardStore;

@Repository
public class BoardStoreLogic implements BoardStore{

	@Override
	public int writeBoard(SqlSession session, BoardDomain board) {
		int result = session.insert("BoardMapper.insertBoard",board);
		return result;
	}

	@Override
	public int selectListCount(SqlSession session) {
		int result = session.selectOne("BoardMapper.selectListCount");
		return result;
	}

	@Override
	public List<BoardDomain> selectBoardList(SqlSession session, PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds =new RowBounds(offset, limit);
		List<BoardDomain> bDomain = session.selectList("BoardMapper.selectBoardList", null, rowBounds);
		return bDomain;
	}

	@Override
	public BoardDomain selectOneBoard(SqlSession session, Integer boardNo) {
		BoardDomain board = session.selectOne("BoardMapper.selectOnebyNo", boardNo);
		return board;
	}

	@Override
	public int deleteBoard(SqlSession session, BoardDomain board) {
		int result = session.update("BoardMapper.deleteBoard",board);
		return result;
	}

	@Override
	public int updateBoard(SqlSession session, BoardDomain board) {
		int result = session.update("BoardMapper.updateBoard",board);
		return result;
	}
	
}
