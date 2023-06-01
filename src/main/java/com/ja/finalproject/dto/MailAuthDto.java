package com.ja.finalproject.dto;

import java.util.Date;

public class MailAuthDto {

	private int id;
	private int member_id;
	private String key;
	private String complete;
	private Date complete_date;
	
	public MailAuthDto() {
		super();
	}

	public MailAuthDto(int id, int member_id, String key, String complete, Date complete_date) {
		super();
		this.id = id;
		this.member_id = member_id;
		this.key = key;
		this.complete = complete;
		this.complete_date = complete_date;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public Date getComplete_date() {
		return complete_date;
	}

	public void setComplete_date(Date complete_date) {
		this.complete_date = complete_date;
	}
	
	
	
}
