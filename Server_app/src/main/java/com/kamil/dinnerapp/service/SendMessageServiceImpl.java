package com.kamil.dinnerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.entity.Conversation;
import com.kamil.dinnerapp.entity.Message;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.send.SendMessage;
import com.kamil.dinnerapp.entity.send.SendProfileBrief;

@Service
public class SendMessageServiceImpl {

	private MessageServiceImpl messageService;
	private ConversationServiceImpl conversationService;
	private ProfileServiceImpl profileService;
	private ConversationParticipantServiceImpl conversationParticipantsService;
	private UnreadServiceImpl unreadService;
	@Autowired
	public SendMessageServiceImpl(MessageServiceImpl messageService, ConversationServiceImpl conversationService,
			ProfileServiceImpl profileService, ConversationParticipantServiceImpl conversationParticipantsService,
			UnreadServiceImpl unreadService) {

		this.messageService = messageService;
		this.conversationService = conversationService;
		this.profileService = profileService;
		this.conversationParticipantsService = conversationParticipantsService;
		this.unreadService = unreadService;
		
	}

	public SendMessage sendMessage(SendMessage sendMessage) {

		Message message = new Message();
		System.out.println("przed  pobraniem conversation");
		Conversation conversation = conversationService.get(sendMessage.getConversationId());
		message.setConversation(conversation);

		Profile senderProfile = profileService.get(sendMessage.getProfile().getProfileId());
		message.setSender(senderProfile);

		message.setText(sendMessage.getMessageTxt());

		Message feedback = messageService.save(message);

		if (feedback != null) {

			SendProfileBrief profile = new SendProfileBrief(feedback.getSender().getId(),
					feedback.getSender().getFirstName(), feedback.getSender().getLastName());

			Integer conversationId = feedback.getConversation().getId();
			Integer messageId = feedback.getId();
			String messageText = feedback.getText();
			String date = feedback.getDate();

			SendMessage sendMessageFeedback = new SendMessage(profile, conversationId, messageId, messageText, date);

			// add unreaded Message for targetProfile to conversations, czyli get conversationParticipants and send to them
			
			List<SendProfileBrief> conversationParticipants = conversationParticipantsService.getParticipantsForConversation(conversationId);
			
			for(int i = 0; i< conversationParticipants.size(); i++) {
				System.out.println("ProfileId " +  conversationParticipants.get(i).getProfileId());
				if (conversationParticipants.get(i).getProfileId() == profile.getProfileId()){ // profile.getPorfileId is ID of sender profile
					System.out.println("sender: " + senderProfile.getId());
					System.out.println("profile to delete: "+ conversationParticipants.get(i).getProfileId());
					conversationParticipants.remove(i);
				}
			}
			for(SendProfileBrief profileBrief : conversationParticipants) {
				unreadService.save(conversationId, profileBrief.getProfileId());
			}
			
			
			
			return sendMessageFeedback;
		}
		return null;

	}

}
