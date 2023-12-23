package com.ssafy.udong.model.service;

import com.ssafy.udong.model.dto.User;

public interface UserService {

	//회원가입
	public void signUpUser(User user) throws Exception;

	//로그인
	public User login(User user) throws Exception;



}
