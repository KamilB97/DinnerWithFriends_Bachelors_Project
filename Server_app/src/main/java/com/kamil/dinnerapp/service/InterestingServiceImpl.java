package com.kamil.dinnerapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.InterestingDAOImpl;
import com.kamil.dinnerapp.dao.StaticResourceDAO;
import com.kamil.dinnerapp.entity.Interesting;
@Service
public class InterestingServiceImpl implements StaticResourceService<Interesting, Integer> {
	
	private InterestingDAOImpl interestingDAO;
	
	@Autowired
	public InterestingServiceImpl(InterestingDAOImpl interestingDAO) {
		this.interestingDAO = interestingDAO;
	}

	@Override
	public Interesting get(Integer id) {
		
		return interestingDAO.get(id);
	}

	@Override
	public List<Interesting> getAll() {
		
		return interestingDAO.getAll();
	}
	@Transactional
	public Integer getSimilarInterestingsCondition(int baseProfileId, int targetProfileId) {
		
		int interestingsCondition = interestingDAO.getSimilarInterestingsCondition(baseProfileId, targetProfileId);
		return interestingsCondition;
		
	}

}
