package com.kamil.dinnerapp.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.kamil.dinnerapp.entity.City;
import com.kamil.dinnerapp.entity.JwtUser;
import com.kamil.dinnerapp.entity.SendUser;
import com.kamil.dinnerapp.entity.Token;
import com.kamil.dinnerapp.entity.TokenAndProfileId;
import com.kamil.dinnerapp.entity.User;
import com.kamil.dinnerapp.entity.UserCredentials;
import com.kamil.dinnerapp.entity.send.SendEmail;
import com.kamil.dinnerapp.service.CityServiceImpl;
import com.kamil.dinnerapp.service.CreateAndUpdateUserServiceImpl;
import com.kamil.dinnerapp.service.LoginServiceImpl;
import com.kamil.dinnerapp.service.TokenServiceImpl;
import com.kamil.dinnerapp.service.UserServiceImpl;



@RestController
@RequestMapping("/")
@EnableWebMvc
public class RegisterLoginRestController {
	
	private CreateAndUpdateUserServiceImpl createAndUpdateUserService;
	private TokenServiceImpl tokenService;
	private LoginServiceImpl loginService;
	private CityServiceImpl cityService;
	private UserServiceImpl userService;
@Autowired
	public RegisterLoginRestController(
			CreateAndUpdateUserServiceImpl createAndUpdateUserService,
			TokenServiceImpl tokenService,
			LoginServiceImpl loginService,
			CityServiceImpl cityService,
			UserServiceImpl userService) {
		this.createAndUpdateUserService = createAndUpdateUserService;
		this.tokenService = tokenService;
		this.loginService = loginService;
		this.cityService = cityService;
		this.userService = userService;
	}
	
	
	
	//@CrossOrigin(origins = "*")
	@PostMapping("/register")
	public Map<String,String> createUser(@RequestBody SendUser sendUser) {
		createAndUpdateUserService.createUser(sendUser);

		 return Collections.singletonMap("response", "succes");
	}
	
	@PostMapping("/login")
	public TokenAndProfileId loginUser(@RequestBody UserCredentials userCredentials) {
		
		
		TokenAndProfileId tokenAndProfile = loginService.login(userCredentials);
		//Token wrapedToken = new Token(token);
		return tokenAndProfile;
	}
	
	//@RequestMapping(value = "/test",method =RequestMethod.GET)
	@GetMapping("/test")
	public User test(@RequestHeader("Authorization") String token){

		System.out.println("before token express");
		System.out.println("tokken: "+ token);
		
		User user = new User();
		user.setEmail("test");
		user.setId(2);
		user.setPassword("test");
		user.setPhone("123456789");
		System.out.println("before test return");
		return user;
		
	}
	@PostMapping("/emails")
	public Map<String, Boolean> idEmailAvailable(@RequestBody SendEmail email) {
		
		Boolean isEmailAvailable =  userService.isEmailAvailable(email);
		
			 return Collections.singletonMap("available", isEmailAvailable);
		
		
	}
	
	

}
