package com.ja.finalproject.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ja.finalproject.board.service.BoardServiceImpl;
import com.ja.finalproject.dto.BoardLikeDto;
import com.ja.finalproject.dto.CommentDto;
import com.ja.finalproject.dto.MemberDto;

@RestController
@RequestMapping("/board/*")
public class RestBoardController {
	
	@Autowired
	private BoardServiceImpl boardService;

	@RequestMapping("toggleLike")
	public Map<String, Object> toggleLike(HttpSession session, BoardLikeDto params) {

		Map<String, Object> map = new HashMap<>();
		
		MemberDto sessionUser = (MemberDto)session.getAttribute("sessionUser");
		params.setMember_id(sessionUser.getId());
		boardService.toggleLike(params);
		
		map.put("result", "success");
		
		return map;
		
	}
	
	@RequestMapping("isLiked")
	public Map<String, Object> isLiked(HttpSession session, BoardLikeDto params) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 
		 MemberDto sessionUser = (MemberDto)session.getAttribute("sessionUser");
		 
		 if(sessionUser == null) {
				map.put("result", "fail");
				map.put("reason", "로그인 되어 있지 않습니다.");
				return map;
		 }
		 
		 params.setMember_id(sessionUser.getId());
		 
			map.put("result", "success");
			map.put("isLiked", boardService.isLiked(params));
			
			return map;
	}
	
	@RequestMapping("getTotalLikeCount")
	public Map<String, Object> getTotalLikeCount(int boardId) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("result", "success");
		map.put("count", boardService.getTotalLike(boardId));
		
		return map;
	}
	
	@RequestMapping("registerComment")
	public Map<String, Object> registerComment(HttpSession session, CommentDto params) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		MemberDto sessionUser = (MemberDto)session.getAttribute("sessionUser");
		params.setMember_id(sessionUser.getId());
		
		boardService.registerComment(params);
		
		map.put("result", "success");
		
		return map;
	}
	
	
	@RequestMapping("deleteComment")
	public Map<String, Object> deleteComment(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		boardService.deleteComment(id);
		
		map.put("result", "success");
		
		return map;
	}
	
	@RequestMapping("updateComment")
	public Map<String, Object> updateComment(CommentDto params) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		boardService.updateComment(params);
				map.put("result", "success");
		
		return map;
	}
	
	@RequestMapping("getCommentList")
	public Map<String, Object> getCommentList(int boardId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("commentList", boardService.getCommentList(boardId));
		
		map.put("result", "success");
		
		return map;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
