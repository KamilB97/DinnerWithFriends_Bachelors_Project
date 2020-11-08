package com.kamil.dinnerapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.kamil.dinnerapp.entity.Matched;
import com.kamil.dinnerapp.entity.NotMatched;
import com.kamil.dinnerapp.entity.Swipe;
import com.kamil.dinnerapp.entity.UserConnection;
import com.kamil.dinnerapp.entity.send.SendMatched;
import com.kamil.dinnerapp.entity.send.SendProfile;
import com.kamil.dinnerapp.service.GenericService;
import com.kamil.dinnerapp.service.MatchService;
import com.kamil.dinnerapp.service.MatchingServiceImpl;
import com.kamil.dinnerapp.service.NotMatchedServiceImpl;
import com.kamil.dinnerapp.service.SwipeServiceImpl;

@RestController
@RequestMapping("/api/matcher")
@EnableWebMvc
public class MatcherRestController {
	
	private GenericService<UserConnection, Integer> connectionService;
	private SwipeServiceImpl swipeService;
	private MatchService<Matched> matchedService;
	private NotMatchedServiceImpl notMatchedService;
	private MatchingServiceImpl matchingService;
	@Autowired
	public MatcherRestController(GenericService<UserConnection, Integer> connectionService, SwipeServiceImpl swipeService,
			MatchService<Matched> matchedService, NotMatchedServiceImpl  notMatchedService, MatchingServiceImpl matchingService ) {
		this.connectionService = connectionService;
		this.swipeService = swipeService;
		this.matchedService = matchedService;
		this.notMatchedService = notMatchedService;
		this.matchingService = matchingService;
	}
	
	
	@GetMapping("/connections") // działa
	public List<UserConnection> getAllMatches(){
		return connectionService.getAll();
	}
	@GetMapping("/swipes") // działa
	public List<Swipe> getAllSwipes(){
		return swipeService.getAll();
	}
	@GetMapping("/matches/{profileId}") // działa 
	public List<SendMatched> getMatchesForId(@PathVariable int profileId){
		
		return matchedService.getAllForId(profileId);
	}
	
	@GetMapping("/notmatches/{profileId}") // działa
	public List<NotMatched> getNotMatchesForId(@PathVariable int profileId){
		
		return notMatchedService.getAllForId(profileId);
	}
	
	@GetMapping("/updateswipe/{id}/{baseProfileId}/{targetProfileId}/{liked}") // działa
	public Map<String, String> updateSwipe(@PathVariable int id,@PathVariable int baseProfileId, @PathVariable int targetProfileId,
			@PathVariable int liked) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		
		
		String date = "2019-08-31 20:00:00";
		try {
			matchingService.doSwipe(id, baseProfileId, targetProfileId, liked, date);
			map.put("response", "successfull");
			return map;
		} catch (Exception e) {
			throw new Exception("Server error");
		}
		
	}
	
	@GetMapping("/matchingwork/{profileId}/{cityId}") // działa 
	public int  doMatchingWork(@PathVariable Integer profileId, @PathVariable Integer cityId){
		System.out.println("STARTING DO MATCHING WORK");
		 return matchingService.doMatchingWork(profileId, cityId);
		
	}
	
	@GetMapping("/candidates/{profileId}") // działa
	public List<SendProfile> getCandidateList(@PathVariable Integer profileId) {
	
		
		return swipeService.getFriendCandidatesList(profileId);
	}
}
