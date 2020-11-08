package com.kamil.dinnerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "invitations")
public class Invitation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invitation_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private Profile sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private Profile receiver;
	
	public Invitation() {
	}

	public Invitation(Profile sender, Profile receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Profile getSender() {
		return sender;
	}

	public void setSender(Profile sender) {
		this.sender = sender;
	}

	public Profile getReceiver() {
		return receiver;
	}

	public void setReceiver(Profile receiver) {
		this.receiver = receiver;
	}
	
	

}
