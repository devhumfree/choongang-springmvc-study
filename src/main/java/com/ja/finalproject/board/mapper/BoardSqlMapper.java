package com.ja.finalproject.board.mapper;

import java.util.List;

import com.ja.finalproject.dto.BoardDto;

public interface BoardSqlMapper {
	
	public BoardDto selectById(int id);

	public void insert(BoardDto boardDto);
	public List<BoardDto> selectAll(int pageNum);
//	public List<BoardDto> selectAll();
	public int getBoardCount();
	
	// 조회수 증가
	public void increaseReadCount(int id);
	
	// 글 삭제
	public void deleteById(int id);
	
	// 글 수정
	public void update(BoardDto boardDto);
	
}
