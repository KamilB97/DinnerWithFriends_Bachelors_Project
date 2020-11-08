package com.kamil.dinnerapp.entity.send;

public class SendMatched {
	
	private SendProfileBrief profile;
	//private SendProfileBrief profile2;

	private Integer conversationId;

	public SendMatched() {
	}

	public SendMatched(SendProfileBrief profile,  Integer conversationId) {
		this.profile = profile;
		//this.profile2 = profile2;
		this.conversationId = conversationId;
	}

	
//	public SendProfileBrief getProfile1() {
//		return profile1;
//	}
//
//	public void setProfile1(SendProfileBrief profile1) {
//		this.profile1 = profile1;
//	}
//
//	public SendProfileBrief getProfile2() {
//		return profile2;
//	}
//
//	public void setProfile2(SendProfileBrief profile2) {
//		this.profile2 = profile2;
//	}

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
		return "SendMatched [profile=" + profile + ", conversationId=" + conversationId + "]";
	}

	
	

}
