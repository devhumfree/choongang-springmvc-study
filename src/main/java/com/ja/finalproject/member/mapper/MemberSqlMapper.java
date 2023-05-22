package com.ja.finalproject.member.mapper;

import java.util.List;

import com.ja.finalproject.dto.HobbyCategoryDto;
import com.ja.finalproject.dto.MemberDto;
import com.ja.finalproject.dto.MemberHobbyDto;

public interface MemberSqlMapper {
		// insert, delete, update 사실상 리턴타입 void
		// select는 리턴타입 고려해야함
	
		public int createPk();
	
		public void insert(MemberDto memberDto);
		
		public MemberDto selectByUserIdAndPw(MemberDto memberDto);
		
		public MemberDto selectById(int id); //#id
		
		//취미 관련
		public List<HobbyCategoryDto> selectHobbyList();
		//취미 등록
		public void insertMemberHobby(MemberHobbyDto memberHobbyDto);
		
}
