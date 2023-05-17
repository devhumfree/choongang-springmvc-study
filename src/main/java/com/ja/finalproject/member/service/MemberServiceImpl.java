package com.ja.finalproject.member.service; // root-context에 인스턴스로 들어감

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.dto.MemberDto;
import com.ja.finalproject.member.mapper.MemberSqlMapper;

@Service
public class MemberServiceImpl {
	
	@Autowired
	private MemberSqlMapper memberSqlMapper;
	
	public void register(MemberDto memberDto) {
		
		System.out.println("사실상 특이한 경우에 여기서 알고리즘 수행");
		memberSqlMapper.insert(memberDto);
	}
	
	public MemberDto getMemberByIdAndPw(MemberDto memberDto) {
		
		MemberDto sessionUser = memberSqlMapper.selectByUserIdAndPw(memberDto);
		
		return sessionUser;
	}
}
