package com.ssafy.udong.model.dao;

import java.util.List;

import com.ssafy.udong.model.dto.Review;

public interface ReviewDao {

	//리뷰 목록 조회
	public List<Review> getAll(int placeId);

	//ID에 해당하는 리뷰 하나 가져오기
	public Review getOne(int reviewId);

	//리뷰 등록
	public void insertReview(Review review);

	//리뷰 삭제
	public int deleteReview(int reviewId);

	//리뷰 수정
	public int updateReview(int reviewId);

	//조회수 증가
	public void updateViewCnt(int reviewId);

	public boolean updateReview(String content, int reviewId);

	
}
