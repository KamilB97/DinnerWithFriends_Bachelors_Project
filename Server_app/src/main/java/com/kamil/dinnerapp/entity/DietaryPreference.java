package com.kamil.dinnerapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kamil.dinnerapp.entity.jointable.ProfileDietary;

@Entity
@Table(name = "dietarypreference")
public class DietaryPreference {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diet_id")
	private int id;
	@Column(name = "diet_name")
	private String name;
	
	@OneToMany(mappedBy = "diet")
	@JsonIgnore
	private List<ProfileDietary> profiles;
	
	
	public DietaryPreference(String name) {
		this.name = name; 
	}
	
	public DietaryPreference() {
	}

	
	
	public List<ProfileDietary> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<ProfileDietary> profiles) {
		this.profiles = profiles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
