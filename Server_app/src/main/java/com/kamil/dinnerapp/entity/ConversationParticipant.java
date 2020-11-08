package com.kamil.dinnerapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "conversation_participant")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ConversationParticipant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "conversation_participant_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "conversation_id")
	//@JsonManagedReference("conversation")
	private Conversation conversation;
	
	@OneToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;
	
	@Column(name = "active")
	private Integer active;
	
	//@OneToMany(mappedBy = "conversation")
	//private List<Message> messages;
	
	
	public ConversationParticipant() {
	}


	public ConversationParticipant(Conversation conversation, Profile profile, Integer active) {
		this.conversation = conversation;
		//this.conversation.addParticipantToConversation(this); // @@@#$$$#$  zwróć ówagę czy  nie powoduje komplikacji
		this.profile = profile;
		this.active = active;
	}


//	public List<Message> getMessages() {
//		return messages;
//	}
//
//
//	public void setMessages(List<Message> messages) {
//		this.messages = messages;
//	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Conversation getConversation() {
		return conversation;
	}


	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
		//this.conversation.addParticipantToConversation(this);  // @@@#$$$#$  zwróć ówagę czy  nie powoduje komplikacji
	}


	public Profile getProfile() {
		return profile;
	}


	public void setProfile(Profile profile) {
		this.profile = profile;
	}


	public Integer getActive() {
		return active;
	}


	public void setActive(Integer active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return "ConversationParticipant [id=" + id + ", conversation=" + conversation + ", profile=" + profile
				+ ", active=" + active + "]";
	}
	
	

}
