package com.ja.finalproject.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.finalproject.dto.MemberDto;

@Controller
@RequestMapping("member/*")
public class MemberController {
	
	@RequestMapping("loginPage")
	public String loginPage() {
		return "member/loginPage";
	}
	
	@RequestMapping("registerPage")
	public String registerPage() {
		return "member/registerPage";
	}
	
	@RequestMapping("registerProcess")
	public String registerProcess(MemberDto params) {
		System.out.println("레지스터프로세스 콜");
		System.out.println(params.getUser_id());
		System.out.println(params.getUser_pw());
		System.out.println(params.getNickname());
		return "";
	}
	
}
