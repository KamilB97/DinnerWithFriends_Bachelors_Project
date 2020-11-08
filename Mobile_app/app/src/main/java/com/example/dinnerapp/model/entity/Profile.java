package com.example.dinnerapp.model.entity;

import java.util.ArrayList;

public class Profile {

    private Integer profileId;
    private Integer age;
    private String gender;
    private String firstName;
    private String lastName;
    private String cityName;
    private String about;
    private String street;
    private Double longitude;
    private Double latitude;
    private String image;

    private ArrayList<DietaryPreference> dietaryList;
    private ArrayList<Interesting> interestingsList;

    public Profile() {
    }

    public Profile(
            Integer profileId, Integer age, String gender, String firstName,
            String lastName, String cityName, String about, String street,
            Double longitude, Double latitude, String image) {

        this.profileId = profileId;
        this.age = age;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityName = cityName;
        this.about = about;
        this.street = street;
        this.longitude = longitude;
        this.latitude = latitude;
        this.image = image;
    }

    public Profile(
            Integer profileId, Integer age, String gender, String firstName,
            String lastName, String cityName, String about, String street,
            String image) {

        this.profileId = profileId;
        this.age = age;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityName = cityName;
        this.about = about;
        this.street = street;
        this.image = image;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<DietaryPreference> getDietaryList() {
        return dietaryList;
    }

    public void setDietaryList(ArrayList<DietaryPreference> dietaryList) {
        this.dietaryList = dietaryList;
    }

    public ArrayList<Interesting> getInterestingsList() {
        return interestingsList;
    }

    public void setInterestingsList(ArrayList<Interesting> interestingsList) {
        this.interestingsList = interestingsList;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", about='" + about + '\'' +
                ", street='" + street + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", dietaryList=" + dietaryList +
                ", interestingsList=" + interestingsList +
                '}';
    }
}
