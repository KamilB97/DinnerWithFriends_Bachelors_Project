package com.kamil.dinnerapp.entity.send;

public class SendImage {
	
	Integer profileId;
	String imageUrl;
	
	public SendImage() {
	}

	public SendImage(Integer profileId, String imageUrl) {
		this.profileId = profileId;
		this.imageUrl = imageUrl;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	
	

}
