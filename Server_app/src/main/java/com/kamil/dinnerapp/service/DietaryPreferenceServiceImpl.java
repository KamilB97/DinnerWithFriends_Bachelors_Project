package com.kamil.dinnerapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.DietaryPreferenceDAOImpl;
import com.kamil.dinnerapp.dao.StaticResourceDAO;
import com.kamil.dinnerapp.entity.DietaryPreference;

@Service
public class DietaryPreferenceServiceImpl implements StaticResourceService<DietaryPreference, Integer> {
	
	private DietaryPreferenceDAOImpl dietaryPreferencesDAO;
	
	public DietaryPreferenceServiceImpl(DietaryPreferenceDAOImpl dietaryPreferencesDAO) {
		this.dietaryPreferencesDAO = dietaryPreferencesDAO;
	}

	@Override
	public DietaryPreference get(Integer id) {
		return dietaryPreferencesDAO.get(id);
	}

	@Override
	public List<DietaryPreference> getAll() {
		return dietaryPreferencesDAO.getAll();
	}
	
	@Transactional
	public Integer getSimilarDietaryCondition(int baseProfileId, int targetProfileId) {
		
		int dietCondition = dietaryPreferencesDAO.getSimilarDietaryCondition(baseProfileId, targetProfileId);
		return dietCondition;
	}
	

}
