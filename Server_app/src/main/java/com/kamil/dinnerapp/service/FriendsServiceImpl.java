package com.kamil.dinnerapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.dinnerapp.dao.FriendsDAOImpl;
import com.kamil.dinnerapp.entity.Friends;
import com.kamil.dinnerapp.entity.Profile;
import com.kamil.dinnerapp.entity.send.SendFriend;
import com.kamil.dinnerapp.entity.send.SendMatched;
import com.kamil.dinnerapp.entity.send.SendProfileBrief;

@Service
public class FriendsServiceImpl {
	
	private FriendsDAOImpl friendsDAO;
	private ProfileServiceImpl profileService;
	
	@Autowired
	public FriendsServiceImpl(FriendsDAOImpl friendsDAO, ProfileServiceImpl profileService) {

		this.friendsDAO = friendsDAO;
		this.profileService = profileService;
	}
	
	@Transactional
	public Friends get(Integer id) {
		Friends friend = friendsDAO.get(id);
		return friend;
	}
	
	@Transactional
	public List<SendFriend> getAllFriendsForProfile(Integer id) {
		
		List<Friends> friendList = friendsDAO.getAllFriendsForProfile(id);
		List<SendFriend> sendFriendList = new ArrayList<SendFriend>();
		SendProfileBrief profile = new SendProfileBrief();
		for(Friends friend : friendList ) {
			if( friend.getProfil1().getId() != id) {
				
				Integer profile1Id = friend.getProfil1().getId();
				Profile profile1Obj = profileService.get(profile1Id);
				String name1 = profile1Obj.getFirstName();
				String surname1 = profile1Obj.getLastName();
				profile = new SendProfileBrief(profile1Id, name1, surname1);
				if(friend.getProfil1().getImage() != null) {
				profile.setImage(friend.getProfil1().getImage().getUrl());
				}
			}
			if(friend.getProfile2().getId() != id) {
				Integer profile2Id = friend.getProfile2().getId();
				Profile profile2Obj = profileService.get(profile2Id);
				String name2 = profile2Obj.getFirstName();
				String surname2 = profile2Obj.getLastName();
				profile = new SendProfileBrief(profile2Id, name2, surname2);
				if(friend.getProfile2().getImage() != null) {
				profile.setImage(friend.getProfile2().getImage().getUrl());
				}
			}
			
			Integer friendsId = friend.getId();
			Integer conversationId = friend.getConversation().getId();
			
			SendFriend sendFriend = new SendFriend(friendsId, profile, conversationId);
			
			sendFriendList.add(sendFriend);
			
		}
		
		
		return sendFriendList;
		
	}

	@Transactional
	public void save(Friends friends) {
		
		friendsDAO.save(friends);
		
	}
	
	@Transactional
	public void delete(Integer profile1Id, Integer profile2Id) {
		friendsDAO.delete(profile1Id, profile2Id);
	}
	

}
