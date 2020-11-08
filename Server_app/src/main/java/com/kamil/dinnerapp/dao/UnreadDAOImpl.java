package com.kamil.dinnerapp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Conversation;
import com.kamil.dinnerapp.entity.UnreadMessage;

@Repository
public class UnreadDAOImpl {

	private EntityManager entityManager;
	
	@Autowired
	public UnreadDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public UnreadMessage get(Integer conversationId, Integer profileId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<UnreadMessage> theQuery = currentSession.createQuery("from UnreadMessage um where um.profile.id = :profileId and um.conversation.id = :conversationId ", UnreadMessage.class);
		theQuery.setParameter("profileId", profileId);
		theQuery.setParameter("conversationId", conversationId);
		UnreadMessage unreadMessage = theQuery.getSingleResult();
		return unreadMessage;
	}
	public void save(UnreadMessage unreadMessage) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(unreadMessage);
	}
	
	public void delete(Integer conversationId, Integer profileId) {
		Session currentSession = entityManager.unwrap(Session.class);
		System.out.println("delete: " + conversationId + " " + profileId);
		Query theQuery = currentSession.createQuery("delete from UnreadMessage um where um.profile.id = :profileId and um.conversation.id = :conversationId");
		theQuery.setParameter("profileId", profileId);
		theQuery.setParameter("conversationId", conversationId);
		int rowCount = theQuery.executeUpdate();
		System.out.println("row count: " + rowCount);
	}
	
	public void delete(UnreadMessage unreadMessage) {
		Session currentSession = entityManager.unwrap(Session.class);
		System.out.println(unreadMessage.getProfile().getId());
		System.out.println(unreadMessage.getConversation().getId());
		
		System.out.println("before delete");
		try {
		currentSession.delete(unreadMessage);
		}catch (NoResultException nre) {
			System.err.println("CHECK FOR UNREAD MESSAGE IN CONVERSATION FOR SPECIFIED PROFILE ID: brak wiadomości do odczytu");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Conversation> getConversationWithUnreadMessages(Integer profileId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Conversation> theQuery = currentSession.createQuery("select conversation from UnreadMessage um where um.profile.id = :profileId", Conversation.class);
		theQuery.setParameter("profileId", profileId);
		List<Conversation> conversations = theQuery.getResultList();
		
		return conversations;
	}
	
	public boolean checkUnreadForConversationAndProfile(Integer conversationId, Integer profileId) {
		Session currentSession = entityManager.unwrap(Session.class);
		try {
			Query<UnreadMessage> theQuery = currentSession.createQuery("from UnreadMessage um where um.profile.id = :profileId and um.conversation.id = :conversationId ", UnreadMessage.class);
			theQuery.setParameter("profileId", profileId);
			theQuery.setParameter("conversationId", conversationId);
			UnreadMessage unreadMessage = theQuery.getSingleResult();
			
			if(unreadMessage != null) {
				return true;
			}
		}catch (NoResultException nre) {
			System.err.println("CHECK FOR UNREAD MESSAGE IN CONVERSATION FOR SPECIFIED PROFILE ID: brak wiadomości do odczytu");
			return false;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
		
	}
	
	
}
