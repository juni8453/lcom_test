package com.lcom_test.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcom_test.example.domain.Board;
import com.lcom_test.example.domain.Pagination;
import com.lcom_test.example.domain.User;
import com.lcom_test.example.mapper.BoardMapper;
import com.lcom_test.example.service.BoardService;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<Board> selectBoardList(Pagination pagination) {
		return boardMapper.selectBoardList(pagination);
	}

	@Override
	public void insertBoard(Board board) {
		boardMapper.insertBoard(board);
	}

	@Override
	public void deleteBoard(Board board) {
		boardMapper.deleteBoard(board);
		
	}

	@Override
	public int getBoardCount() {
		return boardMapper.getBoardCount();
	}

	@Override
	public Board getBoard(int bId) {
		boardMapper.getBoardViews(bId);
		return boardMapper.getBoard(bId);
	}
}
