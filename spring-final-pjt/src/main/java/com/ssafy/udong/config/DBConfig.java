package com.ssafy.udong.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

//MyBatis에서 구현체로 만들어줄 DAO를 스캔하기 위한 파일 
@Configuration
@MapperScan(basePackages = "com.ssafy.udong.model.dao")
public class DBConfig {

}