package com.kamil.dinnerapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity(name = "Swipe")
@Table(name = "swipe")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Swipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "swipe_id")
	private int id;


	@ManyToOne()
	@JoinColumns(
			{@JoinColumn(name = "base_profile_id"), 
		 	 @JoinColumn(name = "target_profile_id")
			})
	@JsonManagedReference(value = "userConnection")
    private UserConnection userConnection;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile; 
	
	@Column(name = "liked")
	private int liked;
	
	@Column(name = "date")
	private String swipDate;

	public Swipe(int liked) {
		
		this.liked = liked;
	}
	
	public Swipe() {
	
	}

	
	public Swipe(UserConnection userConnection, Profile profile) {
		this.userConnection = userConnection;
		this.profile = profile;
	}
	

	public Swipe(int id, UserConnection userConnection, Profile profile, int liked, String swipDate) {
		this.id = id;
		this.userConnection = userConnection;
		this.profile = profile;
		this.liked = liked;
		this.swipDate = swipDate;
	}
	public Swipe(Integer id, Integer baseProfile, Integer targetProfile , Profile profile, Integer liked, String swipDate) {
		this.id = id;
		this.userConnection.setBaseProfileId(baseProfile);
		this.userConnection.setTargetProfileId(targetProfile);
		this.profile = profile;
		this.liked = liked;
		this.swipDate = swipDate;
	}

	public UserConnection getConnection() {
		return userConnection;
	}

	public void setConnection(UserConnection connection) {
		this.userConnection = connection;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	public String getSwipDate() {
		return swipDate;
	}

	public void setSwipDate(String swipDate) {
		this.swipDate = swipDate;
	}

	@Override
	public String toString() {
		return "Swipe [id=" + id + ", userConnection=" + userConnection.toString() + ", profile=" + profile.toString() + ", liked=" + liked
				+ ", swipDate=" + swipDate + "]";
	}
	
	
	
	
	
	
	
	
	
	
	

}
