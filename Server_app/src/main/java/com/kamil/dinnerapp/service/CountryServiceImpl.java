package com.kamil.dinnerapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.StaticResourceDAO;
import com.kamil.dinnerapp.entity.City;
import com.kamil.dinnerapp.entity.Country;
@Service
public class CountryServiceImpl implements StaticResourceService<Country, Integer> {

	private StaticResourceDAO<Country, Integer> countryDAO;
	@Autowired
	public CountryServiceImpl(StaticResourceDAO<Country, Integer> countryDAO) {
		this.countryDAO = countryDAO;
	
	}
	@Override
	@Transactional
	public Country get(Integer id) {
		Country country = countryDAO.get(id);
		return country;
	}
	@Override
	@Transactional
	public List<Country> getAll() {
		List<Country> countries = countryDAO.getAll();
		return countries;
	}
	
	
}
