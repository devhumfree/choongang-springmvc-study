package com.ja.finalproject.member.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.finalproject.dto.HobbyCategoryDto;
import com.ja.finalproject.dto.MemberDto;
import com.ja.finalproject.member.service.MemberServiceImpl;

@Controller
@RequestMapping("member/*")
public class MemberController {
	// 서비스에 있는 메서드를 호출해야하 함!!
	// 멤버변수 선언 및 Autowired 하여 제어역행 IoC해야함
	
	@Autowired
	private MemberServiceImpl memberService;
	
	@RequestMapping("loginPage")
	public String loginPage() {
		return "member/loginPage";
	}
	
	@RequestMapping("registerPage")
	public String registerPage(Model model) {
		
		/*
		 * List<HobbyCategoryDto> list = memberService.getHobbyList();
		 * model.addAttribute("hobbyList", list);
		 */
		
		model.addAttribute("hobbyList", memberService.getHobbyList());
		return "member/registerPage";
	}
	
	@RequestMapping("registerProcess")
	public String registerProcess(MemberDto params, int[] hobby_id) {
		// DB insert...
		
		// 3tear
		//요청 -> Controller(Tomcat API), -> service(비즈니스로직담당 '코어로직' 알고리즘수행), -> persistance(DB연동로직)
		
		System.out.println("레지스터프로세스 콜");
		System.out.println(params.getUser_id());
		System.out.println(params.getUser_pw());
		System.out.println(params.getNickname());
		
		memberService.register(params, hobby_id);
		
		return "member/registerComplete";
//		포워딩 제이에스피
//		리다리렉트는 리퀘스트 매핑으로
	}
	
	@RequestMapping("loginProcess")
	public String loginProcess(HttpSession session, MemberDto params) {
		MemberDto sessionUser = memberService.getMemberByIdAndPw(params);
		
		if(sessionUser == null) {
			// 인증실팽
			return "member/loginFail";
		} else {
			// 인증성공
			session.setAttribute("sessionUser", sessionUser);
			return "redirect:../board/mainPage";
		}
	}

	@RequestMapping("logoutProcess")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:../board/mainPage";
	}
	
	@RequestMapping("successMail")
	public String SuccessMail(String key) {
		
		memberService.successMail(key);
		
		return "member/completeMailAuth";
	}
}






















