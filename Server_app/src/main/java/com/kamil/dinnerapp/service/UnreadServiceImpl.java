package com.kamil.dinnerapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.UnreadDAOImpl;
import com.kamil.dinnerapp.entity.Conversation;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.UnreadMessage;
import com.kamil.dinnerapp.entity.send.SendUnreadConversationId;

@Service
public class UnreadServiceImpl {
	
	private UnreadDAOImpl unreadDao;
	private ProfileServiceImpl profileService;
	private ConversationServiceImpl conversationService;
	
	@Autowired
	public UnreadServiceImpl(UnreadDAOImpl unreadDao, ProfileServiceImpl profileService, ConversationServiceImpl conversationService) {
		this.unreadDao = unreadDao;
		this.profileService = profileService;
		this.conversationService = conversationService; 
	}

	@Transactional
	public void delete(Integer conversationId, Integer profileId) {
		unreadDao.delete(conversationId, profileId);
	}
	@Transactional
	public List<SendUnreadConversationId> getConversationWithUnreadMessages(Integer profileId) {
		List<Conversation> conversationList = unreadDao.getConversationWithUnreadMessages(profileId);
		List<SendUnreadConversationId> unreadConversationsList = new ArrayList<SendUnreadConversationId>();
		for(Conversation conversation : conversationList) {
			
			SendUnreadConversationId unreadConversationId = new SendUnreadConversationId(conversation.getId(), conversation.getCustomCreated());
			unreadConversationsList.add(unreadConversationId);
		}
		
		return  unreadConversationsList;
	}
	@Transactional
	public boolean checkUnreadForConversationAndProfile(Integer conversationId, Integer profileId) {
		
		boolean isUnreadMessageInConversationForProfile = unreadDao.checkUnreadForConversationAndProfile(conversationId, profileId);
		return isUnreadMessageInConversationForProfile;
				
	}
	@Transactional
	public void deleteByObject(Integer conversationId, Integer profileId) {
		System.out.println("deleteByObject");
		UnreadMessage unreadMessage = this.get(conversationId, profileId);
		System.out.println("deleting");
		unreadDao.delete(unreadMessage);
		System.out.println("deleted");

	}
	@Transactional
	public UnreadMessage get(Integer conversationId, Integer profileId) {
		System.out.println("getin object");
		UnreadMessage unreadMessage = unreadDao.get(conversationId, profileId);
		return unreadMessage;
		
	}
	@Transactional
	public void save(Integer conversationId, Integer profileId) {
		Profile profile = profileService.get(profileId);
		Conversation conversation = conversationService.get(conversationId);
		UnreadMessage unreadMessage = new UnreadMessage(conversation, profile);
		if(!unreadDao.checkUnreadForConversationAndProfile(conversationId, profileId)) {
			unreadDao.save(unreadMessage);
		}
		
		
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
}
