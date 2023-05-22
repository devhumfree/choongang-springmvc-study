package com.ja.finalproject.member.service; // root-context에 인스턴스로 들어감

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.dto.HobbyCategoryDto;
import com.ja.finalproject.dto.MemberDto;
import com.ja.finalproject.dto.MemberHobbyDto;
import com.ja.finalproject.member.mapper.MemberSqlMapper;

@Service
public class MemberServiceImpl {
	
	@Autowired
	private MemberSqlMapper memberSqlMapper;
	
	public void register(MemberDto memberDto, int[] hobbyIdList) {
		System.out.println("여기서 알고리즘 수행");
		
		int memberPk = memberSqlMapper.createPk(); //먼저 pk를 만들고 id에 넣어줌m=, 4번인경우
		
		memberDto.setId(memberPk); //4번 id로 입력
		memberSqlMapper.insert(memberDto);
		
		if(hobbyIdList != null) {
			for(int hobbyId : hobbyIdList) {
				MemberHobbyDto memberHobbyDto = new MemberHobbyDto();
				memberHobbyDto.setHobby_id(hobbyId);
				memberHobbyDto.setMember_id(memberPk); //4번 pk에 계속 넣어줌
				memberSqlMapper.insertMemberHobby(memberHobbyDto);
			}
		}
		
	}
	
	public MemberDto getMemberByIdAndPw(MemberDto memberDto) {
		
		MemberDto sessionUser = memberSqlMapper.selectByUserIdAndPw(memberDto);
		
		return sessionUser;
	}
	
	public List<HobbyCategoryDto> getHobbyList() {
		return memberSqlMapper.selectHobbyList();
	}
}
