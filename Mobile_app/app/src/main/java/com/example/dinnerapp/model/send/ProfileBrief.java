package com.example.dinnerapp.model.send;

public class ProfileBrief {

    private Integer profileId;
    private String name;
    private String surname;
    private String image;

    public ProfileBrief() {
    }
    public ProfileBrief(Integer profileId, String name, String surname, String image) {
        this.profileId = profileId;
        this.name = name;
        this.surname = surname;
        this.image = image;
    }
    public ProfileBrief(Integer profileId, String name, String surname) {
        this.profileId = profileId;
        this.name = name;
        this.surname = surname;
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
        image = image;
    }

    @Override
    public String toString() {
        return "ProfileBrief{" +
                "profileId=" + profileId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", Image='" + image + '\'' +
                '}';
    }
}
