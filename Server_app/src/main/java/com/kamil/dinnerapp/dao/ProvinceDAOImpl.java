package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Province;
@Repository
public class ProvinceDAOImpl implements StaticResourceDAO<Province, Integer> {
	
	private EntityManager entityManager;
	
	@Autowired
	public ProvinceDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	public Province get(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Province.class, id);
	}

	public List<Province> getAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Province> theQuery = currentSession.createQuery("from Province",Province.class);
		List<Province> provinces = theQuery.getResultList();
		return provinces;
	}

}
