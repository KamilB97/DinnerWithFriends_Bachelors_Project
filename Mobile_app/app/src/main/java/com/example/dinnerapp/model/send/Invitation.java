package com.example.dinnerapp.model.send;

public class Invitation {

    private Integer invitationId;
    private ProfileBrief profile;

    public Invitation(Integer invitationId, ProfileBrief profile) {
        this.invitationId = invitationId;
        this.profile = profile;
    }

    public Invitation(Integer invitationId, Integer profileId, String name, String surname, String image ) {
        this.invitationId = invitationId;
        this.profile = new ProfileBrief(profileId, name, surname, image);
    }


    public Invitation() {
    }

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public ProfileBrief getProfile() {
        return profile;
    }

    public void setProfile(ProfileBrief profile) {
        this.profile = profile;
    }
}
