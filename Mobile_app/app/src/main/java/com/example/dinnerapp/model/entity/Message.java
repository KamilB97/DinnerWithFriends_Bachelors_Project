package com.example.dinnerapp.model.entity;

public class Message {

    private Integer messageId;
    private String messageTxt;
    private String date;

    public Message(Integer messageId, String messageTxt, String date) {
        this.messageId = messageId;
        this.messageTxt = messageTxt;
        this.date = date;
    }

    public Message(Integer messageId, String messageTxt) {
        this.messageId = messageId;
        this.messageTxt = messageTxt;
    }
    public Message( String messageTxt) {
        this.messageTxt = messageTxt;
    }

    public Message() {
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageTxt() {
        return messageTxt;
    }

    public void setMessageTxt(String messageTxt) {
        this.messageTxt = messageTxt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageTxt='" + messageTxt + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
