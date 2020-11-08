package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.User;
@Repository
public class UserDAOImpl {
	private EntityManager entityManager;


	@Autowired
	public UserDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public User get(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);
		User user = currentSession.get(User.class, id);
		return user;
	}
	public User getUserByEmail(String email) {
		
		User user = null;
		Session currentSession = entityManager.unwrap(Session.class);
		try {
			Query<User> theQuery = currentSession.createQuery("from User u where u.email = :sentEmail");
			theQuery.setParameter("sentEmail", email);
		
			user = theQuery.getSingleResult();
		} catch (Exception e) {
			return user;
		}
		return user;
		
		
	}
	
	public User getUserByProfileId(Integer profileId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> theQuery = currentSession.createQuery("from User u where u.profile.id = :profileId");
		theQuery.setParameter("profileId", profileId);
		User theUser = theQuery.getSingleResult();
		return theUser;
	}

	

	public List<User> getAll() {

		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> theQuery = currentSession.createQuery("from User", User.class);
		
		List<User> users = theQuery.getResultList();
		
		return users;
	}

	public User save(User user) {
		Session currentSession = entityManager.unwrap(Session.class);
		if(user.getId() != 0) {
			user.setId(0);
		}
		
		 currentSession.saveOrUpdate(user);
		 return user;
		
	}
	
	public User update(User user) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(user);
		return user;
	}

	public void delete(Integer userId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from User where id = :userId");
		theQuery.setParameter("userId",userId);
		theQuery.executeUpdate();
		
	}
	
	

}
