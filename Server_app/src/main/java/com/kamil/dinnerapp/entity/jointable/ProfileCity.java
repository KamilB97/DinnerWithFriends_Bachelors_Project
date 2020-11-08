package com.kamil.dinnerapp.entity.jointable;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kamil.dinnerapp.entity.City;
import com.kamil.dinnerapp.entity.Profile;


@Entity
@Table(name = "profile_city")
public class ProfileCity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OneToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	public ProfileCity() {
	}

	public ProfileCity(Profile profile, City city) {
		this.profile = profile;
		this.city = city;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "ProfileCity [city=" + city.getName() + "]";
	}
	
	
}
	
	
	


