package com.kamil.dinnerapp.entity.send;

public class SendUnreadConversationId {
	private Integer id;
	private Integer customCreated;
	
	public SendUnreadConversationId() {
		// TODO Auto-generated constructor stub
	}

	public SendUnreadConversationId(Integer id, Integer customCreated) {
		this.id = id;
		this.customCreated = customCreated;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomCreated() {
		return customCreated;
	}

	public void setCustomCreated(Integer customCreated) {
		this.customCreated = customCreated;
	}
	
	

}
