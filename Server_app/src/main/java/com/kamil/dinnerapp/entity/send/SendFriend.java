package com.kamil.dinnerapp.entity.send;

public class SendFriend {
	private Integer friendshipId;
	private SendProfileBrief profile;

	private Integer conversationId;
	
	public SendFriend() {
	}

	public SendFriend(Integer friendshipId,SendProfileBrief profile, Integer conversationId) {
		this.profile = profile;
		this.conversationId = conversationId;
		this.friendshipId = friendshipId;
	}

	
	
	public Integer getFriendshipId() {
		return friendshipId;
	}

	public void setFriendshipId(Integer friendshipId) {
		this.friendshipId = friendshipId;
	}

	public SendProfileBrief getProfile() {
		return profile;
	}

	public void setProfile(SendProfileBrief profile) {
		this.profile = profile;
	}

	public Integer getConversationId() {
		return conversationId;
	}

	public void setConversationId(Integer conversationId) {
		this.conversationId = conversationId;
	}

	@Override
	public String toString() {
		return "SendFriend [friendshipId=" + friendshipId + ", profile=" + profile + ", conversationId="
				+ conversationId + "]";
	}

	
	
	
	

}
