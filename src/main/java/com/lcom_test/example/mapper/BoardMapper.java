package com.lcom_test.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.lcom_test.example.domain.Board;

@Mapper
public interface BoardMapper {
	public List<Board> selectBoardList();

}
