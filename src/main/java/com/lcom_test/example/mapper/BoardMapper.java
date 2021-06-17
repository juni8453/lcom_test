package com.lcom_test.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.lcom_test.example.domain.Board;
import com.lcom_test.example.domain.Pagination;

@Mapper
public interface BoardMapper {
	public List<Board> selectBoardList(Pagination pagination);

	public void insertBoard(Board board);

	public void deleteBoard(Board board);

	public int getBoardCount();

	public Board getBoard(int bId);

	public void getBoardViews(int bId);

	public void insertEdit(Board board);

}
