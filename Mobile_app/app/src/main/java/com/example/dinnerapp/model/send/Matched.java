package com.example.dinnerapp.model.send;

public class Matched {

    private ProfileBrief profileBrief;
    private Integer conversationId;

    public Matched() {
    }

    public Matched(ProfileBrief profileBrief, Integer conversationId) {
        this.profileBrief = profileBrief;
        this.conversationId = conversationId;
    }
    public Matched(String profileId, String name, String surname, String image, Integer conversationId) {
        this.conversationId = conversationId;
        this.profileBrief = new ProfileBrief(Integer.parseInt(profileId),name,surname,image);
    }

    public ProfileBrief getProfileBrief() {
        return profileBrief;
    }

    public void setProfileBrief(ProfileBrief profileBrief) {
        this.profileBrief = profileBrief;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }
}
