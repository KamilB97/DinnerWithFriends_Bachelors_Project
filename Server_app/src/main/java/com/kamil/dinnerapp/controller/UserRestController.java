package com.kamil.dinnerapp.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.kamil.dinnerapp.dao.UserConnectionDAOImpl;
import com.kamil.dinnerapp.entity.JwtUser;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.SendProfile;
import com.kamil.dinnerapp.entity.SendUser;
import com.kamil.dinnerapp.entity.User;
import com.kamil.dinnerapp.entity.UserConnection;
import com.kamil.dinnerapp.entity.send.SendAbout;
import com.kamil.dinnerapp.entity.send.SendEmail;
import com.kamil.dinnerapp.entity.send.SendUpdatePassword;
import com.kamil.dinnerapp.service.AuthorizationServiceImpl;
import com.kamil.dinnerapp.service.CreateAndUpdateProfileServiceImpl;
import com.kamil.dinnerapp.service.CreateAndUpdateUserServiceImpl;
import com.kamil.dinnerapp.service.GenericService;
import com.kamil.dinnerapp.service.MatchingServiceImpl;
import com.kamil.dinnerapp.service.ProfileServiceImpl;
import com.kamil.dinnerapp.service.UpdatePassowrdServiceImpl;
import com.kamil.dinnerapp.service.UserConnectionServiceImpl;
import com.kamil.dinnerapp.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
@EnableWebMvc
public class UserRestController {

	private ProfileServiceImpl profileService;
	private UserServiceImpl userServiceImpl;
	private UserConnectionServiceImpl userConnectionService;
	private MatchingServiceImpl matchingservice;
	private CreateAndUpdateProfileServiceImpl createAndUpdateProfilService;
	private CreateAndUpdateUserServiceImpl createAndUpdateUserService;
	private UpdatePassowrdServiceImpl updatePasswordService;
	

	@Autowired
	public UserRestController(ProfileServiceImpl profileService, UserServiceImpl userServiceImpl,
			UserConnectionServiceImpl userConnection, MatchingServiceImpl matchingservice,
			CreateAndUpdateProfileServiceImpl createAndUpdateProfilService,
			CreateAndUpdateUserServiceImpl createAndUpdateUserService,
			UpdatePassowrdServiceImpl updatePasswordService
			) {
		this.profileService = profileService;
		this.userServiceImpl = userServiceImpl;
		this.userConnectionService = userConnection;
		this.matchingservice = matchingservice;
		this.createAndUpdateProfilService = createAndUpdateProfilService;
		this.createAndUpdateUserService = createAndUpdateUserService;
		this.updatePasswordService =updatePasswordService;
	}

	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		// System.out.println("profiles:getAll - " +profileService.getAll());
		return userServiceImpl.get(userId);
	}

	@PutMapping("/users")
	public String updateUser(@RequestBody SendUser sendUser) {

		createAndUpdateUserService.UpdateUser(sendUser);

		return "succes";
	}

	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {

		userServiceImpl.delete(userId);

	}

	@GetMapping("/profiles/{profileId}")
	public  com.kamil.dinnerapp.entity.send.SendProfile getProfile(@PathVariable int profileId) {
		// System.out.println("profiles:getAll - " +profileService.getAll());
		return profileService.getSendProfile(profileId);
	}
	
	@GetMapping("/profiles/byuserid/{userId}")
	public Profile getProfileByUserId(@PathVariable Integer userId) {
		// System.out.println("profiles:getAll - " +profileService.getAll());
		return profileService.getProfileByUserId(userId);
	}

	@PostMapping("/profiles")
	public void createProfile(@RequestBody SendProfile sendProfile) {
		createAndUpdateProfilService.CreateProfile(sendProfile);

	}
	
	@PutMapping("/profiles")
	public void updateProfile(@RequestBody SendProfile sendProfile) {
		createAndUpdateProfilService.UpdateProfile(sendProfile);
	}
	
	@PostMapping("/profiles/about")
	public void updateProfileAbout (@RequestBody SendAbout sendAbout) {
		profileService.updateAbout(sendAbout);
	}

	@GetMapping("profiles/myprofile/{profileId}")
	public com.kamil.dinnerapp.entity.send.SendProfile getMyProfile(@PathVariable int profileId) {
		return profileService.getSendProfile(profileId);
	}

	@GetMapping("/profiles")
	public List<Profile> getAllProfiles() {
		return profileService.getAll();
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userServiceImpl.getAll();
	}

	@PostMapping("/userconnections")
	public void addUserConnection(@RequestBody UserConnection connection) {
		userConnectionService.save(connection);
	}

	@GetMapping("/userconnections")
	public List<UserConnection> getUserConnections() {

		return userConnectionService.getAll();
	}
	@PutMapping("users/password")
	public Map<String, Boolean> updatePassword(@RequestBody SendUpdatePassword updatePassword){
		

		try {
			updatePasswordService.updatePassword(updatePassword);	
			return Collections.singletonMap("response", true);
			
		} catch (Exception e) {
			 return Collections.singletonMap("response", false);
		}
		 
		
		
		
	}
	
	// przerobić na posta i jazda 

	

}