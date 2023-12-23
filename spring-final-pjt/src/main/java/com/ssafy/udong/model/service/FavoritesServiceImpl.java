package com.ssafy.udong.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.udong.model.dao.FavoritesDao;
import com.ssafy.udong.model.dao.PlaceDao;
import com.ssafy.udong.model.dao.UserDao;
import com.ssafy.udong.model.dto.Favorites;
import com.ssafy.udong.model.dto.Place;

@Service
public class FavoritesServiceImpl implements FavoritesService{

	@Autowired
	private FavoritesDao favoritesDao;
	
	@Autowired
	private PlaceDao placeDao;
	
	@Autowired
	private UserDao userDao;
	

	//즐겨찾기 등록
	@Override
	@Transactional
	public void addFavorites(int userId, int placeId) throws Exception {
		
		//유저와 장소 정보가 존재하는지 확인
		if(userDao.getUserById(userId) != null && placeDao.getPlaceById(placeId) != null) {
			//즐겨찾기가 이미 존재하면
			if(favoritesDao.getByUserIdAndPlaceId(userId, placeId) != null) {
				throw new Exception("즐겨찾기가 이미 존재합니다.");
			}
			//즐겨찾기가 존재하지 않는다면 새로 추가
			else {
				favoritesDao.insertFavorites(userId, placeId);
			}
		}else {
			//유저와 장소 정보가 존재하지 않는다면
			throw new Exception("유저 또는 장소의 정보가 존재하지 않습니다.");
		}

	}

	//즐겨찾기 목록 조회
	@Override
	public List<Place> getList(int userId) {
		List<Favorites> list = favoritesDao.getUserById(userId);
		
		List<Place> placeList = new ArrayList<Place>();
		for (Favorites f : list) {
			placeList.add(placeDao.getPlaceById(f.getPlaceId()));
		}
		return placeList;
	}

	//즐겨찾기 삭제
	@Override
	@Transactional
	public boolean removeFavorites(int userId, int placeId) {
		Favorites f = favoritesDao.getByUserIdAndPlaceId(userId,placeId);
		if(f != null) {
			//즐겨찾기 삭제
			favoritesDao.deleteFavorites(f.getFavoritesId());
			return true; //성공적으로 삭제됨
		}else {
			return false; //즐겨찾기 찾지 못함			
		}
	}
	
}
