package com.lcom_test.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcom_test.example.domain.Board;
import com.lcom_test.example.domain.User;
import com.lcom_test.example.mapper.BoardMapper;
import com.lcom_test.example.service.BoardService;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<Board> selectBoardList() {
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(Board board) {
		boardMapper.insertBoard(board);
	}
}
