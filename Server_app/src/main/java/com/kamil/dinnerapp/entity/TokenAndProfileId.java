package com.kamil.dinnerapp.entity;

public class TokenAndProfileId {
	String token;
	Integer profileId;
	String name;
	String surname;
	Integer userId;

	public TokenAndProfileId() {
	}

	public TokenAndProfileId(String token, Integer profileId) {
		this.token = token;
		this.profileId = profileId;
	}
	
	public TokenAndProfileId(String token, Integer profileId, Integer userId, String name, String surname) {
		this.token = token;
		this.profileId = profileId;
		this.name = name;
		this.surname = surname;
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
	
	
	
}
