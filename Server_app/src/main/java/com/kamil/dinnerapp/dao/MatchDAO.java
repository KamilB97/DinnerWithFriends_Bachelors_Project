package com.kamil.dinnerapp.dao;

import java.util.List;

import com.kamil.dinnerapp.entity.Matched;
import com.kamil.dinnerapp.entity.MatchedId;

public interface MatchDAO<match>{
	
	//match get(MatchedPK pk); 
	List<match> getAllForId(int profileId);
	void save(match t); 
	void delete(MatchedId pk);

	
	

}
