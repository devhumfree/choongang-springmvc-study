package com.ja.finalproject.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ja.finalproject.dto.MemberDto;
import com.ja.finalproject.member.service.MemberServiceImpl;

// AJAX 용
// 다 똑같은데 포워딩 하지 않음(HTML 화면 리턴 X, 값을 리턴
// 리턴 타입은 꼭 String이 아니어도 된다.
// 일반적으로 리턴 타입은 객체 혹은 컬렉션을 리턴한다 => JSON 변환해서 리턴함.


@RestController
@RequestMapping("/member/*")
public class RestMemberController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	@RequestMapping("existsUserId")
	public Map<String, Object> existsUserId(String userId) {
		System.out.println("테스트!!!!");
		
		boolean exists = memberService.existUserId(userId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", exists);
		
		return map;
	}
	
	@RequestMapping("getMyId")
	public Map<String, Object> getMyId(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		MemberDto sessionUser = (MemberDto)session.getAttribute("sessionUser");
		
		if(sessionUser == null) {
			map.put("result", "fail");
			map.put("reason", "로그인 되어 있지 않습니다.");
		}else {
			map.put("result", "success");
			map.put("id", sessionUser.getId());
		}
		
		return map;
	}
	
	
}
