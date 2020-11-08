package com.example.dinnerapp.model.send;

public class Friend {

    private Integer friendshipId;
    private ProfileBrief profileBrief;
    private Integer conversationId;

    public Friend() {
    }

    public Friend(Integer friendshipId, ProfileBrief profileBrief, Integer conversationId) {
        this.friendshipId = friendshipId;
        this.profileBrief = profileBrief;
        this.conversationId = conversationId;
    }
    public Friend(Integer friendshipId, Integer conversationId) {
        this.friendshipId = friendshipId;

        this.conversationId = conversationId;
    }
    public Friend(Integer friendshipId, String profileId, String name, String surname, String image, Integer conversationId) {
        this.friendshipId = friendshipId;
        this.conversationId = conversationId;
        this.profileBrief = new ProfileBrief(Integer.parseInt(profileId),name,surname,image);
    }

    public Integer getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Integer friendshipId) {
        this.friendshipId = friendshipId;
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
