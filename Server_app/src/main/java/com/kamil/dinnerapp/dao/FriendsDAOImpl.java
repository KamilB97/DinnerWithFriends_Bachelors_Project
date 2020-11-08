package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Friends;

@Repository
public class FriendsDAOImpl {
	
	private EntityManager entityManager;
	
	@Autowired
	public FriendsDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Friends get(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Friends.class, id);
		
	}
	
	public List<Friends> getAllFriendsForProfile(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		Query<Friends> theQuery = currentSession.createQuery("from Friends f where f.profile1.id = :profileId or f.profile2.id = :profileId");
		theQuery.setParameter("profileId", id);
		
		List<Friends> friendList = theQuery.getResultList();
		return friendList;
		
	}

	public void save(Friends friends) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(friends);
		
	}
	
	public void delete(Integer profile1Id, Integer profile2Id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Friends> theQuery = currentSession.createQuery("delete from Friends f where (f.profile1.id = :profile1Id and f.profile2.id = :profile2Id)"
				+ " or (f.profile1.id = :profile2Id and f.profile2.id = :profile1Id)");
		theQuery.setParameter("profile1Id", profile1Id);
		theQuery.setParameter("profile2Id", profile2Id);
		theQuery.executeUpdate();
	}
	
	

}
