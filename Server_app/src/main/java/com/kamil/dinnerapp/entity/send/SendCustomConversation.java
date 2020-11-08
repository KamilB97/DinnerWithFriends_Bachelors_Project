package com.kamil.dinnerapp.entity.send;

import java.util.List;

public class SendCustomConversation {
	
	Integer id;
	String name;
	Integer customCreated;
	List<SendProfileBrief> participants;
	
	public SendCustomConversation() {
		// TODO Auto-generated constructor stub
	}

	public SendCustomConversation(String name, Integer customCreated) {
		this.name = name;
		this.customCreated = customCreated;
	}
	public SendCustomConversation(String name, Integer customCreated, List<SendProfileBrief> participants) {
		this.name = name;
		this.customCreated = customCreated;
		this.participants = participants;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCustomCreated() {
		return customCreated;
	}

	public void setCustomCreated(Integer customCreated) {
		this.customCreated = customCreated;
	}

	public List<SendProfileBrief> getParticipants() {
		return participants;
	}

	public void setParticipants(List<SendProfileBrief> participants) {
		this.participants = participants;
	}

	@Override
	public String toString() {
		return "SendCustomConversation [id=" + id + ", name=" + name + ", customCreated=" + customCreated + "]";
	}
	
	
	
	

}
