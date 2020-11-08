package com.kamil.dinnerapp.entity.send;

public class SendProfileBrief {

	private Integer profileId;
	private String name;
	private String surname;
	private String image;
	public SendProfileBrief() {
	}

	public SendProfileBrief(Integer profileId, String name, String surname) {
		this.profileId = profileId;
		this.name = name;
		this.surname = surname;
	}
	public SendProfileBrief(Integer profileId) {
		this.profileId = profileId;
		
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
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "SendProfileBrief [profileId=" + profileId + ", name=" + name + ", surname=" + surname + "]";
	}
	

}
