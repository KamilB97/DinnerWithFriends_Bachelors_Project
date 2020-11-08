package com.kamil.dinnerapp.service.jointable;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.jointable.ProfileDietaryDAOImpl;
import com.kamil.dinnerapp.entity.jointable.ProfileDietary;
import com.kamil.dinnerapp.entity.jointable.ProfileInteresting;

@Service
public class ProfileDietaryServiceImpl {

	private ProfileDietaryDAOImpl profileDietaryDAO;

	@Autowired
	public ProfileDietaryServiceImpl(ProfileDietaryDAOImpl profileDietaryDAO) {
		this.profileDietaryDAO = profileDietaryDAO;

	}
	
	@Transactional
	public void addRelationProfileDietary(ProfileDietary profileDietary) {

		profileDietaryDAO.addRelationProfileDietary(profileDietary);

	}
	
	@Transactional
	public void deleteRelationsByProfileId(Integer profileId) {
		profileDietaryDAO.deleteRelationsByProfileId(profileId);
	}

	@Transactional
	public Integer getSimilarDietaryCondition(int baseProfileId, int targetProfileId) {
		Integer condition = profileDietaryDAO.getSimilarDietaryCondition(baseProfileId, targetProfileId);
		return condition;

	}
}
