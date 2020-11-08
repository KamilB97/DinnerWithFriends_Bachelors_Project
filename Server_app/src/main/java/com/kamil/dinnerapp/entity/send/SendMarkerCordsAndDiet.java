package com.kamil.dinnerapp.entity.send;

public class SendMarkerCordsAndDiet {
	
	String latitude;
	String longitude;
	String dietaryName;
	
	public SendMarkerCordsAndDiet() {
	}

	public SendMarkerCordsAndDiet(String latitude, String longitude, String dietaryName) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.dietaryName = dietaryName;
	}


	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String lattitude) {
		this.latitude = lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDietaryName() {
		return dietaryName;
	}

	public void setDietaryName(String dietaryName) {
		this.dietaryName = dietaryName;
	}

	@Override
	public String toString() {
		return "SendMarkerCordsAndDiet [lattitude=" + latitude + ", longitude=" + longitude + ", dietaryName="
				+ dietaryName + "]";
	}
	
	
}
