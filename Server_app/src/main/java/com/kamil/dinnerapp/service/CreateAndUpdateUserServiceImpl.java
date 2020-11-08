package com.kamil.dinnerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.entity.JwtUser;
import com.kamil.dinnerapp.entity.SendUser;
import com.kamil.dinnerapp.entity.User;
import com.kamil.dinnerapp.entity.send.SendEmail;
import com.kamil.dinnerapp.entity.send.SendProfile;

@Service
public class CreateAndUpdateUserServiceImpl {

	
	private UserServiceImpl userService;
    private BCryptPasswordEncoder passwordEncoder;
    private CreateAndUpdateProfileServiceImpl profileService;
	
	@Autowired
	public CreateAndUpdateUserServiceImpl(
			UserServiceImpl userService,
			BCryptPasswordEncoder passwordEncoder,
			CreateAndUpdateProfileServiceImpl profileService) {
		
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.profileService = profileService;
		
	}
	
	public void createUser(SendUser sendUser) {
		
	//	String email = sendUser.getEmail();
	//	if(userService.isEmailAvailable(email)) {
			String password = sendUser.getPassword();
			String encryptedPassword = passwordEncoder.encode(password);
			String phone = null;
			if (sendUser.getPhone() != null) {
				phone = sendUser.getPhone();
			}
			
			User theUser = new User();
			
			theUser.setEmail(sendUser.getEmail());
			theUser.setPassword(encryptedPassword);
			theUser.setPhone(phone);
			
			theUser = userService.save(theUser);
			
			com.kamil.dinnerapp.entity.SendProfile profile = new com.kamil.dinnerapp.entity.SendProfile();
			profile.setUserId(theUser.getId());
			profile.setFirstName("nie ustawiono");
			profile.setLastName("nie ustawiono");
			profile.setAge(0);
			profileService.CreateProfile(profile);
			//profile.setUserId(theUser.getId());
			
		//	return createProfileService.CreateProfile(profile);
			
		
		//}
		
		
			
		
	}
	
public User UpdateUser(SendUser sendUser) {
		
		Integer id = sendUser.getId();
		String email = sendUser.getEmail();
		String password = sendUser.getPassword();
		String phone = null;
		if (sendUser.getPhone() != null) {
			phone = sendUser.getPhone();
		}
		
		User theUser = new User();
		theUser.setId(id);
		theUser.setEmail(email);
		theUser.setPassword(password);
		theUser.setPhone(phone);
		
		theUser = userService.update(theUser);
		return theUser;
		
		
	}
	
	
	
}
