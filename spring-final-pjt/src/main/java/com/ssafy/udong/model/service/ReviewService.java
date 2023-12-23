package com.ssafy.udong.model.service;

import java.util.List;
import com.ssafy.udong.model.dto.Review;

public interface ReviewService {

	//리뷰 목록 조회
	public List<Review> getReviews(int placeId);

	//리뷰 상세 조회
	public Review getReview(int reviewId);

	//리뷰 등록
	public void writeReview(Review review);

	//리뷰 삭제
	public boolean removeReview(int reviewId);

	//리뷰 수정
//	public boolean modifyReview(Review review);

	public boolean modifyReview(String content, int reviewId);

	
}
