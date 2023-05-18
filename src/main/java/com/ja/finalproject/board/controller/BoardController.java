package com.ja.finalproject.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.finalproject.board.service.BoardServiceImpl;
import com.ja.finalproject.dto.BoardDto;
import com.ja.finalproject.dto.MemberDto;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardServiceImpl boardService;
	
	@RequestMapping("mainPage")
	public String mainPage(Model model) {
		
		List<Map<String, Object>> list = boardService.getBoardList();
		
		model.addAttribute("list", list); //request 객체에 담는다.
		
		return "board/mainPage";
	}
	
	@RequestMapping("writeContentPage")
	public String writeContentPage() {
		return "board/wirteContentPage";
	}
	
	@RequestMapping("writeContentProcess")
	public String writeContentProcess(HttpSession session, BoardDto params) {
		
		MemberDto sessionUser = (MemberDto)session.getAttribute("sessionUser");
		
		int memberId = sessionUser.getId();
		params.setMember_id(memberId);
		
		boardService.writeContent(params);
		
		return "redirect:./mainPage";
	}
	
	@RequestMapping("readContentPage") //parameter
	public String readContentPage(Model model, int id) {
		
		boardService.increaseReadCount(id);
		
		Map<String, Object> map = boardService.getBoard(id);
		
		model.addAttribute("data", map);
		
		return "board/readContentPage";
	}
	
//	@RequestMapping("{id}") //pathVariable
//	public String readContentPageForPathVariable(Model model, @PathVariable("id") int id) {
//		
//		boardService.increaseReadCount(id);
//		
//		Map<String, Object> map = boardService.getBoard(id);	
//		
//		model.addAttribute("data", map);
//		
//		return "board/readContentPage";
//	}
	
	@RequestMapping("deleteProcess")
	public String deleteProcess(int id) {
		
		boardService.deleteContent(id);
		
		return "redirect:./mainPage";
	}
	
	@RequestMapping("updatePage")
	public String updatePage(Model model, int id) {
		
		Map<String, Object> map = boardService.getBoard(id);
		model.addAttribute("data", map);
		
		return "board/updatePage";
	}
	
	@RequestMapping("updateContentProcess")
	public String updateContentProcess(BoardDto boardDto) {
		
		boardService.updateContent(boardDto);
		
		return "redirect:./mainPage";
	}
}
