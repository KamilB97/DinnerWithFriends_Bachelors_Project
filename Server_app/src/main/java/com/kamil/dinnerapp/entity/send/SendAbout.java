package com.kamil.dinnerapp.entity.send;

public class SendAbout {
	Integer profileId;
	String about;

	public SendAbout() {
	}

	public SendAbout(Integer profileId, String about) {
		this.profileId = profileId;
		this.about = about;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

}
