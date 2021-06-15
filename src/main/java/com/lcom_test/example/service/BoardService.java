package com.lcom_test.example.service;

import java.util.List;
import com.lcom_test.example.domain.Board;
import com.lcom_test.example.domain.Pagination;

public interface BoardService {
	public List<Board> selectBoardList(Pagination pagination);

	public void insertBoard(Board board);

	public void deleteBoard(Board board);

	public int getBoardCount();
}
