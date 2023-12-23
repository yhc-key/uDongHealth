package com.ssafy.udong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.udong.model.dto.Review;
import com.ssafy.udong.model.service.ReviewService;


@RestController
@RequestMapping("/api")
public class ReviewController {
	private static final String SUCCESS = "succes";
	private static final String FAIL = "fail";

	@Autowired
	private ReviewService reviewService;

	// 리뷰 목록 조회
	@GetMapping("/review/{placeId}")
	public ResponseEntity<?> list(@PathVariable int placeId) {
		List<Review> list = reviewService.getReviews(placeId);
			
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}

	// 리뷰 상세 조회
	@GetMapping("/review/{placeId}/{reviewId}")
	public ResponseEntity<Review> detail(@PathVariable int placeId, @PathVariable int reviewId) {
		Review review = reviewService.getReview(reviewId);
		if (review != null)
			return new ResponseEntity<Review>(review, HttpStatus.OK);
		return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
	}

	// 리뷰 등록
	@PostMapping("/review/{placeId}")
	public ResponseEntity<Review> write(@RequestBody Review review,
			@PathVariable int placeId) {
		reviewService.writeReview(review);
			
		return new ResponseEntity<Review>(review, HttpStatus.CREATED);
	}

	// 리뷰 삭제
	@DeleteMapping("/review/{placeId}/{reviewId}")
	public ResponseEntity<String> delete(@PathVariable int placeId, @PathVariable int reviewId) {
		if (reviewService.removeReview(reviewId))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.NOT_FOUND);
	}

	// 리뷰 수정
	@PutMapping("/review/{placeId}/{reviewId}")
	public ResponseEntity<String> update(@RequestBody String content,
			@PathVariable int placeId, @PathVariable int reviewId) {
		if (reviewService.modifyReview(content, reviewId))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}

}
