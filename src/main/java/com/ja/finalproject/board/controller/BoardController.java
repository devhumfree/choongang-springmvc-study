package com.ja.finalproject.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ja.finalproject.board.service.BoardServiceImpl;
import com.ja.finalproject.dto.BoardDto;
import com.ja.finalproject.dto.BoardImageDto;
import com.ja.finalproject.dto.MemberDto;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardServiceImpl boardService;
	
	@RequestMapping("mainPage")
	public String mainPage(Model model,
			@RequestParam(value = "page", defaultValue = "1") int page,
			String searchType,
			String searchWord
			) {
		
		List<Map<String, Object>> list = boardService.getBoardList(page, searchType,  searchWord);
		int boardCount = boardService.getBoardCount(searchType, searchWord);
		int totalPage = (int)Math.ceil(boardCount/10.0);
		
		int startPage = ((page-1)/5)*5 +1;
		int endPage = ((page-1)/5+1)*5;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		
		model.addAttribute("list", list); //request 객체에 담는다.
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage",page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		String searchQueryString = "";
		if(searchType !=null && searchWord != null) {
			searchQueryString += "&searchType=" + searchType;			
			searchQueryString += "&searchWord=" + searchWord;

		}
		
		model.addAttribute("searchQueryString", searchQueryString);
		
		return "board/mainPage";
	}
	
	@RequestMapping("writeContentPage")
	public String writeContentPage() {
		return "board/wirteContentPage";
	}
	
	@RequestMapping("writeContentProcess")
	public String writeContentProcess(HttpSession session, BoardDto params, MultipartFile [] boardFiles) {
		
		List<BoardImageDto> boardImageDtoList = new ArrayList<>();
		
		// 파일 저장 로직
		if(boardFiles != null) {
			
			for(MultipartFile multipartFile : boardFiles) {
				if(multipartFile.isEmpty()) { // 예외처리
					continue;
				}
				
				System.out.println("파일명: " + multipartFile.getOriginalFilename());
				
				String rootFolder = "C:/uploadFiles/";
				
				// 날짜별 폴더 생성 로직
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String today = sdf.format(new Date());
				
				File targetFolder = new File(rootFolder + today);
				
				if(!targetFolder.exists()) {
						targetFolder.mkdirs();
				}
				
				// 저장 파일명 만들기. 핵심은 파일명 충돌 방지 = 랜덤 + 시간
				String fileName = UUID.randomUUID().toString();
				fileName +="-" + System.currentTimeMillis();
				
				// 확장자 추출
				String originalFileName = multipartFile.getOriginalFilename();
				
				String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
				
				String saveFileName = today + "/"+fileName + ext;	
				
				try {
					multipartFile.transferTo(new File(rootFolder+ saveFileName));					
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				BoardImageDto boardImageDto = new BoardImageDto();
				boardImageDto.setOriginal_filename(originalFileName);
				boardImageDto.setLink(saveFileName);
				
				boardImageDtoList.add(boardImageDto);
			}
			
		}
		
		
		// 데이터 저장 로직
		
		MemberDto sessionUser = (MemberDto)session.getAttribute("sessionUser");
		
		int memberId = sessionUser.getId();
		params.setMember_id(memberId);
		
		boardService.writeContent(params, boardImageDtoList);
		
		return "redirect:./mainPage";
	}
	
	@RequestMapping("readContentPage") //parameter
	public String readContentPage(Model model, int id) {
		
		boardService.increaseReadCount(id);
		
		Map<String, Object> map = boardService.getBoard(id);
		
		//html escape
		BoardDto boardDto = (BoardDto)map.get("boardDto");
		String content = boardDto.getContent();
		 content = StringEscapeUtils.escapeHtml4(content);
		 content = content.replaceAll("\n", "<br>");
		 boardDto.setContent(content);
		 
		 
		 
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
