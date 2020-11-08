package com.kamil.dinnerapp.entity.send;

import java.util.ArrayList;

import antlr.collections.List;

public class SendProfile {

	Integer profileId;
	Integer age;
	String gender;
	String firstName;
	String lastName;
	String cityName;
	//Integer cityId;
	String about;
	String street;
	String longitude;
	String latitude;
	String image;
	ArrayList<SendInteresting> listOfInterestings;
	ArrayList<SendDietaryPreference> listOfDietaryPreferences;
	
	public SendProfile() {
	}
	public SendProfile(Integer profileId, Integer age, String gender, String firstName, String lastName, String cityName,
			String about, ArrayList<SendInteresting> listOfInterestings,
			ArrayList<SendDietaryPreference> listOfDietaryPreferences) {
		this.profileId = profileId;
		this.age = age;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cityName = cityName;
		this.about = about;
		this.listOfInterestings = listOfInterestings;
		this.listOfDietaryPreferences = listOfDietaryPreferences;
		this.image = null;
		
	}

//	public SendProfile(Integer profileId, Integer age, String gender, String firstName, String lastName, String cityName, Integer cityId,
//			String about, ArrayList<SendInteresting> listOfInterestings,
//			ArrayList<SendDietaryPreference> listOfDietaryPreferences) {
//		this.profileId = profileId;
//		this.age = age;
//		this.gender = gender;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.cityName = cityName;
//		this.cityId = cityId;
//		this.about = about;
//		this.listOfInterestings = listOfInterestings;
//		this.listOfDietaryPreferences = listOfDietaryPreferences;
//	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}
	

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	//	public Integer getCityId() {
//		return cityId;
//	}
//	public void setCityId(Integer cityId) {
//		this.cityId = cityId;
//	}
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
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
	public ArrayList<SendInteresting> getListOfInterestings() {
		return listOfInterestings;
	}

	public void setListOfInterestings(ArrayList<SendInteresting> listOfInterestings) {
		this.listOfInterestings = listOfInterestings;
	}

	public ArrayList<SendDietaryPreference> getListOfDietaryPreferences() {
		return listOfDietaryPreferences;
	}

	public void setListOfDietaryPreferences(ArrayList<SendDietaryPreference> listOfDietaryPreferences) {
		this.listOfDietaryPreferences = listOfDietaryPreferences;
	}
	@Override
	public String toString() {
		return "SendProfile [profileId=" + profileId + ", age=" + age + ", gender=" + gender + ", firstName="
				+ firstName + ", lastName=" + lastName + ", cityName=" + cityName + ", about=" + about
				+ ", listOfInterestings=" + listOfInterestings + ", listOfDietaryPreferences="
				+ listOfDietaryPreferences + "]";
	}
	
	
	
	
}
