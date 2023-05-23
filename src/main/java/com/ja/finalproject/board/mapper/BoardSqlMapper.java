package com.ja.finalproject.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ja.finalproject.dto.BoardDto;
import com.ja.finalproject.dto.BoardImageDto;

public interface BoardSqlMapper {
	
	public int createPk(); //동시에 다수 쿼리적용위해
	
	
	public BoardDto selectById(int id);
	
	public void insert(BoardDto boardDto);
	public List<BoardDto> selectAll(@Param("pageNum") int pageNum,@Param("searchType") String searchType,@Param("searchWord") String searchWord);
//	public List<BoardDto> selectAll();
	public int getBoardCount(@Param("searchType") String searchType,@Param("searchWord") String searchWord);
	
	// 조회수 증가
	public void increaseReadCount(int id);
	
	// 글 삭제
	public void deleteById(int id);
	
	// 글 수정
	public void update(BoardDto boardDto);
	
	// 이미지 등록
	public void insertBoardImage(BoardImageDto boardImageDto);
	
	// 이미지 불러오기
	public List<BoardImageDto> selectBoardImageByBoardId(int boardId);
	
	
	
}
