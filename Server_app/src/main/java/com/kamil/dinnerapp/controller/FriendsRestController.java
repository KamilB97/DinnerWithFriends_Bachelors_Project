package com.kamil.dinnerapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamil.dinnerapp.entity.Conversation;
import com.kamil.dinnerapp.entity.Friends;
import com.kamil.dinnerapp.entity.Invitation;
import com.kamil.dinnerapp.entity.send.SendFriend;
import com.kamil.dinnerapp.entity.send.SendInvitation;
import com.kamil.dinnerapp.service.AddFriendService;
import com.kamil.dinnerapp.service.FriendsServiceImpl;
import com.kamil.dinnerapp.service.InvitationServiceImpl;
import com.kamil.dinnerapp.service.ProfileServiceImpl;

@RestController
@RequestMapping("/api")
public class FriendsRestController {

	private FriendsServiceImpl friendsService;
	private InvitationServiceImpl invitationService;
	private AddFriendService addFriendService;

	@Autowired
	public FriendsRestController(FriendsServiceImpl friendsService, InvitationServiceImpl invitationService,
			AddFriendService addFriendService) {
		this.friendsService = friendsService;
		this.invitationService = invitationService;
		this.addFriendService = addFriendService;

	}

	@GetMapping("/friends/{profileId}") // działa
	public List<SendFriend> getFriendsList(@PathVariable Integer profileId) {

		return friendsService.getAllFriendsForProfile(profileId);

	}

	@GetMapping("/friends/delete/{profile1Id}/{profile2Id}") // działa
	public void deleteFriend(@PathVariable Integer profile1Id, @PathVariable Integer profile2Id) {

		friendsService.delete(profile1Id, profile2Id);

	}

	@GetMapping("/invitations/{senderId}/{receiverId}") // działa
	public Map<String, Boolean> sendFriendInvitation(@PathVariable Integer senderId, @PathVariable Integer receiverId) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean response = false;
	
		try {
			invitationService.save(senderId, receiverId);
			response = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("successful", response);
		return map;

	}

	@GetMapping("/invitations/{profileId}") // działa w param wpisujesz receiver id
	public List<SendInvitation> getInvitationsList(@PathVariable Integer profileId) {

		return invitationService.getAllInvitationsForProfile(profileId);

	}

	@GetMapping("/invitations/accept/{invitationId}") // działa
	public Conversation acceptInvitation(@PathVariable Integer invitationId) {

		return addFriendService.addFriendAfterInvAccept(invitationId);

		// return null;

	}

	@GetMapping("/invitations/denial/{invitationId}") // działa
	public void denialInvitation(@PathVariable Integer invitationId) {

		invitationService.delete(invitationId);

	}

}
