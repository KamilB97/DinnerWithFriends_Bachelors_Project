package com.kamil.dinnerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamil.dinnerapp.entity.City;
import com.kamil.dinnerapp.entity.Country;
import com.kamil.dinnerapp.entity.DietaryPreference;
import com.kamil.dinnerapp.entity.Interesting;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.Province;
import com.kamil.dinnerapp.entity.UserConnection;
import com.kamil.dinnerapp.entity.jointable.ProfileCity;
import com.kamil.dinnerapp.service.CityServiceImpl;
import com.kamil.dinnerapp.service.DietaryPreferenceServiceImpl;
import com.kamil.dinnerapp.service.InterestingServiceImpl;
import com.kamil.dinnerapp.service.MatchingServiceImpl;
import com.kamil.dinnerapp.service.StaticResourceService;
import com.kamil.dinnerapp.service.UserConnectionServiceImpl;
import com.kamil.dinnerapp.service.jointable.ProfileDietaryServiceImpl;
import com.kamil.dinnerapp.service.jointable.ProfileInterestingServiceImpl;

@RestController
@RequestMapping("/api")
public class StaticResourcesController {
	
	private CityServiceImpl cityService;
	private StaticResourceService<Province, Integer> provinceService;
	private StaticResourceService<Country, Integer> countryService;
	private InterestingServiceImpl interestingService;
	private DietaryPreferenceServiceImpl dietaryPreferenceService;
	private UserConnectionServiceImpl userConnectionService;
	private MatchingServiceImpl matchingService;
	private ProfileInterestingServiceImpl profileInterestingService;
	private ProfileDietaryServiceImpl profileDietaryService;
	
	
	@Autowired
	public StaticResourcesController(
			CityServiceImpl cityService,
			StaticResourceService<Province, Integer> provinceService, 
			StaticResourceService<Country, Integer> countryService,
			InterestingServiceImpl interestingService, 
			DietaryPreferenceServiceImpl dietaryPreferenceService,
			UserConnectionServiceImpl userConnectionService,
			MatchingServiceImpl matchingService,
			ProfileInterestingServiceImpl profileInterestingService,
			ProfileDietaryServiceImpl profileDietaryService) 
	{	
		this.cityService = cityService;
		this.provinceService = provinceService;
		this.countryService = countryService;
		this.interestingService = interestingService;
		this.dietaryPreferenceService = dietaryPreferenceService;
		this.userConnectionService = userConnectionService;
		this.matchingService = matchingService;
		this.profileInterestingService =profileInterestingService;
		this.profileDietaryService = profileDietaryService;
	}



	@GetMapping("/cities")
	public List<City> getCities(){
		return cityService.getAll();
		
	}
	@GetMapping("/provinces/{provinceId}")
	public List<City> getCitiesForProvince(@PathVariable int provinceId){
		return cityService.getCitiesForProvince(provinceId);
	}
	
	@GetMapping("/cities/id")
	public List<Integer> getCitiesIds(){
		return cityService.getAllCityId();
		
	}
	@GetMapping("/cities/{id}")
	public City getCityById(@PathVariable int id){
		return cityService.get(id);
		
	}
	
	@GetMapping("/provinces")
	public List<Province> getProvinces(){
		return provinceService.getAll();
		
	}
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("/countries")
	public List<Country> getCountries(){
		
		return countryService.getAll();
		
	}
	@GetMapping("/interestings")
	public List<Interesting> getInterestings(){
		return interestingService.getAll();
	}
	@GetMapping("/dietaryPreferences")
	public List<DietaryPreference> getDietaryPreferences(){
		return dietaryPreferenceService.getAll();
	}
	
	
	@GetMapping("/profile/interestings/{profileId}")
	public void deleteProfileInterestingsRelationship(@PathVariable Integer profileId){
		 profileInterestingService.deleteRelationsByProfileId(profileId);
	}
	@GetMapping("/profile/dietaryPreferences/{profileId}")
	public void deleteProfileDietaryPreferencesRelationship(@PathVariable Integer profileId){
		 profileDietaryService.deleteRelationsByProfileId(profileId);
	}
	
	
	@GetMapping("/city/profiles/{cityId}/{profileId}")
	public List<Integer> getProfilesForCItyId(@PathVariable int cityId, @PathVariable int profileId){
		
		return cityService.getProfilesForCity(cityId, profileId);
		
	}
	@GetMapping("/interestings/{baseProfileId}/{targetProfileId}")
	public int getSimilarInterestingsCondition(@PathVariable int baseProfileId, @PathVariable int targetProfileId) {
		
		return interestingService.getSimilarInterestingsCondition(baseProfileId, targetProfileId);
		
	}
	
	@GetMapping("/dietaryPreferences/{baseProfileId}/{targetProfileId}")
	public int getSimilarDietaryCondition(@PathVariable int baseProfileId, @PathVariable int targetProfileId) {
		
		Integer condition = dietaryPreferenceService.getSimilarDietaryCondition(baseProfileId, targetProfileId);
	//userConnectionService.specialSave(baseProfileId, targetProfileId,condition);
		return condition;
		
	}

	
	
	
	
	
	
	
}
