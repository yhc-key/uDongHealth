package com.ssafy.udong.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.udong.model.dao.ReviewDao;
import com.ssafy.udong.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewDao reviewDao;
	
	//리뷰 목록 조회
	@Override
	public List<Review> getReviews(int placeId) {
		return reviewDao.getAll(placeId);
	}

	//리뷰 한개 조회
	@Override
	public Review getReview(int reviewId) {
		reviewDao.updateViewCnt(reviewId);
		return reviewDao.getOne(reviewId);
	}

	//리뷰 등록
	@Override
	@Transactional
	public void writeReview(Review review) {
		reviewDao.insertReview(review);
	}

	//리뷰 삭제
	@Override
	@Transactional
	public boolean removeReview(int reviewId) {
		return reviewDao.deleteReview(reviewId) == 1;
	}

//	//리뷰 수정
//	@Override
//	@Transactional
//	public boolean modifyReview(RevieupdateRevieww review) {
//		Review r = reviewDao.getOne(review.getReviewId());
//		return reviewDao.updateReview() > 0;
//	}

	@Override
	public boolean modifyReview(String content, int reviewId) {
		
		return reviewDao.updateReview(content, reviewId);
	}


}
