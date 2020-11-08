package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Country;

@Repository
public class CountryDAOImpl implements StaticResourceDAO<Country, Integer> {
	
private EntityManager entityManager;
	
	@Autowired
	public CountryDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	public Country get(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Country.class, id);
	}

	public List<Country> getAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Country> theQuery = currentSession.createQuery("from Country",Country.class);
		List<Country> countries = theQuery.getResultList();
		return countries;
	}

}
