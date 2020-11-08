package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.User;
import com.kamil.dinnerapp.entity.UserConnection;

@Repository
public class UserConnectionDAOImpl {

	private EntityManager entityManager;
	
	@Autowired
	public UserConnectionDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	public UserConnection get(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);
		UserConnection connection = currentSession.get(UserConnection.class, id);
		return connection;
	}
	
	public UserConnection get(Integer baseProfileId, Integer targetProfileId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<UserConnection> theQuery = currentSession.createQuery("from UserConnection uc where uc.baseProfileId = :baseProfileId "
				+ "and uc.targetProfileId = :targetProfileId");
		theQuery.setParameter("baseProfileId", baseProfileId);
		theQuery.setParameter("targetProfileId", targetProfileId);
		
		UserConnection userConnection = theQuery.getSingleResult();
		return userConnection;
		
		
	}


	public List<UserConnection> getAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<UserConnection> theQuery = currentSession.createQuery("from UserConnection", UserConnection.class);
		List<UserConnection> connections = theQuery.getResultList();
		
		return connections;
	}

	public UserConnection save(UserConnection connection) {
		Session currentSession = entityManager.unwrap(Session.class);
		//System.out.println("saving user Connection: "+ connection.getBaseProfileId() +" "+ connection.getTargetProfileId()+" "+ connection.getFitCondition());
		currentSession.saveOrUpdate(connection);
		
		return connection;
		
	}

	public void delete(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<UserConnection> theQuery = currentSession.createQuery("delete from UserConnection where id = :connectionId ");
		theQuery.setParameter("connectionId", id);
		theQuery.executeUpdate();
		
	}
	
	
	

}
