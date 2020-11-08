package com.kamil.dinnerapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.CityDAOImpl;
import com.kamil.dinnerapp.dao.StaticResourceDAO;
import com.kamil.dinnerapp.entity.City;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.jointable.ProfileCity;
@Service
public class CityServiceImpl implements StaticResourceService<City, Integer> {
	
	private CityDAOImpl cityDAO;
	@Autowired
	public CityServiceImpl(CityDAOImpl cityDAO) {
		this.cityDAO = cityDAO;
	
	}
	@Override
	@Transactional
	public City get(Integer id) {
		System.out.println("w city service");
		City city = cityDAO.get(id);
		return city;
	}
	@Override
	@Transactional
	public List<City> getAll() {
		List<City> cities = cityDAO.getAll();
		return cities;
	}
	@Transactional
	public List<Integer> getProfilesForCity(int cityId, int profileId){
		//cityDAO.getProfilesForCity(cityId, profileId);
		return cityDAO.getProfilesForCity(cityId, profileId);
	}
	
	@Transactional
	public List<Integer> getProfilesForCity(int cityId){
		return cityDAO.getProfilesForCity(cityId);
	}
	
	
	@Transactional
	public Integer getCityIdByName(String cityName) {
		
		Integer cityId = cityDAO.getCityIdByName(cityName);
		return cityId;
		
		}
	@Transactional
	public List<Integer> getAllCityId(){
		List<Integer> cityIdList = cityDAO.getAllCityId();
		
		for (Integer city : cityIdList) {
			System.out.println("cityId = " + city);
		}
		return cityIdList;
	}
	@Transactional
	public List<City> getCitiesForProvince(Integer provinceId){
		List<City> cityList = cityDAO.getCitiesForProvince(provinceId);
		return cityList;
	}
	
	

}
