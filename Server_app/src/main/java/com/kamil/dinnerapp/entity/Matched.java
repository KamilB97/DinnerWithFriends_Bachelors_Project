package com.kamil.dinnerapp.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "Matched")
@Table(name = "matched")
@IdClass(value = MatchedId.class)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Matched {
	
	@Id
	@JoinColumn(name = "profile1")
	private Integer profile1;
	
	@Id
	@JoinColumn(name = "profile2")
	private Integer profile2;
	
	@Column(name = "date")
	private String date;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conversation_id")
	@JsonBackReference("conversation")
	private Conversation conversation;
	
	public Matched() {
	}
	
	public Matched(Integer profile1, Integer profile2, Conversation conversation, String date) {
		this.profile1 = profile1;
		this.profile2 = profile2;
		this.conversation = conversation;
		this.date = date;
	}


	
	
	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Integer getProfile1() {
		return profile1;
	}

	public void setProfile1(Integer profile1) {
		this.profile1 = profile1;
	}

	public Integer getProfile2() {
		return profile2;
	}

	public void setProfile2(Integer profile2) {
		this.profile2 = profile2;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	
	
	
	
	
	
	

}
