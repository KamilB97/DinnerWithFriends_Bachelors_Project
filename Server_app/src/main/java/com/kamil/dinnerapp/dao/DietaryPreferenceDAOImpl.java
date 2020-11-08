package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.DietaryPreference;
@Repository
public class DietaryPreferenceDAOImpl implements StaticResourceDAO<DietaryPreference, Integer> {
	
	
	private EntityManager entityManager;
	
	@Autowired
	public DietaryPreferenceDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public DietaryPreference get(Integer id) {
		Session  currentSession = entityManager.unwrap(Session.class);
		
		
		return currentSession.get(DietaryPreference.class, id);
	}

	@Override
	public List<DietaryPreference> getAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<DietaryPreference> theQuery= currentSession.createQuery("from DietaryPreference", DietaryPreference.class);
		List<DietaryPreference> dietaryPreferences = theQuery.getResultList();
		
		return dietaryPreferences;
	}
	
	public Integer getSimilarDietaryCondition(int baseProfileId, int targetProfileId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
	Query<Long> querryCountDiet = currentSession.createQuery(
			"SELECT count(*) "
			+ "from Profile p4 inner join p4.dietaryPreferences d2 "
			+ "where (p4.id = :targetProfileId AND d2.id IN "
			+ "(select d1.id from Profile p3 inner join p3.dietaryPreferences d1 "
			+ "where p3.id = :baseProfileId))");
				
	querryCountDiet.setParameter("baseProfileId", baseProfileId);
	querryCountDiet.setParameter("targetProfileId", targetProfileId);

		Long DietCondition = querryCountDiet.getSingleResult();
		Integer intDietCondition = DietCondition != null ? DietCondition.intValue() : null;
		System.out.println("count dietary target: "+intDietCondition);	
		
		return intDietCondition;
	
	}

}
