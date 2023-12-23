package com.ssafy.udong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.udong.model.dto.Place;
import com.ssafy.udong.model.service.FavoritesService;

@RestController
@RequestMapping("/api")
public class FavoritesController {
	private static final String SUCCESS = "succes";
	private static final String FAIL = "fail";

	@Autowired
	private FavoritesService favoritesService;
	

	//즐겨찾기 등록
	@PostMapping("/favorites/{userId}/{placeId}")
	public ResponseEntity<String> addFavorites(@PathVariable int userId,
			@PathVariable int placeId) throws Exception{
		
		favoritesService.addFavorites(userId, placeId);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);			
	}
	
	//즐겨찾기 목록 조회
	@GetMapping("/favorites/{userId}")
	public ResponseEntity<?> getFavorites(@PathVariable int userId){
		
		List<Place> list = favoritesService.getList(userId);
		
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Place>>(list, HttpStatus.OK);
	}
	
	//즐겨찾기 삭제
	@DeleteMapping("/favorites/{userId}/{placeId}")
	public ResponseEntity<String> removeFavorites(@PathVariable int userId,
			@PathVariable int placeId){
		
		if (favoritesService.removeFavorites(userId, placeId))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.NOT_FOUND);
		
	}
}
