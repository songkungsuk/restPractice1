package com.game.team5.util;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTToken {
	private final String JWT_TOKEN_KEY;
	private final long JWT_TOKEN_EXPIRE_DATE;
	
	public JWTToken(@Value("${jwt.token.key}") String jwtTokenKey,
			@Value("${jwt.token.lifetime}") long jwtTokenExpireDate) {
			this.JWT_TOKEN_KEY = jwtTokenKey;
			this.JWT_TOKEN_EXPIRE_DATE = jwtTokenExpireDate;
	}
	
	public String getTokenKey() {
		return JWT_TOKEN_KEY;
	}
	
	public long getTokenExprieDate() {
		return JWT_TOKEN_EXPIRE_DATE;
	}
	
	public String getToken(String uiId) {
		Date date = new Date();
		long expireTime = date.getTime()+ JWT_TOKEN_EXPIRE_DATE;
		Date expireDate = new Date(expireTime);
		Key key = Keys.hmacShaKeyFor(JWT_TOKEN_KEY.getBytes());
		log.info("key=>{}",key);
		
		String token = Jwts.builder()
		.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
		.setSubject(uiId)
		.setIssuedAt(date)
		.setExpiration(expireDate)
		.signWith(key, SignatureAlgorithm.HS256)
		.compact();
		
		return token;
	}
	
	public String getUserIdFromToken(String token) {
		Key key = Keys.hmacShaKeyFor(JWT_TOKEN_KEY.getBytes());
		
		try {
			String uiId = Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
			return uiId;
		} catch (ExpiredJwtException e) {
			log.error("Expired Token plz see your token Life Time");
			throw e;
		} catch (SignatureException e) {
			log.error("invalid ");
			throw e;
		}
		
	}
}
