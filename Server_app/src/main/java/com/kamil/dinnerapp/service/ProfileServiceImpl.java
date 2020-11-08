package com.kamil.dinnerapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.GenericDao;
import com.kamil.dinnerapp.dao.ProfileDAO;
import com.kamil.dinnerapp.dao.ProfileDAOImpl;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.jointable.ProfileDietary;
import com.kamil.dinnerapp.entity.jointable.ProfileInteresting;
import com.kamil.dinnerapp.entity.send.SendAbout;
import com.kamil.dinnerapp.entity.send.SendDietaryPreference;
import com.kamil.dinnerapp.entity.send.SendInteresting;
import com.kamil.dinnerapp.entity.send.SendProfile;
import com.kamil.dinnerapp.entity.send.SendProfileBrief;

@Service
public class ProfileServiceImpl {

	private ProfileDAOImpl profileDao;
	
	@Autowired
	public ProfileServiceImpl(ProfileDAOImpl profileDao) {
		this.profileDao = profileDao;
		
	}
	
	@Transactional
	public Profile get(int id) {
		Profile profile = profileDao.get(id);
		return profile;
	}
		
	
	
	@Transactional
	public SendProfile getSendProfile(int id) {
		Profile profile = profileDao.get(id);
		//System.out.println("profile: " + profile.toString());
		Integer profileId = profile.getId();
	//	if (profile.getAge() != 0) {
			Integer age = profile.getAge();
		//}
		
		String gender = profile.getGender();
		String firstName = profile.getFirstName();
		String lastName = profile.getLastName();
		String cityName = "nie ustawiono";
		if(profile.getCity() != null) {
			 cityName = profile.getCity().getCity().getName();
		}
		String about = "nie ustawiono";
		if(profile.getAbout() != null) {
			 about = profile.getAbout();	
		}
			
		String street = "nie ustawiono";
		if(profile.getStreet() != null) {
			 street = profile.getStreet();
		}
		
		String latitude = null;
		if(profile.getLatitude() != null) {
			latitude = profile.getLatitude();
		}
		String longitude = null;
		 if(profile.getLongitude() != null) {
			 longitude = profile.getLongitude();
		 }
		
		 
		
		ArrayList<SendInteresting> listOfInterestings = new ArrayList<SendInteresting>();
		ArrayList<SendDietaryPreference> listOfDietaryPreferences = new ArrayList<SendDietaryPreference>();
		
		List<ProfileInteresting> profileInterestingsList = profile.getProfileInterestings();
		List<ProfileDietary> profileDietList = profile.getProfileDietaryPreferences();
		
		for(ProfileDietary diet: profileDietList) {
			
			SendDietaryPreference sendDietaryPreference = new SendDietaryPreference(diet.getDiet().getId(), diet.getDiet().getName());
			System.out.println(sendDietaryPreference.toString());
			listOfDietaryPreferences.add(sendDietaryPreference);
			
		}
		
		for(ProfileInteresting interest : profileInterestingsList) {
			SendInteresting sendInteresting = new SendInteresting(interest.getInteresting().getId(),interest.getInteresting().getName());
			System.out.println(sendInteresting.toString());
			listOfInterestings.add(sendInteresting);
		}
		
		
		
		SendProfile profileToSend = new SendProfile(profileId, age, gender, firstName, lastName, cityName, about, listOfInterestings, listOfDietaryPreferences);
		if(profile.getStreet() != null) {
			profileToSend.setStreet(street);
		}
		if(profile.getLongitude() != null) {
			profileToSend.setLongitude(longitude);
		}
		if(profile.getLatitude() != null) {
			profileToSend.setLatitude(latitude);
		}
		
		
		if(profile.getImage() != null) {
			String image = profile.getImage().getUrl();
			profileToSend.setImage(image);
			}
		
		return profileToSend;
	}
	@Transactional
	public Profile getProfileByUserId(Integer userId) {
		
		Profile profile = profileDao.getProfileByUserId(userId);
		return profile;
	}

	@Transactional
	public List<Profile> getAll() {
		List<Profile> profiles = profileDao.getAll();
		return profiles;
	}

	@Transactional
	public Profile save(Profile profile) {
		return profileDao.save(profile);
		
	}

	@Transactional
	public void delete(Integer profileId) {
		profileDao.delete(profileId);
		
	}
	
	@Transactional
	public Profile update(Profile profile) {
		System.out.println("Update Service");
		return profileDao.update(profile);
		
	}
	@Transactional
	public void updateAbout(SendAbout sendAbout) {
		
		Profile profile = profileDao.get(sendAbout.getProfileId());
		profile.setAbout(sendAbout.getAbout());
		
		profileDao.update(profile);
		
	}
	
	

}
