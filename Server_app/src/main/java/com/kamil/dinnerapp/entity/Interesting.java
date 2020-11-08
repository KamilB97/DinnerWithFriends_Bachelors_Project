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
import com.kamil.dinnerapp.entity.jointable.ProfileInteresting;

@Entity
@Table(name = "interesting")
public class Interesting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interesting_id")
	private int id;
	
	@Column(name = "interesting_name")
	private String name;
	
	@OneToMany(mappedBy = "interesting")
	@JsonIgnore
	private List<ProfileInteresting> profiles;
	
	
	public Interesting(String name) {
		this.name = name;
	}

	public Interesting() {
		}	

	public List<ProfileInteresting> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<ProfileInteresting> profiles) {
		this.profiles = profiles;
	}

	public int getId() {
		return id;
	}

	public void setId(int interestingId) {
		this.id = interestingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	

}
