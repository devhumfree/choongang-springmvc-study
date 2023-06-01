package com.ja.finalproject.dto;

import java.util.Date;

public class CommentDto {
	private int id;
	private int board_id;
	private int member_id;
	private String comment_text;
	private Date reg_date;
	
	public CommentDto() {
		super();
	}

	public CommentDto(int id, int board_id, int member_id, String comment_text, Date reg_date) {
		super();
		this.id = id;
		this.board_id = board_id;
		this.member_id = member_id;
		this.comment_text = comment_text;
		this.reg_date = reg_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
