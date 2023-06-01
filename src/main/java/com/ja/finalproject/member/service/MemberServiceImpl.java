package com.ja.finalproject.member.service; // root-context에 인스턴스로 들어감

import java.util.List;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ja.finalproject.dto.HobbyCategoryDto;
import com.ja.finalproject.dto.MailAuthDto;
import com.ja.finalproject.dto.MemberDto;
import com.ja.finalproject.dto.MemberHobbyDto;
import com.ja.finalproject.member.mapper.MemberSqlMapper;
import com.js.finalproject.util.PasswordEncoder;

@Service
public class MemberServiceImpl {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MemberSqlMapper memberSqlMapper;
	
	
	public void register(MemberDto memberDto, int[] hobbyIdList) {
		System.out.println("여기서 알고리즘 수행");
		
		int memberPk = memberSqlMapper.createPk(); //먼저 pk를 만들고 id에 넣어줌m=, 4번인경우
		
		memberDto.setId(memberPk); //4번 id로 입력
		
		//암호화
		String pw = memberDto.getUser_pw();
		pw = PasswordEncoder.encoding(pw);
		memberDto.setUser_pw(pw);
		
		memberSqlMapper.insert(memberDto);
		
		if(hobbyIdList != null) {
			for(int hobbyId : hobbyIdList) {
				MemberHobbyDto memberHobbyDto = new MemberHobbyDto();
				memberHobbyDto.setHobby_id(hobbyId);
				memberHobbyDto.setMember_id(memberPk); //4번 pk에 계속 넣어줌
				memberSqlMapper.insertMemberHobby(memberHobbyDto);
			}
		}
		
		String mailAuthKey = UUID.randomUUID().toString();
		
		MailAuthDto mailAuthDto = new MailAuthDto();
		mailAuthDto.setMember_id(memberPk);
		mailAuthDto.setKey(mailAuthKey);
		memberSqlMapper.insertMailAuth(mailAuthDto);
		
		// 이메일 보내기 (쓰레드 처리)
		new MailSendThread(javaMailSender, memberDto.getEmail(), mailAuthKey).start();

	}
	
	
	
	public MemberDto getMemberByIdAndPw(MemberDto memberDto) {
		
		//암호화
		String pw = memberDto.getUser_pw();
		pw = PasswordEncoder.encoding(pw);
		memberDto.setUser_pw(pw);
		
		MemberDto sessionUser = memberSqlMapper.selectByUserIdAndPw(memberDto);
		
		return sessionUser;
	}
	
	public List<HobbyCategoryDto> getHobbyList() {
		return memberSqlMapper.selectHobbyList();
	}
	
	public void successMail(String key) {
		memberSqlMapper.updateCompleteYByKey(key);
	}
	
	
	public boolean existUserId(String userId) {
		int count = memberSqlMapper.countByUserId(userId);
		
		if(count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}


class MailSendThread extends Thread{
	
	private JavaMailSender javaMailSender;
	private String to;
	private String key;
	
	public MailSendThread(JavaMailSender javaMailSender, String to, String key) {
		super();
		this.javaMailSender = javaMailSender;
		this.to = to;
		this.key = key;
	}

	public void run() {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			
			mimeMessageHelper.setSubject("FP 회원가입을 축하드립니다.");
			
			
			String text = "";
			text +="회원가입을 축하드립니다.<br>";
			text +="아래 링크를 클릭하여 인증을 완료하신 후 이용 가능합니다.<br>";
			text +="<a href='http://localhost:8181/finalproject/member/successMail?key="+key+"'>인증하기</a>";
			
			mimeMessageHelper.setText(text, true);
			
			mimeMessageHelper.setFrom("admin", "FP관리자"); 
			mimeMessageHelper.setTo(to);
			
			
			javaMailSender.send(mimeMessage);
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}

}





