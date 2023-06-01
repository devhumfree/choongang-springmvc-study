package com.ja.finalproject.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.board.mapper.BoardSqlMapper;
import com.ja.finalproject.dto.BoardDto;
import com.ja.finalproject.dto.BoardImageDto;
import com.ja.finalproject.dto.BoardLikeDto;
import com.ja.finalproject.dto.CommentDto;
import com.ja.finalproject.dto.MemberDto;
import com.ja.finalproject.member.mapper.MemberSqlMapper;

@Service
public class BoardServiceImpl {
	
	@Autowired
	private BoardSqlMapper boardSqlMapper;
	@Autowired
	private MemberSqlMapper memberSqlMapper;

	public void writeContent(BoardDto boardDto, List<BoardImageDto> boardImageDtoList) {
		int boardId = boardSqlMapper.createPk();
		
		boardDto.setId(boardId);
		boardSqlMapper.insert(boardDto);
		
		for(BoardImageDto boardImageDto : boardImageDtoList) {
			boardImageDto.setBoard_id(boardId);
			boardSqlMapper.insertBoardImage(boardImageDto);
		}
		
	}
	
	public List<Map<String,Object>> getBoardList(int pageNum, String searchType, String searchWord) {
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		List<BoardDto> boardDtoList = boardSqlMapper.selectAll(pageNum, searchType, searchWord);
		
		
		for(BoardDto boardDto: boardDtoList) {
			
			Map<String, Object> map = new HashMap<>();
			
			int memberId = boardDto.getMember_id();
			
			MemberDto memberDto = memberSqlMapper.selectById(memberId);
			
			map.put("memberDto", memberDto);
			map.put("boardDto", boardDto);
			
			list.add(map);
		}
		return list;
	}
	
	public int getBoardCount(String searchType, String searchWord) {
		return boardSqlMapper.getBoardCount(searchType, searchWord);
	}
	
	
	public Map<String, Object> getBoard(int id) {
		
		Map<String, Object> map = new HashMap<>();
		
		BoardDto boardDto = boardSqlMapper.selectById(id);
		
		MemberDto memberDto = memberSqlMapper.selectById(boardDto.getMember_id());
		
		List<BoardImageDto> boardImageDtoList = boardSqlMapper.selectBoardImageByBoardId(id);
		
		map.put("memberDto", memberDto);
		map.put("boardDto", boardDto);
		map.put("boardImageDtoList", boardImageDtoList);
		
		return map;
		
	}
	
	public void increaseReadCount(int id) {
		boardSqlMapper.increaseReadCount(id);
	}
	
	public void deleteContent(int id) {
		boardSqlMapper.deleteById(id);
	}
	
	public void updateContent(BoardDto boardDto) {
		boardSqlMapper.update(boardDto);
	}
	
	public void toggleLike(BoardLikeDto boardLikeDto) {
		
		if(boardSqlMapper.countMyLike(boardLikeDto) > 0) {
			boardSqlMapper.deleteLike(boardLikeDto);
		} else {
			boardSqlMapper.insertLike(boardLikeDto);
		}
	}
	
	public boolean isLiked(BoardLikeDto boardLikeDto) {
		return boardSqlMapper.countMyLike(boardLikeDto) > 0;
	}
	
	public int getTotalLike(int boardId) {
		return boardSqlMapper.countLikeByBoardId(boardId);
	}
	
	//댓글
	public void registerComment (CommentDto commentDto) {
		boardSqlMapper.insertComment(commentDto);
	}
	
	public void deleteComment(int id) {
		boardSqlMapper.deleteComment(id);
	}
	
	public void updateComment(CommentDto commentDto) {
		boardSqlMapper.updateComment(commentDto);
	}
	
	public List<Map<String, Object>> getCommentList(int boardId) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<CommentDto> commentList = boardSqlMapper.selectCommentAll(boardId);
		for(CommentDto commentDto :  commentList) {
			MemberDto memberDto = memberSqlMapper.selectById(commentDto.getMember_id());
			Map<String, Object> map = new HashMap<>();
			map.put("memberDto", memberDto);
			map.put("commentDto", commentDto);
			list.add(map);
		}
			
			return list;
	}
}
