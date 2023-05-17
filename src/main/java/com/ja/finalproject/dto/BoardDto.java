package com.ja.finalproject.dto;

import java.util.Date;

public class BoardDto {
	
	private int id;
	private int member_id;
	private String title;
	private String content;
	private int read_count;
	private Date reg_date;
	
	public BoardDto() {
		super();
	}

	public BoardDto(int id, int member_id, String title, String content, int read_count, Date reg_date) {
		super();
		this.id = id;
		this.member_id = member_id;
		this.title = title;
		this.content = content;
		this.read_count = read_count;
		this.reg_date = reg_date;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
	
	
	
}
