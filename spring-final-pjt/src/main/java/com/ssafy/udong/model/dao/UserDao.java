package com.ssafy.udong.model.dao;

import com.ssafy.udong.model.dto.User;

public interface UserDao {

	//회원가입
	public void insertUser(User user);

	//회원 이메일로 조회
	public User getUserByEmail(String email);

	//회원 아이디로 조회
	public User getUserById(int userId);
	
	//회원 조회
	public User getUser(String email);


}
