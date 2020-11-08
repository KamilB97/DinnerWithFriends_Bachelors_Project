package com.kamil.dinnerapp.entity;

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

import org.hibernate.validator.constraints.CodePointLength;

@Entity
@Table(name = "friends")
public class Friends {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "friend_id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "conversation_id")
	private Conversation conversation;
	
	@ManyToOne
	@JoinColumn(name = "profile_1")
	private Profile profile1;
	
	@ManyToOne
	@JoinColumn(name = "profile_2")
	private Profile profile2;
	
	
	public Friends() {
	}
	
	

	public Friends(Conversation conversation, Profile profil1, Profile profile2) {
		this.conversation = conversation;
		this.profile1 = profil1;
		this.profile2 = profile2;
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

	public Profile getProfil1() {
		return profile1;
	}

	public void setProfil1(Profile profil1) {
		this.profile1 = profil1;
	}

	public Profile getProfile2() {
		return profile2;
	}

	public void setProfile2(Profile profile2) {
		this.profile2 = profile2;
	}
	
	
	
	
	

}
