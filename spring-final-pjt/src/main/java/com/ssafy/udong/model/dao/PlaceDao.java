package com.ssafy.udong.model.dao;

import java.util.List;

import com.ssafy.udong.model.dto.Place;
import com.ssafy.udong.model.dto.SearchCondition;

public interface PlaceDao {
	//장소 등록
	public void insertPlace(Place place);

	//전체 장소 목록 조회
	public List<Place> getPlaces();

	//상세 장소 조회
	public Place getPlaceById(int placeId);

	// 검색 기능
	public List<Place> search(SearchCondition condition);

	//현재 위치에서 가장 가까운 4곳 조회
	public List<Place> findNearPlaces(double latitude, double longitude);
}
