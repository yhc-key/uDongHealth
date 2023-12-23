package com.ssafy.udong.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.udong.model.dto.User;
import com.ssafy.udong.model.service.UserService;



@RestController
@RequestMapping("/api")
public class UserController {
	private static final String SUCCESS = "succes";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;

	//회원가입
	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody User user) throws Exception {
		userService.signUpUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	//로그인
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws Exception{
		User tmp = userService.login(user);
		
		//유저 정보가 없을 경우
		if(tmp == null) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<User>(tmp, HttpStatus.OK);
	}
	
}
