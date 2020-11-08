package com.kamil.dinnerapp.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kamil.dinnerapp.service.CityServiceImpl;
import com.kamil.dinnerapp.service.MatchingServiceImpl;

@Component
public class MatchUsersSchedule {

	private MatchingServiceImpl matchingService;
	private CityServiceImpl cityService;

	@Autowired
	public MatchUsersSchedule(MatchingServiceImpl matchingService, CityServiceImpl cityService) {

		this.matchingService = matchingService;
		this.cityService = cityService;

	}

	// 5s - 5000 60s 60000
	@Scheduled(fixedDelay = 600000)
	public void matchUsers() {

		List<Integer> cityList = cityService.getAllCityId();

		System.out.println("PRACA SERWERA: MATCHOWANIE: ");

		for (Integer cityId : cityList) {

			System.out.println("City: " + cityId);

			List<Integer> profilesList = cityService.getProfilesForCity(cityId);

			for (Integer profileId : profilesList) {

				System.out.println("profile: " + profileId);

				matchingService.doMatchingWork(profileId, cityId);

			}

		}

	}

}
