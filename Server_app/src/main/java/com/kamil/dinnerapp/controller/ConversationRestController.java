package com.kamil.dinnerapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamil.dinnerapp.entity.ConversationParticipant;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.send.SendCustomConversation;
import com.kamil.dinnerapp.entity.send.SendCustomConversationWithParticipants;
import com.kamil.dinnerapp.entity.send.SendMarkerCordsAndDiet;
import com.kamil.dinnerapp.entity.send.SendParticipant;
import com.kamil.dinnerapp.entity.send.SendParticipantsLongLat;
import com.kamil.dinnerapp.entity.send.SendProfileBrief;
import com.kamil.dinnerapp.service.ConversationParticipantServiceImpl;
import com.kamil.dinnerapp.service.ConversationServiceImpl;

@RestController
@RequestMapping("/api")
public class ConversationRestController {

	private ConversationServiceImpl conversationService;
	private ConversationParticipantServiceImpl conversationParticipantService;
	
	
	public ConversationRestController(ConversationServiceImpl conversationService, ConversationParticipantServiceImpl conversationParticipantService) {
		this.conversationParticipantService = conversationParticipantService;
		this.conversationService = conversationService;
	}
	
	@GetMapping("/conversations/participants/{conversationId}")
	public List<SendProfileBrief> getConversationParticipants(@PathVariable Integer conversationId) {
		System.out.println("conversationParticipants request ");
		List<SendProfileBrief> participantsList = conversationParticipantService.getParticipantsForConversation(conversationId);
		return participantsList;
	}
	
	@GetMapping("/conversations/participants/image/{conversationId}")
	public List<SendProfileBrief> getConversationParticipantsWithImage(@PathVariable Integer conversationId) {
		System.out.println("conversationParticipants request ");
		List<SendProfileBrief> participantsList = conversationParticipantService.getParticipantsWithImgForConversation(conversationId);
		return participantsList;
	}
	
//	@GetMapping("/conversations/participants/{conversationId}/{profileId}")
//	public ConversationParticipant getConversationParticipants(@PathVariable Integer conversationId, @PathVariable Integer profileId) {
//		
//		ConversationParticipant participant = conversationParticipantService.getParticipantForConversation(conversationId, profileId);
//		return participant;
//	}
	
	
	
	@PostMapping("/conversations/participants")
	public Map<String, String> addParticipantToConv(@RequestBody SendParticipant participant)  {
		
		Map<String, String> map = new HashMap<String, String>();
		
		
			conversationService.addParticipantToConversation(participant);
			map.put("response", "successfull");
			return map;
		
	}
	
	@GetMapping("/conversations/participants/delete/{conversationId}/{profileId}")
	public void deleteParticipantByProfileId(@PathVariable Integer conversationId, @PathVariable Integer profileId) {
		conversationParticipantService.deleteByProfileId(conversationId,profileId);
		
	
	}
	@GetMapping("/conversations/participants/longlat/{conversationId}")
	public SendMarkerCordsAndDiet getConversationParticipantsLongLatDiet(@PathVariable Integer conversationId) {
		
		return conversationParticipantService.getParticipantsLongLatDiet(conversationId);
		
	}
	
	@PostMapping("/conversations/custom")
	public void createCustomConversation(@RequestBody SendCustomConversationWithParticipants customConversation) {
		conversationService.save(customConversation);
	}
	
	@GetMapping("/conversations/custom/{profileId}")
	public List<SendCustomConversation> getCustomConversationsForProfile(@PathVariable Integer profileId) {
		return conversationService.getCustom(profileId);
	}
	
	
}
