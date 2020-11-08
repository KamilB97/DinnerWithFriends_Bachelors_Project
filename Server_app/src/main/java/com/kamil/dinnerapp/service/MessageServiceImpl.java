package com.kamil.dinnerapp.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.MessageDAOImpl;
import com.kamil.dinnerapp.entity.Message;
import com.kamil.dinnerapp.entity.send.SendMessage;
import com.kamil.dinnerapp.entity.send.SendProfileBrief;

@Service
public class MessageServiceImpl {

	private MessageDAOImpl messageDAO;
	private UnreadServiceImpl unreadService;

	@Autowired
	public MessageServiceImpl(MessageDAOImpl messageDAO, UnreadServiceImpl unreadService) {
		this.messageDAO = messageDAO;
		this.unreadService = unreadService;
	}

	@Transactional
	public Message get(Integer id) {

		Message message = messageDAO.get(id);

		return message;

	}

	@Transactional
	public Message save(Message message) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("timestamp from message " + timestamp);
		String date = timestamp.toString();
		System.out.println("timestamp date after to string: " + date);
		message.setDate(date);

		System.out.println("message DATE is: " + message.getDate());
		Integer id = messageDAO.save(message);

		if (id != 0) {
			Message feedback = messageDAO.get(id);
			return feedback;
		}
		return null;

	}

	@Transactional
	public void delete(Integer id) {

		messageDAO.delete(id);
	}

	@Transactional
	public List<SendMessage> getMessagesForConversation(Integer conversationId, Integer inputProfileId) {

		List<Message> messageList = messageDAO.getMessagesForConversation(conversationId);
		List<SendMessage> sendMessageList = new ArrayList<SendMessage>();

		for (Message message : messageList) {

			Integer profileId = message.getSender().getId();
			String name = message.getSender().getFirstName();
			String surname = message.getSender().getLastName();

			Integer conversation = message.getConversation().getId();
			Integer messageId = message.getId();
			String messageText = message.getText();
			String date = message.getDate();

			SendProfileBrief profile = new SendProfileBrief(profileId, name, surname);
			SendMessage sendMessage = new SendMessage(profile, conversation, messageId, messageText, date);
			System.out.println(sendMessage.toString());

			sendMessageList.add(sendMessage);
			// System.out.println("added sendMessage to List");

		}
			unreadService.delete(conversationId, inputProfileId);	// FOR BETTER PERFORMANCE USE deleteObject
		
		return sendMessageList;

	}

}
