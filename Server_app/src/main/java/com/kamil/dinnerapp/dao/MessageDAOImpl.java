package com.kamil.dinnerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamil.dinnerapp.entity.Message;

@Repository
public class MessageDAOImpl {
	
	private EntityManager entityManager;
	
	@Autowired
	public MessageDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Message get(Integer id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		return currentSession.get(Message.class, id);
		
	}
	
	public Integer save(Message message) {
		Session currentSession = entityManager.unwrap(Session.class);
		int id = (Integer)currentSession.save(message); // int id = (Integer) Session.save(entity)
		System.out.println("id of saved message: " + id);
		
		return id;
	}
	
	public void delete(Integer id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("delete form Message where id = :messageId");
		theQuery.setParameter("messageId", id);
		theQuery.executeUpdate();
	}
	public List<Message> getMessagesForConversation(Integer conversationId){
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Message> theQuery = currentSession.createQuery("from Message m where m.conversation.id = :conversationId order by m.date");
		theQuery.setParameter("conversationId", conversationId);
		List<Message> messageList = theQuery.getResultList();
		
		return messageList;
		

	}

}
