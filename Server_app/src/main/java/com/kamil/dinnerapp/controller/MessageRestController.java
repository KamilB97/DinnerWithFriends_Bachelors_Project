package com.kamil.dinnerapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamil.dinnerapp.entity.Conversation;
import com.kamil.dinnerapp.entity.Message;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.UnreadMessage;
import com.kamil.dinnerapp.entity.send.SendCustomConversation;
import com.kamil.dinnerapp.entity.send.SendCustomConversationWithParticipants;
import com.kamil.dinnerapp.entity.send.SendMessage;
import com.kamil.dinnerapp.entity.send.SendUnreadConversationId;
import com.kamil.dinnerapp.service.ConversationServiceImpl;
import com.kamil.dinnerapp.service.MessageServiceImpl;
import com.kamil.dinnerapp.service.SendMessageServiceImpl;
import com.kamil.dinnerapp.service.UnreadServiceImpl;

@RestController
@RequestMapping("/api")
public class MessageRestController {

	private MessageServiceImpl messageService;
	private SendMessageServiceImpl sendMessageService;
	private UnreadServiceImpl unreadService;
	private ConversationServiceImpl conversationService;

	@Autowired
	public MessageRestController(MessageServiceImpl messageService, SendMessageServiceImpl sendMessageService,
			UnreadServiceImpl unreadService, ConversationServiceImpl conversationService) {
		this.messageService = messageService;
		this.sendMessageService = sendMessageService;
		this.unreadService = unreadService;
		this.conversationService = conversationService;
	}

	@PostMapping("/messages")
	public SendMessage sendMessage(@RequestBody SendMessage sendMessage) {
		return sendMessageService.sendMessage(sendMessage);

	}

	@GetMapping("/messages/{conversationId}/{profileId}") // działa
	public List<SendMessage> getMessagesForConversation(@PathVariable Integer conversationId,
			@PathVariable Integer profileId) {

		// System.out.println("tokken: " + token);

		return messageService.getMessagesForConversation(conversationId, profileId);

	}

	@GetMapping("/messages/unread/{conversationId}/{profileId}")
	public Map<String, Boolean> checkUnreadForConversationAndProfile(@PathVariable Integer conversationId,
			@PathVariable Integer profileId) {
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		Boolean response = unreadService.checkUnreadForConversationAndProfile(conversationId, profileId);
		map.put("unread", response);
		return map; 
	}

	@GetMapping("/messages/unread/delete/{conversationId}/{profileId}")
	public void deleteUnreadForProfileConversation(@PathVariable Integer conversationId,
			@PathVariable Integer profileId) {
		unreadService.delete(conversationId, profileId);
		// unreadService.deleteByObject(conversationId, profileId);
	}

	@GetMapping("/messages/unread/{profileId}")
	public List<SendUnreadConversationId> getUnreadConversationForProfile(@PathVariable Integer profileId) {
		return unreadService.getConversationWithUnreadMessages(profileId);
	}

	@GetMapping("/messages/unread/save/{conversationId}/{profileId}")
	public void save(@PathVariable Integer conversationId, @PathVariable Integer profileId) {
		unreadService.save(conversationId, profileId);

	}

	

	

//	@PostMapping("/messages/unread/delete")
//	public void deleteUnreadMessageForProfileConversation(@RequestBody UnreadMessage unreadMessage) {
//		 unreadService.delete(unreadMessage);	
//	}

}
