package com.kamil.dinnerapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.MatchDAO;
import com.kamil.dinnerapp.dao.MatchedDAOImpl;
import com.kamil.dinnerapp.entity.Matched;
import com.kamil.dinnerapp.entity.MatchedId;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.send.SendMatched;
import com.kamil.dinnerapp.entity.send.SendProfileBrief;

@Service
public class MatchedServiceImpl implements MatchService<Matched> {

	private MatchedDAOImpl matchedDAO;
	private ProfileServiceImpl profileService;
	
	@Autowired
	public MatchedServiceImpl(MatchedDAOImpl matchedDAO, ProfileServiceImpl profileService) {
		this.matchedDAO = matchedDAO;
		this.profileService = profileService;
	}
	
	@Override
	@Transactional
	public List<SendMatched> getAllForId(int profileId) {
		List<Matched> matchedList = matchedDAO.getAllForId(profileId);
		List<SendMatched> sendMatchedList = new ArrayList<SendMatched>();
		
		for( Matched matched : matchedList) {
			
			SendProfileBrief profile = new SendProfileBrief();
			
			if(matched.getProfile1() != profileId) {
				Integer profile1Id = matched.getProfile1();
				Profile profile1Obj = profileService.get(profile1Id);
				String name1 = profile1Obj.getFirstName();
				String surname1 = profile1Obj.getLastName();
				profile = new SendProfileBrief(profile1Id, name1, surname1);
				
				if(profile1Obj.getImage() != null) {
					profile.setImage(profile1Obj.getImage().getUrl());
				}
			}
			if(matched.getProfile2() != profileId) {
				Integer profile2Id = matched.getProfile2();
				Profile profile2Obj = profileService.get(profile2Id);
				String name2 = profile2Obj.getFirstName();
				String surname2 = profile2Obj.getLastName();
				profile = new SendProfileBrief(profile2Id, name2, surname2);
				if(profile2Obj.getImage() != null) {
					profile.setImage(profile2Obj.getImage().getUrl());
				}
			}
			Integer conversationId = matched.getConversation().getId();
			
			SendMatched sendMatched = new SendMatched(profile, conversationId);
			
			sendMatchedList.add(sendMatched);
			System.out.println(sendMatched.toString());	
		}
		
		
		return sendMatchedList;
	}
	@Transactional
	public Matched getMatch(Integer profile1Id, Integer profile2Id) {
		
		if(profile2Id > profile1Id) {
			
			Integer profileHolder = profile2Id;
			profile2Id = profile1Id;
			profile1Id = profileHolder;
			
		}
		
		Matched match = matchedDAO.getMatch(profile1Id, profile2Id);
		return match;
	}
	

	@Override
	@Transactional
	public void save(Matched match) {
		matchedDAO.save(match);
		
	}

	@Override
	@Transactional
	public void delete(MatchedId pk) {
		matchedDAO.delete(pk);
		
	}
	
	
	

}
