package com.example.dinnerapp.model.send;

import com.example.dinnerapp.model.entity.Message;

public class SendMessage {

    private ProfileBrief profile;
    private Integer conversationId;
    private Message messageInfo;

    public SendMessage(ProfileBrief profile, Integer conversationId, Message messageInfo) {
        this.profile = profile;
        this.conversationId = conversationId;
        this.messageInfo = messageInfo;
    }
    public SendMessage(Integer profileId, String name, String surname, String image ,
                       Integer conversationId, Integer messageId, String messageTxt, String date) {


        this.profile = new ProfileBrief(profileId, name, surname, image);
        this.conversationId = conversationId;
        this.messageInfo = new Message(messageId, messageTxt, date);
    }

    public SendMessage() {
    }

    public ProfileBrief getProfile() {
        return profile;
    }

    public void setProfile(ProfileBrief profile) {
        this.profile = profile;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Message getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(Message messageInfo) {
        this.messageInfo = messageInfo;
    }

    @Override
    public String toString() {
        return "SendMessage{" +
                "profile=" + profile +
                ", conversationId=" + conversationId +
                ", messageInfo=" + messageInfo +
                '}';
    }
}

