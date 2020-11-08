package com.kamil.dinnerapp.service;

import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.entity.JwtUser;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.TokenAndProfileId;
import com.kamil.dinnerapp.entity.UserCredentials;

@Service
public class LoginServiceImpl {
	
	private AuthorizationServiceImpl authorizationService;
	private TokenServiceImpl tokenService;
	private ProfileServiceImpl profileService;
	
	
	public LoginServiceImpl( AuthorizationServiceImpl authorizationService,
			TokenServiceImpl tokenService,
			ProfileServiceImpl profileService) {
		this.authorizationService = authorizationService;
		this.tokenService = tokenService;
		this.profileService = profileService;
	}
	
	public TokenAndProfileId login(UserCredentials userCredentials) {
		
		JwtUser jwtUser = authorizationService.authorize(userCredentials);
		
		
		if(jwtUser != null) {
			System.out.println("USER ID: " + jwtUser.getId());
			Profile profile = profileService.getProfileByUserId(jwtUser.getId());
			String name = profile.getFirstName();
			String surname = profile.getLastName();
			String token = tokenService.generate(jwtUser);
			Integer userId = profile.getUser().getId();
			TokenAndProfileId tokenAndProfileId = new TokenAndProfileId(token, profile.getId(),userId,name, surname);
			return tokenAndProfileId;
			
		}
		throw new RuntimeException("Wrong Password");
		
		
		
	}

}
