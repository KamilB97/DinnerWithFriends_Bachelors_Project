package com.kamil.dinnerapp.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.entity.City;
import com.kamil.dinnerapp.entity.DietaryPreference;
import com.kamil.dinnerapp.entity.Interesting;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.SendProfile;
import com.kamil.dinnerapp.entity.User;
import com.kamil.dinnerapp.entity.jointable.ProfileCity;
import com.kamil.dinnerapp.entity.jointable.ProfileDietary;
import com.kamil.dinnerapp.entity.jointable.ProfileInteresting;
import com.kamil.dinnerapp.entity.send.SendDietaryPreference;
import com.kamil.dinnerapp.entity.send.SendInteresting;
import com.kamil.dinnerapp.service.jointable.ProfileCityServiceImpl;
import com.kamil.dinnerapp.service.jointable.ProfileDietaryServiceImpl;
import com.kamil.dinnerapp.service.jointable.ProfileInterestingServiceImpl;

@Service
public class CreateAndUpdateProfileServiceImpl {

	private ProfileServiceImpl profileService;
	private InterestingServiceImpl interestingService;
	private DietaryPreferenceServiceImpl dietaryService;
	private UserServiceImpl userService;
	private CityServiceImpl cityService;
	private ProfileCityServiceImpl profileCityService;
	private ProfileInterestingServiceImpl profileInterestingService;
	private ProfileDietaryServiceImpl profileDietaryService;
	private MatchingServiceImpl matchingService;

	@Autowired
	public CreateAndUpdateProfileServiceImpl(
			ProfileServiceImpl profileService,
			InterestingServiceImpl interestingService,
			DietaryPreferenceServiceImpl dietaryService,
			UserServiceImpl userService, CityServiceImpl cityService,
			ProfileCityServiceImpl profileCityService,
			ProfileInterestingServiceImpl profileInterestingService,
			ProfileDietaryServiceImpl profileDietaryService,
			MatchingServiceImpl matchingService) {

		this.profileService = profileService;
		this.interestingService = interestingService;
		this.dietaryService = dietaryService;
		this.userService = userService;
		this.cityService = cityService;
		this.profileCityService = profileCityService;
		this.profileInterestingService = profileInterestingService;
		this.profileDietaryService = profileDietaryService;
		this.matchingService = matchingService;
	}

	public Profile CreateProfile(SendProfile sendProfile) {

		System.out.println(sendProfile.toString());

		Profile profile = new Profile();

		User user = userService.get(sendProfile.getUserId());
		profile.setUser(user);

		if (sendProfile.getFirstName() != null) {
			profile.setFirstName(sendProfile.getFirstName());
		}
				
		if (sendProfile.getLastName() != null) {
			profile.setLastName(sendProfile.getLastName());
		}
		
		if (sendProfile.getGender() != null) {
			profile.setGender(sendProfile.getGender());
		}

		if (sendProfile.getAge() != null) {
			profile.setAge(sendProfile.getAge());
		}
		if (sendProfile.getAbout() != null) {
			profile.setAbout(sendProfile.getAbout());

		}

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("timestamp from profile " + timestamp);
		String date = timestamp.toString();
		System.out.println("update date: " + date);
		profile.setUpdateDate(date);
		System.out.println("profile update date: " + profile.getUpdateDate());
		profile.setStatus(2);

		profile = profileService.save(profile);
		System.out.println("save profile id: " + profile.getId());

		// dodawanie interestings dietary i city

		if (sendProfile.getCityId() != null) {
			System.out.println("DODAWANIE RELACJI CITY");
			City city = cityService.get(sendProfile.getCityId());
			ProfileCity pc = new ProfileCity(profile, city);
			profileCityService.addRelationProfileCity(pc);

//			profileCity.setProfile(profile.getId());
//			profileCity.setCity(sendProfile.getCityId());

			// profileCityService.addRelationProfileCity(profileCity);
		}

		if (sendProfile.getInterestings().length != 0) {
			System.out.println("DODAWANIE RELACJI INTERESTING");
			
			for (Integer interestingId : sendProfile.getInterestings()) {

				System.out.println("profileId: " + profile.getId() + " interestingId: " + interestingId);

				ProfileInteresting profileInteresting = new ProfileInteresting();
				Interesting interesting = interestingService.get(interestingId);
				profileInteresting.setProfile(profile);
				profileInteresting.setInteresting(interesting);

				profileInterestingService.addRelationProfileInteresting(profileInteresting);

			}
		}
		if (sendProfile.getDietary().length != 0) {
			System.out.println("DODAWANIE RELACJI DIETARY");
			
			for (Integer dietaryId : sendProfile.getDietary()) {

				System.out.println("profileId: " + profile.getId() + " dietaryId : " + dietaryId);

				ProfileDietary profileDietary = new ProfileDietary();
				DietaryPreference dietary = dietaryService.get(dietaryId);
				System.out.println("ustawianie dietary w profileDietary");
				profileDietary.setProfile(profile);
				profileDietary.setDiet(dietary);

				profileDietaryService.addRelationProfileDietary(profileDietary);

//				DietaryPreference dietary = dietaryService.get(dietaryId);
//				profile.addDietary(dietary);

			}
		}

		return profileService.update(profile);

	}
	
	public Profile UpdateProfile(SendProfile sendProfile) {
		System.out.println(" przed nullem: ");
		System.out.println(sendProfile.toString());
		Profile profile = profileService.get(sendProfile.getProfileId());
		profile.setId(sendProfile.getProfileId());

		User user = userService.get(sendProfile.getUserId());
		
		profile.setUser(user);
		
		if(sendProfile.getFirstName() != null){
		profile.setFirstName(sendProfile.getFirstName());
		}
		if(sendProfile.getLastName() != null) {
		profile.setLastName(sendProfile.getLastName());
		}
		if(sendProfile.getGender() != null) {
		profile.setGender(sendProfile.getGender());
		}

		if (sendProfile.getAge() != null) {
			profile.setAge(sendProfile.getAge());
		}
		if (sendProfile.getAbout() != null) {
			profile.setAbout(sendProfile.getAbout());

		}
		
		if(sendProfile.getStreet() != null) {
			String street = sendProfile.getStreet();
			profile.setStreet(street);
			
		}
		
		if(sendProfile.getLatitude() != null) {
			String latitude = sendProfile.getLatitude();
			profile.setLatitude(latitude);
		}
		;
		 if(sendProfile.getLongitude() != null) {
			 String longitude = sendProfile.getLongitude();
			 profile.setLongitude(longitude);
		 }
		

		 profile = profileService.update(profile);

		if (sendProfile.getCityId() != null) {
			System.out.println("przed city");
			City city = cityService.get(sendProfile.getCityId());
			System.out.println("po city");
			
			ProfileCity pc = profileCityService.get(sendProfile.getProfileId());
			if(pc != null) {
				System.out.println("po pobraniu pc");
				System.out.println(pc.getCity().getName());
				profileCityService.deleteRelationProfileCity(pc);
			}
			System.out.println("po delete w city pętli");
			
			pc = new ProfileCity(profile, city);
			profileCityService.addRelationProfileCity(pc);
		}
		System.out.println("po warunku city");
		if (sendProfile.getInterestings() != null && sendProfile.getInterestings().length != 0   ) {
			profileInterestingService.deleteRelationsByProfileId(profile.getId()); // usuwanie starych relacji profil - interestings
			for (Integer interestingId : sendProfile.getInterestings()) {

				ProfileInteresting profileInteresting = new ProfileInteresting();
				Interesting interesting = interestingService.get(interestingId);
				profileInteresting.setProfile(profile);
				profileInteresting.setInteresting(interesting);

				profileInterestingService.addRelationProfileInteresting(profileInteresting);
			}
		}
		if (sendProfile.getDietary() != null && sendProfile.getDietary().length != 0) {
			profileDietaryService.deleteRelationsByProfileId(profile.getId()); // usuwanie starych relacji profil - dietaryPreferences
			for (Integer dietaryId : sendProfile.getDietary()) {

				ProfileDietary profileDietary = new ProfileDietary();
				DietaryPreference dietary = dietaryService.get(dietaryId);
				profileDietary.setProfile(profile);
				profileDietary.setDiet(dietary);

				profileDietaryService.addRelationProfileDietary(profileDietary);

			}
		}

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("timestamp from profile " + timestamp);
		String date = timestamp.toString();

		profile.setUpdateDate(date);
		profile.setStatus(2);
		profile = profileService.update(profile);
		
		if (sendProfile.getCityId() != null) {
		matchingService.doMatchingWork(profile.getId(), sendProfile.getCityId());
		}
		return profile;

	}
	

//	public Profile UpdateProfile(com.kamil.dinnerapp.entity.send.SendProfile sendProfile) {
//		System.out.println("W update Method");
//		Profile profile = new Profile();
//		profile.setId(sendProfile.getProfileId());
//		System.out.println("1");
//		User user = userService.getUserByProfileId(sendProfile.getProfileId());
//		profile.setUser(user);
//
//		profile.setFirstName(sendProfile.getFirstName());
//		profile.setLastName(sendProfile.getLastName());
//		profile.setGender(sendProfile.getGender());
//		System.out.println("2");
//		if (sendProfile.getAge() != null) {
//			profile.setAge(sendProfile.getAge());
//			System.out.println("2.1");
//		}
//		if (sendProfile.getAbout() != null) {
//			profile.setAbout(sendProfile.getAbout());
//			System.out.println("2.2");
//		}
//
//		// profile = profileService.update(profile);
//		System.out.println("2.3");
//		if (sendProfile.getCityName() != null) {
//			System.out.println("2.4");
//			//Integer cityId = cityService.getCityIdByName(sendProfile.getCityName());
//			//System.out.println("2.5");
//			City city = cityService.get(sendProfile.getCityId());
//			System.out.println("2.5");
//			ProfileCity pc = new ProfileCity(profile, city);
//			System.out.println("2.6");
//			profileCityService.addRelationProfileCity(pc);
//			System.out.println("3");
//		}
//
//		if (sendProfile.getListOfInterestings().size() != 0) {
//			System.out.println("4");
//			for (SendInteresting interestings : sendProfile.getListOfInterestings()) {
//
//				ProfileInteresting profileInteresting = new ProfileInteresting();
//				Interesting interesting = interestingService.get(interestings.getId());
//				profileInteresting.setProfile(profile);
//				profileInteresting.setInteresting(interesting);
//
//				profileInterestingService.addRelationProfileInteresting(profileInteresting);
//				System.out.println("5");
//			}
//		}
//		if (sendProfile.getListOfDietaryPreferences().size() != 0) {
//			System.out.println("6");
//			for (SendDietaryPreference preferences : sendProfile.getListOfDietaryPreferences()) {
//
//				ProfileDietary profileDietary = new ProfileDietary();
//				DietaryPreference dietary = dietaryService.get(preferences.getId());
//				profileDietary.setProfile(profile);
//				profileDietary.setDiet(dietary);
//
//				profileDietaryService.addRelationProfileDietary(profileDietary);
//				System.out.println("7");
//			}
//		}
//		System.out.println("8");
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		System.out.println("timestamp from profile " + timestamp);
//		String date = timestamp.toString();
//		System.out.println("9");
//		profile.setUpdateDate(date);
//		System.out.println("10");
//		profile.setStatus(2);
//		System.out.println("11");
//		profile = profileService.update(profile);
//		System.out.println("12");
//		return profile;
//
//	}

}
