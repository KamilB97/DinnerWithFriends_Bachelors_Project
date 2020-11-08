package com.example.dinnerapp.model.entity;

import java.util.ArrayList;

public class MyProfileData {

    public static Integer profileId;
    public static String name;
    public static String surname;
    public static Integer userId;
    public static String token;

    public static Integer age;
    public static String gender;
    public static String cityName;
    public static String about;
    public static String street;
    public static String imagePath = null;

    public static ArrayList<DietaryPreference> dietaryList;
    public static ArrayList<Interesting> interestingsList;


    public static void setProfileId(Integer profileId) {
        MyProfileData.profileId = profileId;
    }

    public static void setName(String name) {
        MyProfileData.name = name;
    }

    public static void setSurname(String surname) {
        MyProfileData.surname = surname;
    }

    public static void setUserId(Integer userId) {
        MyProfileData.userId = userId;
    }

    public static void setToken(String token) {
        MyProfileData.token = token;
    }


    public static void setAge(Integer age) {
        MyProfileData.age = age;
    }

    public static void setGender(String gender) {
        MyProfileData.gender = gender;
    }

    public static void setCityName(String cityName) {
        MyProfileData.cityName = cityName;
    }

    public static void setAbout(String about) {
        MyProfileData.about = about;
    }

    public static void setStreet(String street) {
        MyProfileData.street = street;
    }

    public static void setImagePath(String imagePath) {
        MyProfileData.imagePath = imagePath;
    }

    public static void setDietaryList(ArrayList<DietaryPreference> dietaryList) {
        MyProfileData.dietaryList = dietaryList;
    }

    public static void setInterestingsList(ArrayList<Interesting> interestingsList) {
        MyProfileData.interestingsList = interestingsList;
    }


}
