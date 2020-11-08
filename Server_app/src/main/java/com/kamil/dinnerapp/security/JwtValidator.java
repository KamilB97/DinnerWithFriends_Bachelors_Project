package com.kamil.dinnerapp.security;

import org.springframework.stereotype.Component;

import com.kamil.dinnerapp.entity.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator  {

	private String secret = "dinnerapp";

	public JwtUser validate(String token) {
		
		JwtUser jwtUser = null;
		
		try {
			
			
			Claims body = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
			
			jwtUser = new JwtUser();
			jwtUser.setUserName(body.getSubject());
			jwtUser.setId(Integer.parseInt((String) body.get("userId")) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jwtUser;
		
	}

	
	
}
