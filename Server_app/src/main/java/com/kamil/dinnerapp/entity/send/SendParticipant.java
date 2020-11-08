package com.kamil.dinnerapp.entity.send;

public class SendParticipant {

	private Integer id;
	private Integer conversationId;
	private Integer profileId;
	private Integer active;
	
	public SendParticipant() {
	}
	public SendParticipant(Integer conversationId, Integer profileId, Integer active) {
		this.conversationId = conversationId;
		this.profileId = profileId;
		this.active = active;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getConversationId() {
		return conversationId;
	}
	public void setConversationId(Integer conversationId) {
		this.conversationId = conversationId;
	}
	public Integer getProfileId() {
		return profileId;
	}
	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	
	
	

}
