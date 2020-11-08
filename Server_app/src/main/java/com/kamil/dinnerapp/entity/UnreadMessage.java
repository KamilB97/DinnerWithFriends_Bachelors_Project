package com.kamil.dinnerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "unread_message")
public class UnreadMessage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unread_message_id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "conversation_id")
	private Conversation conversation;
	@ManyToOne
	@JoinColumn(name = "to_profile")
	private Profile profile;
	
	public UnreadMessage() {
	}

	public UnreadMessage(Conversation conversation, Profile profile) {
		this.conversation = conversation;
		this.profile = profile;
	}

	public Integer getId() {
		return id;
	}


	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	

}
