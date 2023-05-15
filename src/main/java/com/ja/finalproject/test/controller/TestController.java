package com.ja.finalproject.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/test1")
//	@GetMapping("/test1")
//	@PostMapping("/test1")
	public String test1() {
		System.out.println("안녕1");
		return "test/test1"; // forwarding
	}
	
	@RequestMapping("/test2")
	public String test2() {
		System.out.println("안녕2");
		
		return "redirect:./test1"; // redirect
	}
	
}
