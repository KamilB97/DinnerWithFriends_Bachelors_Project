package com.kamil.dinnerapp.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.entity.JwtUser;
import com.kamil.dinnerapp.entity.User;
import com.kamil.dinnerapp.entity.send.SendUpdatePassword;

@Service
public class UpdatePassowrdServiceImpl {

	private AuthorizationServiceImpl authorizationService;
	private BCryptPasswordEncoder passwordEncoder;
	private UserServiceImpl userService;

	public UpdatePassowrdServiceImpl(BCryptPasswordEncoder passwordEncoder, UserServiceImpl userService,
			AuthorizationServiceImpl authorizationService) {
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
		this.authorizationService = authorizationService;
	}

	@Transactional
	public void updatePassword(SendUpdatePassword updatePassword) throws Exception {

		JwtUser jwtUser = authorizationService.authorize(updatePassword.getUserId(), updatePassword.getOldPassword());

		if (jwtUser != null) {
			User user = userService.get(updatePassword.getUserId());
			String encryptedPassword = passwordEncoder.encode(updatePassword.getNewPassword());
			user.setPassword(encryptedPassword);
			userService.update(user);
		} else {
			throw new Exception("Niepoprawne Hasło");
		}

	}
}
