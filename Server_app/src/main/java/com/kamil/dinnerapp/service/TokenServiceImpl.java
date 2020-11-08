package com.kamil.dinnerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.entity.JwtUser;
import com.kamil.dinnerapp.security.JwtGenerator;

@Service
public class TokenServiceImpl {

	
private JwtGenerator jwtGenerator;
	
	@Autowired
	public TokenServiceImpl(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}
	
	public String generate( JwtUser jwtUser) {
		
		JwtGenerator jwtGenerator = new JwtGenerator();
		return jwtGenerator.generate(jwtUser);
		
	}
	
}
