package com.ssafy.udong.model.dto;

import java.util.Date;

public class User {
	private int userId; 		//유저id
	private String name; 		//유저명
	private String email;		//이메일
	private String password; 	//비밀번호
	private String favorites; 	//즐겨찾기
	private Date regDate; 		//가입날짜
	private Date loginDate; 	//로그인날짜
	private String isRetired; 	//탈퇴여부
	
	//기본생성자
	public User() {}

	public User(int userId, String name, String email, String password, String favorites, Date regDate, Date loginDate,
			String isRetired) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.favorites = favorites;
		this.regDate = regDate;
		this.loginDate = loginDate;
		this.isRetired = isRetired;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFavorites() {
		return favorites;
	}

	public void setFavorites(String favorites) {
		this.favorites = favorites;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getIsRetired() {
		return isRetired;
	}

	public void setIsRetired(String isRetired) {
		this.isRetired = isRetired;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", favorites=" + favorites + ", regDate=" + regDate + ", loginDate=" + loginDate + ", isRetired="
				+ isRetired + "]";
	}
	
	
	
	
}
