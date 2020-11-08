package com.kamil.dinnerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.CodePointLength;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "message")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conversation_id")
	private Conversation conversation;
																
	@OneToOne													
	@JoinColumn(name = "sender_id")
	private Profile sender;
	
//	@Column(name = "receiver_id")
//	private Integer receiver;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "text")
	private String text;
	
	public Message() {
	}
	
	public Message(Conversation conversation, Profile sender) {
		this.conversation = conversation;
		this.sender = sender;
		
	}





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
	}

	

	public Profile getSender() {
		return sender;
	}

	public void setSender(Profile sender) {
		this.sender = sender;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	

}
