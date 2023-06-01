package com.ja.finalproject.dto;

import java.util.Date;

public class BoardLikeDto {

		private int id;
		private int board_id;
		private int member_id;
		private Date like_date;
		
		public BoardLikeDto() {
			super();
		}

		public BoardLikeDto(int id, int board_id, int member_id, Date like_date) {
			super();
			this.id = id;
			this.board_id = board_id;
			this.member_id = member_id;
			this.like_date = like_date;
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

		public Date getLike_date() {
			return like_date;
		}

		public void setLike_date(Date like_date) {
			this.like_date = like_date;
		}
		
		
		
}
