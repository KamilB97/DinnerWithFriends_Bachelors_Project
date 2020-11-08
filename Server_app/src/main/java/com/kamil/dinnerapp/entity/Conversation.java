package com.kamil.dinnerapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "conversation")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Conversation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "conversation_id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "time")
	private String lastMessageTime; 
	
	@OneToOne(mappedBy = "conversation")
	private Matched matched;
	
	@Column(name = "custom_created")
	Integer customCreated;
	
	@OneToMany(mappedBy = "conversation")
	//@JsonBackReference("conversation")
	List<ConversationParticipant> conversationParticipants;
	
	public Conversation() {
		
	}

	public Conversation(String name) {
		this.name = name;
	}
	
	
	
	public Conversation(String name, List<ConversationParticipant> conversationParticipants) {
		this.name = name;
		this.conversationParticipants = conversationParticipants;
	}

	public void addParticipantToConversation(ConversationParticipant participant) {
		
		conversationParticipants.add(participant);
		participant.setConversation(this);
		
	}
	
	public Matched getMatched() {
		return matched;
	}

	public void setMatched(Matched matched) {
		this.matched = matched;
	}

	public List<ConversationParticipant> getConversationParticipants() {
		return conversationParticipants;
	}

	public void setConversationParticipants(List<ConversationParticipant> conversationParticipants) {
		this.conversationParticipants = conversationParticipants;
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

	public String getLastMessageTime() {
		return lastMessageTime;
	}

	public void setLastMessageTime(String lastMessageTime) {
		this.lastMessageTime = lastMessageTime;
	}

	public Integer getCustomCreated() {
		return customCreated;
	}

	public void setCustomCreated(Integer customCreated) {
		this.customCreated = customCreated;
	}
	
	
	
	

}
