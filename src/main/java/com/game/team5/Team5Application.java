package com.game.team5;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan //mybatis mapper.xml 파일을 찾아서 연결시켜주는 어노테이션
@ComponentScan //6개의 대표 어노테이션 Component, RestController, Controller, Repository, Service, Configuration 을 제외한 어노테이션을 찾음 
public class Team5Application {

	public static void main(String[] args) {
		SpringApplication.run(Team5Application.class, args);
	}

}
