package com.lcom_test.example.service;

import java.awt.Image;
import java.util.List;
import com.lcom_test.example.domain.Board;
import com.lcom_test.example.domain.Comment;
import com.lcom_test.example.domain.Pagination;
import com.lcom_test.example.domain.Product;
import com.lcom_test.example.domain.Images;

public interface BoardService {
	public List<Board> selectBoardList(Pagination pagination);

	public void insertBoard(Board board);

	public void deleteBoard(Board board);

	public int getBoardCount();

	public Board getBoard(int bId);

	public void insertEdit(Board board);

	public Board getlist(int bId);

	public void insertComment(Comment comment);

	public int getCommentCount();

	public List<Comment> selectCommentList(Comment comment);

	public void deleteComment(Comment comment);

	public void updateComment(Comment comment);

	public void updateCommentShow(Comment comment);

	public void insertCommentReply(Comment comment);

	public void insertImage(Images images);

	public List<Images> selectImagesList();

	public void updatepId(Images images);

}