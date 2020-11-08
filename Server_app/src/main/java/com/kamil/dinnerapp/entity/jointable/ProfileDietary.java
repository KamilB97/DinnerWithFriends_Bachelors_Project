package com.kamil.dinnerapp.entity.jointable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kamil.dinnerapp.entity.DietaryPreference;
import com.kamil.dinnerapp.entity.Profile;


@Entity
@Table(name = "profile_dietarypreference")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ProfileDietary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_dietary_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;
		
	@ManyToOne
	@JoinColumn(name = "diet_id")
	private DietaryPreference diet;

	
	public ProfileDietary() {
	}


	public ProfileDietary(Profile profile, DietaryPreference diet) {
		this.profile = profile;
		this.diet = diet;
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


	public DietaryPreference getDiet() {
		return diet;
	}


	public void setDiet(DietaryPreference diet) {
		this.diet = diet;
	}
	
	
	
	
	
	
	
	

}
