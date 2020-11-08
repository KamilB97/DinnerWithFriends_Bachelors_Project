package com.kamil.dinnerapp.service.jointable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.jointable.ProfileInterestingDAOImpl;
import com.kamil.dinnerapp.entity.jointable.ProfileInteresting;

@Service
public class ProfileInterestingServiceImpl {
	
	private ProfileInterestingDAOImpl profileInterestingDAO;
	
	@Autowired
	public ProfileInterestingServiceImpl(ProfileInterestingDAOImpl profileInterestingDAO) {
		this.profileInterestingDAO = profileInterestingDAO;
	
	}
	@Transactional
	public void addRelationProfileInteresting(ProfileInteresting profileInteresting) {
		
		profileInterestingDAO.addRelationProfileInteresting(profileInteresting);
			
	}
	@Transactional
	public void deleteRelationsByProfileId(Integer profileId) {
		profileInterestingDAO.deleteRelationsByProfileId(profileId);
	}
	
	@Transactional
	public Integer getSimilarInterestingsCondition(int baseProfileId, int targetProfileId) {
		Integer condition = profileInterestingDAO.getSimilarInterestingsCondition(baseProfileId, targetProfileId);
		return condition;

	}
	

}
