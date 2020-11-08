package com.kamil.dinnerapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.ConversationDAOImpl;
import com.kamil.dinnerapp.entity.Conversation;
import com.kamil.dinnerapp.entity.ConversationParticipant;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.send.SendCustomConversation;
import com.kamil.dinnerapp.entity.send.SendCustomConversationWithParticipants;
import com.kamil.dinnerapp.entity.send.SendParticipant;
import com.kamil.dinnerapp.entity.send.SendProfileBrief;

@Service
public class ConversationServiceImpl {

	private ConversationDAOImpl conversationDAO;
	private ProfileServiceImpl profileService;
	private ConversationParticipantServiceImpl participantService;

	@Autowired
	public ConversationServiceImpl(ConversationDAOImpl conversationDAO, ProfileServiceImpl profileService,
			ConversationParticipantServiceImpl participantService) {
		this.conversationDAO = conversationDAO;
		this.profileService = profileService;
		this.participantService = participantService;
	}

	@Transactional
	public Conversation get(Integer id) {

		Conversation conversation = conversationDAO.get(id);
		return conversation;
	}

	@Transactional
	public List<SendCustomConversation> getCustom(Integer profileId) {
		List<Conversation> conversationList = conversationDAO.getCustom(profileId);
		List<SendCustomConversation> customConversationlist = new ArrayList<SendCustomConversation>();

		for (Conversation conversation : conversationList) {
			SendCustomConversation customConversation = new SendCustomConversation();
			customConversation.setId(conversation.getId());
			customConversation.setName(conversation.getName());
			customConversation.setCustomCreated(conversation.getCustomCreated());
			
			List<SendProfileBrief> participants = participantService.getParticipantsForConversation(conversation.getId());
			customConversation.setParticipants(participants);
			
			customConversationlist.add(customConversation);
			
			
			
			
			
		}
		return customConversationlist;

	}

	@Transactional
	public Conversation save(Conversation conversation) {

		return conversationDAO.save(conversation);

	}

	@Transactional
	public Conversation save(SendCustomConversation customConversation) {

		Conversation conversation = new Conversation();
		conversation.setName(customConversation.getName());
		conversation.setCustomCreated(customConversation.getCustomCreated());

		return conversationDAO.save(conversation);

	}

	@Transactional
	public Conversation save(SendCustomConversationWithParticipants customConversationWithParticipants) {

		Conversation newConversation = new Conversation();
		newConversation.setName(customConversationWithParticipants.getCustomConversation().getName());
		newConversation.setCustomCreated(customConversationWithParticipants.getCustomConversation().getCustomCreated());

		Integer conversationId = conversationDAO.saveAndReturnId(newConversation);
		Conversation conversation = conversationDAO.get(conversationId);

		ArrayList<SendProfileBrief> participantsArray = customConversationWithParticipants.getParticipants();

		for (int i = 0; i < participantsArray.size(); i++) {
			int active = 2;

			SendProfileBrief profileBrief = participantsArray.get(i);
			int participantId = profileBrief.getProfileId();
			Profile profile = profileService.get(participantId);

			ConversationParticipant participant = new ConversationParticipant(conversation, profile, active);
			participantService.save(participant);

		}
		return conversation;

	}
	
	@Transactional 
	public void addParticipantToConversation(SendParticipant participant)  {
		
		ConversationParticipant checkIfExists = null;
		
		try {
			checkIfExists = participantService.getParticipantForConversation(participant.getConversationId(), participant.getProfileId());
			
			
		}catch (NoResultException nre){
				//Ignore this because as per your logic this is ok!
				}
		if(checkIfExists == null) {
			
			Profile profile = profileService.get(participant.getProfileId());
			Conversation conversation = conversationDAO.get(participant.getConversationId());
			Integer active = participant.getActive();
			ConversationParticipant conversationParticipant = new ConversationParticipant(conversation, profile, active );
			
			participantService.save(conversationParticipant);
		}
		
		
		
		

	}

	@Transactional
	public void update(Conversation conversation) {
		conversationDAO.update(conversation);

	}

	@Transactional
	public void delete(Integer id) {

		conversationDAO.delete(id);

	}

}
