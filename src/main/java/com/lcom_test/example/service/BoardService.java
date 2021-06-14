package com.lcom_test.example.service;

import java.util.List;
import com.lcom_test.example.domain.Board;

public interface BoardService {
	public List<Board> selectBoardList();

	public void insertBoard(Board board);

	public void deleteBoard(Board board);
}
