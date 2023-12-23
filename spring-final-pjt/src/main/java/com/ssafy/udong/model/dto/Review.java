package com.ssafy.udong.model.dto;

public class Review {
	private int reviewId; 	//리뷰id
	private int placeId; 	//운동장소id
	private int userId; 	//유저id
	private String writer; 	//작성자
	private int viewCnt; 	//조회수
	private String content; //내용
	
	//기본생성자
	public Review() {}
	
	public Review(int reviewId, int placeId, int userId, String writer, int viewCnt, String content) {
		this.reviewId = reviewId;
		this.placeId = placeId;
		this.userId = userId;
		this.writer = writer;
		this.viewCnt = viewCnt;
		this.content = content;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", placeId=" + placeId + ", userId=" + userId + ", writer=" + writer
				+ ", viewCnt=" + viewCnt + ", content=" + content + "]";
	}
	
	
	
}
