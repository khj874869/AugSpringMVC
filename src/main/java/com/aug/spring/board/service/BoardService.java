package com.aug.spring.board.service;

import java.util.List;

import com.aug.spring.board.domain.BoardDomain;
import com.aug.spring.board.domain.PageInfo;

public interface BoardService {

	int writeBoard(BoardDomain board);

	int getListCount();

	List<BoardDomain> selectBoardList(PageInfo pInfo);

	BoardDomain selectBoardOne(Integer boardNo);

}
