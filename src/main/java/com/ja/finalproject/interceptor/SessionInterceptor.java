package com.ja.finalproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

public class SessionInterceptor implements HandlerInterceptor{
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws ModelAndViewDefiningException {
		
		if(request.getSession().getAttribute("sessionUser") == null) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("member/loginRequired");
			throw new ModelAndViewDefiningException(mv);
		}
		
		return true;
	}

}
