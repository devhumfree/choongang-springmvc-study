package com.ja.finalproject.member.mapper;

import com.ja.finalproject.dto.MemberDto;

public interface MemberSqlMapper {
		// insert, delete, update 사실상 리턴타입 void
		// select는 리턴타입 고려해야함
		public void insert(MemberDto memberDto);
		
		public MemberDto selectByUserIdAndPw(MemberDto memberDto);
		
		public MemberDto selectById(int id); //#id
}
