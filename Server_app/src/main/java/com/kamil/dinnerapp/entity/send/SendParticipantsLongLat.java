package com.kamil.dinnerapp.entity.send;

import java.util.ArrayList;
import java.util.List;

import com.kamil.dinnerapp.entity.DietaryPreference;

public class SendParticipantsLongLat {
	
	private Integer profileId;
	private String name;
	private String surname;
	private String longitude;
	private String latitude;
	
	public SendParticipantsLongLat() {
		
	}
	

	public SendParticipantsLongLat(Integer profileId, String name, String surname) {
		this.profileId = profileId;
		this.name = name;
		this.surname = surname;

	}


	public SendParticipantsLongLat(Integer profileId, String name, String surname, String latitude, String longitude) {
		this.profileId = profileId;
		this.name = name;
		this.surname = surname;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	
	

}
