package com.ja.finalproject.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.board.mapper.BoardSqlMapper;
import com.ja.finalproject.dto.BoardDto;
import com.ja.finalproject.dto.MemberDto;
import com.ja.finalproject.member.mapper.MemberSqlMapper;

@Service
public class BoardServiceImpl {
	
	@Autowired
	private BoardSqlMapper boardSqlMapper;
	@Autowired
	private MemberSqlMapper memberSqlMapper;

	public void writeContent(BoardDto boardDto) {
		
		boardSqlMapper.insert(boardDto);
		
		
	}
	
	public List<Map<String,Object>> getBoardList() {
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		List<BoardDto> boardDtoList = boardSqlMapper.selectAll();
		
		
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
	
	public Map<String, Object> getBoard(int id) {
		
		Map<String, Object> map = new HashMap<>();
		
		BoardDto boardDto = boardSqlMapper.selectById(id);
		MemberDto memberDto = memberSqlMapper.selectById(boardDto.getMember_id());
		
		map.put("memberDto", memberDto);
		map.put("boardDto", boardDto);
		
		return map;
		
	}
	
	
	
	
	
	
	
	
	
}
