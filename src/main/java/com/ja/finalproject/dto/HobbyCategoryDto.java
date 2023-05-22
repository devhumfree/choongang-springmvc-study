package com.ja.finalproject.dto;

public class HobbyCategoryDto {
	private int id;
	private String name;
	
	public HobbyCategoryDto() {
		super();
	}

	public HobbyCategoryDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
