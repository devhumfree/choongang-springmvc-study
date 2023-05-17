package com.ja.finalproject.board.mapper;

import java.util.List;

import com.ja.finalproject.dto.BoardDto;

public interface BoardSqlMapper {
	
	public BoardDto selectById(int id);

	public void insert(BoardDto boardDto);
	public List<BoardDto> selectAll();
	
}
