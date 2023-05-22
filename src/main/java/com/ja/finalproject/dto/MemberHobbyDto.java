package com.ja.finalproject.dto;

public class MemberHobbyDto {
	private int id;
	private int member_id;
	private int hobby_id;
	
	public MemberHobbyDto() {
		super();
	}

	public MemberHobbyDto(int id, int member_id, int hobby_id) {
		super();
		this.id = id;
		this.member_id = member_id;
		this.hobby_id = hobby_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getHobby_id() {
		return hobby_id;
	}

	public void setHobby_id(int hobby_id) {
		this.hobby_id = hobby_id;
	}
	
	
}
