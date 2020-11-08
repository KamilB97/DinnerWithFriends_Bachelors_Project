package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.ConversationParticipant;
import com.kamil.dinnerapp.entity.Profile;

@Repository
public class ConversationParticipantDAOImpl {
	
	private EntityManager entityManager;

	@Autowired
	public ConversationParticipantDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}


	public ConversationParticipant get(Integer id) {

		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(ConversationParticipant.class, id);
	}
	
	public List<Profile> getParticipantsForConversation(Integer conversationId) {

		Session currentSession = entityManager.unwrap(Session.class);
		Query<Profile> theQuery = currentSession.createQuery("select cp.profile from ConversationParticipant cp where cp.conversation.id = :conversationId", Profile.class);
		theQuery.setParameter("conversationId", conversationId);
		List<Profile> participantsList = theQuery.getResultList();
		
		
		return participantsList;
	}
	public ConversationParticipant getParticipantForConversation(Integer conversationId, Integer profileId) {
		
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			Query<ConversationParticipant> theQuery = currentSession.createQuery("from ConversationParticipant cp"
					+ " where cp.conversation.id = :conversationId and cp.profile.id = :profileId", ConversationParticipant.class);
			theQuery.setParameter("conversationId", conversationId);
			theQuery.setParameter("profileId", profileId);
			
			ConversationParticipant participant = theQuery.getSingleResult();
			return participant;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}


	public void save(ConversationParticipant conversationParticipant) {
		
//		if(conversationParticipant.getId() != 0) {
//			conversationParticipant.setId(0);
//		}
		
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(conversationParticipant);
		
	}

	public void update(ConversationParticipant conversation) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(conversation);
		
	}


	public void delete(Integer conversationParticipantId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from ConversationParticipant where id = :ConversationParticipantId");
		theQuery.setParameter("ConversationParticipantId", conversationParticipantId);
		theQuery.executeUpdate();
		
	}
	public void deleteByProfileId(Integer conversationId, Integer profileId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from ConversationParticipant cp where "
				+ "profile.id = :profileId and cp.conversation.id = :conversationId ");
		theQuery.setParameter("conversationId", conversationId);
		theQuery.setParameter("profileId", profileId);
		theQuery.executeUpdate();
		
	}
	
	
	
	
	

}
