package com.ssafy.udong.model.dao;

import java.util.List;

import com.ssafy.udong.model.dto.Favorites;

public interface FavoritesDao {

	//즐겨찾기 등록
	void insertFavorites(int userId, int placeId);	
	
	//유저ID로 즐겨찾기 목록 조회
	List<Favorites> getUserById(int userId);

	//유저ID, 장소ID로 즐겨찾기 목록 조회
	Favorites getByUserIdAndPlaceId(int userId, int placeId);

	//즐겨찾기 삭제
	void deleteFavorites(int favoritesId);



}
