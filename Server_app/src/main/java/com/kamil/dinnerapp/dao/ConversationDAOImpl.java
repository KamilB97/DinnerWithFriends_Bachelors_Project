package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Conversation;

@Repository
public class ConversationDAOImpl {
	
	
private EntityManager entityManager;

@Autowired
public ConversationDAOImpl(EntityManager entityManager) {
	this.entityManager = entityManager;
	
}


public Conversation get(Integer id) {

	Session currentSession = entityManager.unwrap(Session.class);
	return currentSession.get(Conversation.class, id);
}

public List<Conversation> getCustom(Integer id) {

	Session currentSession = entityManager.unwrap(Session.class);
//	Query theQuery = currentSession.createQuery(" from Conversation as c inner join c.conversationParticipants as cp "
//			+ "where c.customCreated = 1 and cp.profile.id = :profileId");
	Query theQuery = currentSession.createQuery(" SELECT cp.conversation from ConversationParticipant as cp inner join cp.conversation as c "
		+ "where c.customCreated = 1 and cp.profile.id = :profileId");
	
	theQuery.setParameter("profileId", id);
	List<Conversation> customConversationList = theQuery.getResultList();
	
	
	return customConversationList;
}


public Conversation save(Conversation conversation) {

	System.out.println("conversation id before: " + conversation.getId());
	Session currentSession = entityManager.unwrap(Session.class);
	currentSession.saveOrUpdate(conversation);
	System.out.println("conversation id after save : " + conversation.getId());
	
	return conversation;
}
public Integer saveAndReturnId(Conversation conversation) {

	System.out.println("conversation id before: " + conversation.getId());
	Session currentSession = entityManager.unwrap(Session.class);
	Integer id = (Integer) currentSession.save(conversation);
	System.out.println("conversation id after save : " + conversation.getId());
	
	return id;
}

public void update(Conversation conversation) {
	Session currentSession = entityManager.unwrap(Session.class);
	currentSession.saveOrUpdate(conversation);
	
}


public void delete(Integer id) {
	Session currentSession = entityManager.unwrap(Session.class);
	
	Query theQuery = currentSession.createQuery("delete from Conversation where id = :conversationId");
	theQuery.setParameter("conversationId", id);
	theQuery.executeUpdate();
	
}

}
