package com.kamil.dinnerapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.MatchDAO;
import com.kamil.dinnerapp.dao.NotMatchedDAOImpl;
import com.kamil.dinnerapp.entity.MatchedId;
import com.kamil.dinnerapp.entity.NotMatched;

@Service
public class NotMatchedServiceImpl  {

private NotMatchedDAOImpl notMatchedDAO;
	
	private NotMatchedServiceImpl(NotMatchedDAOImpl notMatchedDAO) {
		this.notMatchedDAO = notMatchedDAO;
	}
	
	public NotMatched getNotMatched(Integer profile1Id, Integer profile2Id) {
		
			if(profile2Id > profile1Id) {
			
			Integer profileHolder = profile2Id;
			profile2Id = profile1Id;
			profile1Id = profileHolder;
			
		}
		
		NotMatched notMatched = notMatchedDAO.getNotMatched(profile1Id, profile2Id);
		return notMatched;
	}
	
	
	public List<NotMatched> getAllForId(int profileId) {
		List<NotMatched> list = notMatchedDAO.getAllForId(profileId);
		return list;
	}

	
	public void save(NotMatched match) {
		notMatchedDAO.save(match);
		
	}

	
	public void delete(MatchedId pk) {
		notMatchedDAO.delete(pk);
		
	}
	
}
