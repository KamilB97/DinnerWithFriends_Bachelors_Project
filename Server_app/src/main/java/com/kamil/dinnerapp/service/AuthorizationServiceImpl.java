package com.kamil.dinnerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.entity.JwtUser;
import com.kamil.dinnerapp.entity.UserCredentials;
import com.kamil.dinnerapp.entity.User;

@Service
public class AuthorizationServiceImpl {

	private UserServiceImpl userService;
	private BCryptPasswordEncoder BCEncoder;

	@Autowired
	public AuthorizationServiceImpl(
			UserServiceImpl userService,
			BCryptPasswordEncoder BCEncoder) {
		
		this.userService = userService;
		this.BCEncoder = BCEncoder;
	}

	public JwtUser authorize(UserCredentials userCredentials) {

		String email = userCredentials.getUsername();
		String rawPassword = userCredentials.getPassword();

		User theUser = userService.getUserByEmail(email);

		String dbEncryptedPassword = theUser.getPassword();

		boolean isPasswordCorrect = BCEncoder.matches(rawPassword, dbEncryptedPassword);

		if (isPasswordCorrect) {

			JwtUser jwtUser = new JwtUser(theUser.getId(), theUser.getEmail());

			return jwtUser;
		}

		return null;

	}
	
	public JwtUser authorize(Integer userId, String password) {

		User theUser = userService.get(userId);

		String dbEncryptedPassword = theUser.getPassword();

		boolean isPasswordCorrect = BCEncoder.matches(password, dbEncryptedPassword);

		if (isPasswordCorrect) {

			JwtUser jwtUser = new JwtUser(theUser.getId(), theUser.getEmail());

			return jwtUser;
		}

		return null;

	}

}
