package com.kamil.dinnerapp.service;

import java.util.List;

import com.kamil.dinnerapp.entity.MatchedId;
import com.kamil.dinnerapp.entity.send.SendMatched;

public interface MatchService<matchType> {
	
	List<SendMatched> getAllForId(int profileId);
	void save(matchType t); 
	void delete(MatchedId pk);

}
