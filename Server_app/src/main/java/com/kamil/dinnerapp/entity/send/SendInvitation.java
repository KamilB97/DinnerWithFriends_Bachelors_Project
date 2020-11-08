package com.kamil.dinnerapp.entity.send;

public class SendInvitation {
	Integer invitationId;
	SendProfileBrief profile;
	public SendInvitation() {
		// TODO Auto-generated constructor stub
	}
	
	public SendInvitation(Integer invitationId, SendProfileBrief profile) {
		this.invitationId = invitationId;
		this.profile = profile;
	}

	public Integer getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(Integer invitationId) {
		this.invitationId = invitationId;
	}
	public SendProfileBrief getProfile() {
		return profile;
	}
	public void setProfile(SendProfileBrief profile) {
		this.profile = profile;
	}
	
	
	

}
