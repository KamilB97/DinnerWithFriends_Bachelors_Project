package com.kamil.dinnerapp.service.jointable;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.jointable.ProfileCityDAOImpl;
import com.kamil.dinnerapp.entity.jointable.ProfileCity;

@Service
public class ProfileCityServiceImpl {
	
	private ProfileCityDAOImpl profileCityDAO;
	
	@Autowired
	public ProfileCityServiceImpl(ProfileCityDAOImpl profileCityDAO) {
		this.profileCityDAO = profileCityDAO;
	
	}
	@Transactional
	public void addRelationProfileCity(ProfileCity profileCity) {
		
		profileCityDAO.addRelationProfileCity(profileCity);
			
	}
	
	@Transactional
	public void deleteRelationProfileCity(ProfileCity profileCity) {
		
		profileCityDAO.deleteRelationProfileCity(profileCity);
			
	}
	@Transactional
	public void updateRelationProfileCity(ProfileCity profileCity) {
		profileCityDAO.updateRelationProfileCity(profileCity);
			
	}
	@Transactional
	public ProfileCity get(Integer profileId) {
		
	ProfileCity pc = profileCityDAO.get(profileId);
	if(pc != null) {
		System.out.println(pc.getCity().getName() +" "+ pc.getProfile());	
	}
	
		return pc;
				
		}

}
