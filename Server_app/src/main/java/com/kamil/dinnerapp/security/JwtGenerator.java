package com.kamil.dinnerapp.security;

import org.springframework.stereotype.Component;

import com.kamil.dinnerapp.entity.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	
	private String secret = "dinnerapp";

	public String generate(JwtUser jwtUser) {
		
		Claims claims = Jwts.claims()					//Jwts.builder()   // setExpiration
				.setSubject(jwtUser.getUserName());
		claims.put("userId", String.valueOf(jwtUser.getId()));
		
		return Jwts.builder()
			.setClaims(claims)
			.signWith(SignatureAlgorithm.HS256, secret)
			.compact();
				
		
	}

}
