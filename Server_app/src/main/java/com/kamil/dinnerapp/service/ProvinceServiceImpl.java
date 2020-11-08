package com.kamil.dinnerapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.StaticResourceDAO;
import com.kamil.dinnerapp.entity.Province;
@Service
public class ProvinceServiceImpl implements StaticResourceService<Province, Integer> {

	private StaticResourceDAO<Province, Integer> provinceDAO;
	
	@Autowired
	public ProvinceServiceImpl(StaticResourceDAO<Province, Integer> provinceDao) {
		this.provinceDAO = provinceDao;
	}
	
	@Override
	@Transactional
	public Province get(Integer id) {
		Province province = provinceDAO.get(id);
		return province;
	}

	@Override
	@Transactional
	public List<Province> getAll() {
		List<Province> provinces = provinceDAO.getAll();
		return provinces;
	}
	
	

}
