package com.kamil.dinnerapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.SwipeDAOImpl;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.Swipe;
import com.kamil.dinnerapp.entity.jointable.ProfileDietary;
import com.kamil.dinnerapp.entity.jointable.ProfileInteresting;
import com.kamil.dinnerapp.entity.send.SendDietaryPreference;
import com.kamil.dinnerapp.entity.send.SendInteresting;
import com.kamil.dinnerapp.entity.send.SendProfile;

@Service
public class SwipeServiceImpl implements GenericService<Swipe, Integer> {

	private SwipeDAOImpl swipeDAO;
	private ProfileServiceImpl profileService;

	@Autowired
	public SwipeServiceImpl(SwipeDAOImpl swipeDAO, ProfileServiceImpl profileService) {
		this.swipeDAO = swipeDAO;
		this.profileService = profileService;
	}

	@Override
	@Transactional
	public Swipe get(Integer id) {
		Swipe swipe = swipeDAO.get(id);
		return swipe;
	}

	public Swipe get(Integer baseProfileId, Integer targetProfileId, Integer searchingProfileId) {

		if (targetProfileId > baseProfileId) {
			Integer targetHolder = targetProfileId;
			targetProfileId = baseProfileId;
			baseProfileId = targetHolder;
		}

		Swipe swipe = swipeDAO.get(baseProfileId, targetProfileId, searchingProfileId);
		return swipe;
	}

	@Transactional
	public List<Swipe> get(Integer baseProfileId, Integer targetProfileId) {

		if (targetProfileId > baseProfileId) {
			Integer targetHolder = targetProfileId;
			targetProfileId = baseProfileId;
			baseProfileId = targetHolder;
		}

		List<Swipe> swipeList = swipeDAO.get(baseProfileId, targetProfileId);

		return swipeList;

	}

	@Override
	@Transactional
	public List<Swipe> getAll() {
		List<Swipe> swipes = swipeDAO.getAll();
		return swipes;
	}

	@Override
	@Transactional
	public void save(Swipe swipe) {
		swipeDAO.save(swipe);

	}

	@Transactional
	public void update(Swipe swipe) {
		swipeDAO.update(swipe);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public void delete(Swipe swipe) {

		swipeDAO.delete(swipe);

	}

	@Transactional
	public void deleteBothSwipes(Integer baseProfileId, Integer targetProfileId) {

		if (targetProfileId > baseProfileId) {
			Integer targetHolder = targetProfileId;
			targetProfileId = baseProfileId;
			baseProfileId = targetHolder;
		}

		swipeDAO.deleteBothSwipes(baseProfileId, targetProfileId);
	}

	@Transactional
	public List<SendProfile> getFriendCandidatesList(Integer searchingProfileId) {

		System.out.println("searching profile id: " + searchingProfileId);
		
		List<Swipe> swipeList = swipeDAO.getFriendCandidatesList(searchingProfileId);
		//System.out.println("size of swipe List: " + swipeList.size());
		//System.out.println("swipe list: " + swipeList.toString());
		List<SendProfile> candidateList = new ArrayList<SendProfile>();

		// if profile.getId == baseProfile pobierz targetProfile
		// if profile.getId == targetProfile pobierz base

		for (int i = 0; i < swipeList.size(); i ++) {
			
			System.out.println("swipeList looping i= " + i);
			
			Swipe swipe = swipeList.get(i);
			Integer requestingProfileId = swipe.getProfile().getId();

			Profile candidateProfile = new Profile();

			if (requestingProfileId == swipe.getConnection().getBaseProfileId()) {

				Integer candidateId = swipe.getConnection().getTargetProfileId();
				candidateProfile = profileService.get(candidateId);
				System.out.println("in if " + candidateProfile.getId() + " " + candidateProfile.getFirstName());
			} else if (requestingProfileId == swipe.getConnection().getTargetProfileId()) {
				Integer candidateId = swipe.getConnection().getBaseProfileId();
				candidateProfile = profileService.get(candidateId);
				System.out.println("in else if " + candidateProfile.getId() + " " + candidateProfile.getFirstName());
			}

			ArrayList<SendInteresting> listOfInterestings = new ArrayList<SendInteresting>();
			ArrayList<SendDietaryPreference> listOfDietaryPreferences = new ArrayList<SendDietaryPreference>();
			
			System.out.println("Cnadidate Profile ID: " + candidateProfile.getId());
			
			List<ProfileInteresting> profileInterestingsList = candidateProfile.getProfileInterestings();
			//System.out.println(profileInterestingsList.get(0).getId());
			//System.out.println(profileInterestingsList.get(0).getInteresting().getName());
			List<ProfileDietary> profileDietaryPreferencesList = candidateProfile.getProfileDietaryPreferences();
			//System.out.println(profileDietaryPreferencesList.get(0).getId());
			//System.out.println(profileDietaryPreferencesList.get(0).getDiet().getName());
			
			for (ProfileDietary diet : profileDietaryPreferencesList) {

				SendDietaryPreference sendDietaryPreference = new SendDietaryPreference(diet.getDiet().getId(),
						diet.getDiet().getName());
			//	 System.out.println("send dietary p: " + sendDietaryPreference.toString());
				listOfDietaryPreferences.add(sendDietaryPreference);

			}

			for (ProfileInteresting interest : profileInterestingsList) {
				SendInteresting sendInteresting = new SendInteresting(interest.getInteresting().getId(),
						interest.getInteresting().getName());
			//	 System.out.println("send interestings: " + sendInteresting.toString());
				listOfInterestings.add(sendInteresting);
			}

			Integer profileId = candidateProfile.getId();
			System.out.println(profileId);
			Integer age = candidateProfile.getAge();
			System.out.println(age);
			String gender = candidateProfile.getGender();
			System.out.println(gender);
			String firstName = candidateProfile.getFirstName();
			System.out.println(firstName);
			String lastName = candidateProfile.getLastName();
			System.out.println(lastName);
			String cityName = null;
			
			
			if(candidateProfile.getCity() != null) {
			 cityName = candidateProfile.getCity().getCity().getName();
			
			}else {
				cityName = "Wrocław";
				System.out.println(cityName);
			}
			String about = candidateProfile.getAbout();
			System.out.println(about);
			SendProfile sendProfile = new SendProfile(profileId, age, gender, firstName, lastName, cityName, about,
					listOfInterestings, listOfDietaryPreferences);			
			
			if(candidateProfile.getImage() != null) {
				
			String imageURL = candidateProfile.getImage().getUrl();
			sendProfile.setImage(imageURL);
			}
			
			System.out.println("adding candidate to list");
			System.out.println(sendProfile.toString());
			
			candidateList.add(sendProfile);
			
			
		}
		
		for (SendProfile sendProfile : candidateList) {
			System.out.println(sendProfile.getFirstName() + " " + sendProfile.getLastName() + " " + sendProfile.getGender());
		}
		
		return candidateList;

	}

}
