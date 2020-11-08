package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Profile;

@Repository
public class ProfileDAOImpl {

private EntityManager entityManager;
	
	@Autowired
	public ProfileDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	
	public Profile get(Integer id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Profile profile = currentSession.get(Profile.class, id);
		
		return profile;
	}
	
	public Profile getProfileByUserId(Integer userId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Profile> theQuery = currentSession.createQuery("from Profile where user.id=:userId");
		theQuery.setParameter("userId", userId);
		Profile profile = theQuery.getSingleResult();
		
		return profile;
	}

	public List<Profile> getAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Profile> theQuery = currentSession.createQuery("from Profile", Profile.class);
		
		List<Profile> profiles = theQuery.getResultList();
		
		return profiles;
		
		
	}

	public Profile save(Profile profile) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		if(profile.getId() != 0){
			profile.setId(0);
		}
		
		currentSession.saveOrUpdate(profile);
		return profile;
	
	}
	
	public Profile update(Profile profile) {
		System.out.println("Update DAO");
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(profile);
		
		return profile;
	}

	public void delete(Integer profileId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery(
				"delete from Profile where id=:profileId");
		
		theQuery.setParameter("profileId", profileId);
		theQuery.executeUpdate();
		
	}
	
}
