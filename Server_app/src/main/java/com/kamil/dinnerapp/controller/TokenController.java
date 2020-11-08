package com.kamil.dinnerapp.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamil.dinnerapp.entity.JwtUser;
import com.kamil.dinnerapp.security.JwtGenerator;
import com.kamil.dinnerapp.service.TokenServiceImpl;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	private TokenServiceImpl tokenService;
	
	@Autowired
	public TokenController(TokenServiceImpl tokenService) {
		this.tokenService = tokenService;
	}

	@PostMapping
	public String generate(@RequestBody final JwtUser jwtUser) {
		
		return tokenService.generate(jwtUser);
		
	}
	

}
