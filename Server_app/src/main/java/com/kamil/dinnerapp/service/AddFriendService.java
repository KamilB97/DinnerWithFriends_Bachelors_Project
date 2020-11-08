package com.kamil.dinnerapp.service;

import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.entity.Conversation;
import com.kamil.dinnerapp.entity.ConversationParticipant;
import com.kamil.dinnerapp.entity.Friends;
import com.kamil.dinnerapp.entity.Invitation;
import com.kamil.dinnerapp.entity.Matched;
import com.kamil.dinnerapp.entity.Profile;

@Service
public class AddFriendService {

	private ConversationServiceImpl conversationService;
	private InvitationServiceImpl invitationService;
	private FriendsServiceImpl friendsService;
	private MatchedServiceImpl matchedService;
	private ConversationParticipantServiceImpl conversationParticipantService;

	public AddFriendService(
			ConversationServiceImpl conversationService, 
			InvitationServiceImpl invitationService,
			FriendsServiceImpl friendsService, MatchedServiceImpl matchedService,
			ConversationParticipantServiceImpl conversationParticipantService) {
		
		this.conversationService = conversationService;
		this.invitationService = invitationService;
		this.friendsService = friendsService;
		this.matchedService = matchedService;
		this.conversationParticipantService = conversationParticipantService;
	}

	public Conversation addFriendAfterInvAccept(Integer invitationId) {

		System.out.println("POBRANIE INVITATION");
		Invitation invitation = invitationService.get(invitationId);
		Profile profile1 = invitation.getSender();
		Profile profile2 = invitation.getReceiver();

		Matched match = null;
		try {
			System.out.println("POBRANIE MATCHED");
			match = matchedService.getMatch(profile1.getId(), profile2.getId());
		} catch (Exception e) {
			System.out.println(
					" ADD FRIEND AFTER INV ACCEPT: BRAK Matchowania, osoby nie sparowane, tworzenie nowej conversacji");
		}

		Conversation conversation;
		// jeśli osoby są sparowane nie tworze nowej konwersacji a zachowuje starą
		if (match != null) {
			System.out.println("MATCHED != NULL");
			conversation = match.getConversation();
		}
		// jeśli osoby nie są sparowane tworze nową konwersację oraz dodaję do niej
		// uczestników
		else {
			System.out.println("MATCHED ==  NULL");
			conversation = new Conversation();
			System.out.println("ZAPISANIE CONVERSACJI");
			conversation = conversationService.save(conversation);

			ConversationParticipant senderParticipant = new ConversationParticipant(conversation, profile1, 2);
			ConversationParticipant receiverParticipant = new ConversationParticipant(conversation, profile2, 2);
			System.out.println("DODANIE PARTICIPANTÓW P1");
			conversationParticipantService.save(senderParticipant);
			System.out.println("DODANIE PARTICIPANTÓW P2");
			conversationParticipantService.save(receiverParticipant);
		}

		// dodawanie rekordu do friends
		Friends friends = new Friends(conversation, profile1, profile2);
		//System.out.println("ZAPISANIE FRIENDS");
		friendsService.save(friends);
		//System.out.println("USUNIĘCIE INVITATION");
		invitationService.delete(invitation);

		return conversation;

	}

}
