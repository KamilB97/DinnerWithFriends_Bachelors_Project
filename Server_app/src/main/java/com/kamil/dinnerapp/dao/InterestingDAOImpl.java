package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Interesting;
@Repository
public class InterestingDAOImpl implements StaticResourceDAO<Interesting, Integer>{

	private EntityManager entityManager; 
	@Autowired
	public InterestingDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Interesting get(Integer id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Interesting.class, id);
		
	}

	@Override
	public List<Interesting> getAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Interesting> theQuery = currentSession.createQuery("from Interesting", Interesting.class);
		List<Interesting> interestings = theQuery.getResultList(); 	
	
		return interestings; 
	}
	public Integer getSimilarInterestingsCondition(int baseProfileId, int targetProfileId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Long> querryCountInt = currentSession.createQuery(
				  "SELECT count(*) "
				+ "from Profile p2 inner join p2.interestings i2 "
				+ "where (p2.id = :targetProfileId AND i2.id IN "
				+ "(select i1.id from Profile p1 inner join p1.interestings i1 "
				+ "where p1.id = :baseProfileId))");
		
		querryCountInt.setParameter("baseProfileId", baseProfileId);
		querryCountInt.setParameter("targetProfileId", targetProfileId);
		
		Long interestingCondition = querryCountInt.getSingleResult();
		Integer intInterestingCondition = interestingCondition != null ? interestingCondition.intValue() : null;

		System.out.println("interestings condition: "+ intInterestingCondition);
		return intInterestingCondition;
		
	}
	
	

}
