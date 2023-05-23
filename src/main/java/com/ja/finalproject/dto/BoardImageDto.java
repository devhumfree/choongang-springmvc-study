package com.ja.finalproject.dto;

public class BoardImageDto {
	
	private int id;
	private int board_id;
	private String original_filename;
	private String link;
	
	public BoardImageDto() {
		super();
	}

	public BoardImageDto(int id, int board_id, String original_filename, String link) {
		super();
		this.id = id;
		this.board_id = board_id;
		this.original_filename = original_filename;
		this.link = link;
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

	public String getOriginal_filename() {
		return original_filename;
	}

	public void setOriginal_filename(String original_filename) {
		this.original_filename = original_filename;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	

}
