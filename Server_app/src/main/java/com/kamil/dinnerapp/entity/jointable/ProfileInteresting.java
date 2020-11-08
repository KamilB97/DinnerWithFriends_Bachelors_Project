package com.kamil.dinnerapp.entity.jointable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kamil.dinnerapp.entity.Interesting;
import com.kamil.dinnerapp.entity.Profile;

@Entity
@Table(name = "profile_interesting")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ProfileInteresting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_interesting_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;
	
	
	@ManyToOne
	@JoinColumn(name = "interesting_id")	
	private Interesting interesting;
	
	public ProfileInteresting() {
	}

	public ProfileInteresting(Profile profile, Interesting interesting) {
		this.profile = profile;
		this.interesting = interesting;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Interesting getInteresting() {
		return interesting;
	}

	public void setInteresting(Interesting interesting) {
		this.interesting = interesting;
	}

	
	
	

}
