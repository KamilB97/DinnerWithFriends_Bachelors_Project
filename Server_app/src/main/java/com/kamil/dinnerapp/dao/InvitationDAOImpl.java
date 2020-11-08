package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Friends;
import com.kamil.dinnerapp.entity.Invitation;

@Repository
public class InvitationDAOImpl {
	
	private EntityManager entityManager;
	
	@Autowired
	public InvitationDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	
	}
	
	
	public Invitation get(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Invitation.class, id);
	}
	
	public List<Invitation> getAllInvitationsForProfile(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Invitation> theQuery = currentSession.createQuery("from Invitation i where i.receiver.id = :profileId");
		theQuery.setParameter("profileId", id);
		List<Invitation> InvitationsList = theQuery.getResultList();
		return InvitationsList;
		
	}

	public void save(Invitation invitation) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(invitation);
		
		
	}
	
	public void delete(Integer profile1Id, Integer profile2Id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Invitation> theQuery = currentSession.createQuery("delete from Invitation i where (i.sender.id = :profile1Id and i.receiver.id = :profile2Id)"
				+ " or (i.sender.id = :profile2Id and i.receiver.id = :profile1Id)");
		theQuery.setParameter("profile1Id", profile1Id);
		theQuery.setParameter("profile2Id", profile2Id);
		theQuery.executeUpdate();
	}
	
	public void delete(Integer invitationId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Invitation> theQuery = currentSession.createQuery("delete from Invitation i where i.id = :invitationId");
		theQuery.setParameter("invitationId", invitationId);
		theQuery.executeUpdate();
		
	}
	
public void delete(Invitation invitation) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(invitation);
		
	}
	
	

}
