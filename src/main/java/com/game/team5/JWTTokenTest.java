package com.game.team5;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTTokenTest {
	
	private static final Long EXPIRETIME = 2000L;
	
	public static void main(String[] ages) {
		Date day = new Date();
		Date expireDay = new Date(day.getTime()+ EXPIRETIME);
		
		String keyStr = "12345678910123456789101234567891012345678910";
		//keyStr을 사용하여 shakey 
		Key key =  Keys.hmacShaKeyFor(keyStr.getBytes());
		
		String token = Jwts.builder() //jwts.builder를 이용한 jwtbuilder 인스턴스 생성
		.setHeaderParam(Header.TYPE, Header.JWT_TYPE) // Jwtbuilder의 메소드인
		.setSubject("Hello World") //토큰제목
		.setIssuedAt(day)//발행일
		.setExpiration(expireDay) //파기일 을 설정
		.signWith(key, SignatureAlgorithm.HS256) //jwt를 서명하기위한 키를 설정
		.compact(); //발행
		
		log.info("token =>{}" ,token); 
		
		String parse = Jwts.parserBuilder() //parse는 jwt를 검증하는것
		.setSigningKey(key) // 어떤 키를 사용해 검증을 할것인가.
		.build() //build 하면 thread-safe Jwtparser 가 반환됩니다. 뭐 대충 검증하는 쓰레드 발생한다는뜻이겠지.
		.parseClaimsJws(token) //오리지널 jwt 추출
		.getBody() // 페이로드 추출[내용]
		.getSubject(); // 제목 추출
		
		
		log.info("parse = > {}", parse);
		
		
		
		
	}
}
