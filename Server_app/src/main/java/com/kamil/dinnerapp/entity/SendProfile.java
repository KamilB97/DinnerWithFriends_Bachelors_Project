package com.kamil.dinnerapp.entity;

import java.util.Arrays;

public class SendProfile {

private Integer profileId;
private Integer userId;
private String firstName;
private String lastName;
private String gender;
private Integer age;

private Integer cityId;
private String about;
String street;
String longitude;
String latitude;
private Integer[] dietary;
private Integer[] interestings;

public SendProfile() {
}

public SendProfile(Integer userId, String firstname, String lastname, String gender) {
	this.userId = userId;
	this.firstName = firstname;
	this.lastName = lastname;
	this.gender = gender;
}

public Integer getUserId() {
	return userId;
}

public void setUserId(Integer userId) {
	this.userId = userId;
}



public Integer getProfileId() {
	return profileId;
}

public void setProfileId(Integer profileId) {
	this.profileId = profileId;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstname) {
	this.firstName = firstname;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastname) {
	this.lastName = lastname;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public Integer getAge() {
	return age;
}

public void setAge(Integer age) {
	this.age = age;
}

public Integer getCityId() {
	return cityId;
}

public void setCityId(Integer cityId) {
	this.cityId = cityId;
}

public String getAbout() {
	return about;
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

public void setAbout(String about) {
	this.about = about;
}

public Integer[] getDietary() {
	return dietary;
}

public void setDietary(Integer[] dietary) {
	this.dietary = dietary;
}

public Integer[] getInterestings() {
	return interestings;
}

public void setInterestings(Integer[] interestings) {
	this.interestings = interestings;
}

@Override
public String toString() {
	return "SendProfile [profileId=" + profileId + ", userId=" + userId + ", firstName=" + firstName + ", lastName="
			+ lastName + ", gender=" + gender + ", age=" + age + ", cityId=" + cityId + ", about=" + about + ", street="
			+ street + ", longitude=" + longitude + ", latitude=" + latitude + ", dietary=" + Arrays.toString(dietary)
			+ ", interestings=" + Arrays.toString(interestings) + "]";
}








}
