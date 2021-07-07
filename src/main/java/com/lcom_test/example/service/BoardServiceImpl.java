package com.lcom_test.example.service;

import java.awt.Image;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcom_test.example.domain.Board;
import com.lcom_test.example.domain.Comment;
import com.lcom_test.example.domain.Pagination;
import com.lcom_test.example.domain.User;
import com.lcom_test.example.domain.Images;
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
		if(board.getbGroup() == 0) {
			boardMapper.insertBoard(board);
			boardMapper.updateGroup(board);
		}
		if(board.getbGroup() != 0) {
			boardMapper.insertReply(board);
			boardMapper.updateOrder(board);
		}
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

	@Override
	public void insertEdit(Board board) {
		boardMapper.insertEdit(board);
		
	}

	@Override
	public Board getlist(int bId) {
		return boardMapper.getlist(bId);
	}

	@Override
	public void insertComment(Comment comment) {
		if(comment.getcGroup() == 0) {
			boardMapper.insertComment(comment);
			boardMapper.updateCommentGroup(comment);
		}
		
		if(comment.getcGroup() != 0) {
			boardMapper.insertCommentReply(comment);
			boardMapper.updateCommentOrder(comment);
		}
		
	}

	@Override
	public int getCommentCount() {
		return boardMapper.getCommentCount();
	}

	@Override
	public List<Comment> selectCommentList(Comment comment) {
		return boardMapper.selectCommentList(comment);
	}

	@Override
	public void deleteComment(Comment comment) {
		boardMapper.deleteComment(comment);
		
	}

	@Override
	public void updateComment(Comment comment) {
		boardMapper.updateComment(comment);
		
	}

	@Override
	public void updateCommentShow(Comment comment) {
		boardMapper.updateCommentShow(comment);		
	}

	@Override
	public void insertCommentReply(Comment comment) {
		boardMapper.insertCommentReply(comment);
		
	}

	@Override
	public void insertImage(Images images) {
		boardMapper.insertImage(images);
	}

	@Override
	public List<Images> selectImagesList() {
		return boardMapper.selectImagesList();
	}
}
