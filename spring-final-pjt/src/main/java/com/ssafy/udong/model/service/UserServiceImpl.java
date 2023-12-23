package com.ssafy.udong.model.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.udong.model.dao.UserDao;
import com.ssafy.udong.model.dto.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;

	//회원가입
	@Override
	public void signUpUser(User user) throws Exception{
		//이메일 중복 검사
		if(isEmailAlreadyExists(user.getEmail())) {
			throw new Exception("중복된 이메일입니다.");
		}else {
			userDao.insertUser(user);
		}
	}

	//이메일 중복 검사
	private boolean isEmailAlreadyExists(String email) {
		User user = userDao.getUserByEmail(email);
		//이미 존재하면 true 반환
		if(user != null) {
			return true;
		}
		return false;
	}

	//로그인
	@Override
	public User login(User user) throws Exception {
		
		User tmp = userDao.getUser(user.getEmail());
		
		if(tmp != null) {
			//이메일에 해당하는 비밀번호 일치 여부 확인
			if(tmp.getPassword().equals(user.getPassword())) {
				//비밀번호 일치하면 로그인 성공
				System.out.println(tmp);
				return tmp;
			}else {
				throw new Exception("비밀번호가 일치하지 않습니다.");
			}
		}
		//이메일 존재하지 않을 경우
		else {
			throw new Exception("사용자를 찾을 수 없습니다.");
		}
	}

}