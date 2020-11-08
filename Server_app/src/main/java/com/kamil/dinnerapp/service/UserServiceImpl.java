package com.kamil.dinnerapp.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotAcceptable;

import com.kamil.dinnerapp.dao.GenericDao;
import com.kamil.dinnerapp.dao.UserDAOImpl;
import com.kamil.dinnerapp.entity.JwtUser;
import com.kamil.dinnerapp.entity.User;
import com.kamil.dinnerapp.entity.send.SendEmail;
import com.kamil.dinnerapp.entity.send.SendUpdatePassword;

@Service
public class UserServiceImpl {

	private UserDAOImpl userDAOImpl;
//	private AuthorizationServiceImpl authorizationService;
//	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserDAOImpl userDAO) {
		this.userDAOImpl = userDAO;
//		this.authorizationService = authorizationService;
//		this.passwordEncoder = passwordEncoder;

	}

	@Transactional
	public User get(int id) {
		User profile = userDAOImpl.get(id);
		return profile;
	}

	@Transactional
	public User getUserByEmail(String email) {
	
		User theUser = userDAOImpl.getUserByEmail(email);
		return theUser;
		
	}

	public User getUserByProfileId(Integer profileId) {
		User theUser = userDAOImpl.getUserByProfileId(profileId);
		return theUser;
	}

	@Transactional
	public List<User> getAll() {
		List<User> users = userDAOImpl.getAll();
		return users;
	}

//	@Transactional
//	public void updatePassword(SendUpdatePassword updatePassword)throws Exception{
//		
//		//JwtUser jwtUser = authorizationService.authorize(updatePassword.getUserId(), updatePassword.getOldPassword());
//		String jwtUser = "12";
//		if(jwtUser != null) {
//			User user = userDAOImpl.get(updatePassword.getUserId());
//			String encryptedPassword = passwordEncoder.encode(updatePassword.getNewPassword());
//			user.setPassword(encryptedPassword);
//			userDAOImpl.update(user);
//		}else {
//			throw new Exception("Niepoprawne Hasło");
//		}
//		
//		
//	}
	@Transactional
	public User save(User theUser) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("timestamp from user " + timestamp);
		String date = timestamp.toString();
		theUser.setRegisterDate(date);

		return userDAOImpl.save(theUser);

	}

	@Transactional
	public User update(User theUser) {
		return userDAOImpl.update(theUser);
	}

	@Transactional
	public void delete(Integer userId) {
		userDAOImpl.delete(userId);
	}

	@Transactional
	public Boolean isEmailAvailable(SendEmail email) {
		
			User user = userDAOImpl.getUserByEmail(email.getEmail());
			if(user == null) {
				return true;
			}
			else {
				return false;
			}
	}
	@Transactional
	public Boolean isEmailAvailable(String email) {
		
			User user = userDAOImpl.getUserByEmail(email);
			if(user == null) {
				return true;
			}
			else {
				return false;
			}
		
	}
}