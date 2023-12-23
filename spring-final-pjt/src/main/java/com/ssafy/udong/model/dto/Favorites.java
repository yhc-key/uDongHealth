package com.ssafy.udong.model.dto;

public class Favorites {
	private int favoritesId; //즐겨찾기 ID
	private int userId; 	//유저 ID
	private int placeId;	//장소 ID
	
	//기본 생성자
	public Favorites() {}

	public Favorites(int favoritesId, int userId, int placeId) {
		this.favoritesId = favoritesId;
		this.userId = userId;
		this.placeId = placeId;
	}

	public int getFavoritesId() {
		return favoritesId;
	}

	public void setFavoritesId(int favoritesId) {
		this.favoritesId = favoritesId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	@Override
	public String toString() {
		return "Favorites [favoritesId=" + favoritesId + ", userId=" + userId + ", placeId=" + placeId + "]";
	}
	
	
}
