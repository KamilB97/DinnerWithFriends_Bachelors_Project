package com.example.dinnerapp.model.send;

import java.util.List;

public class CustomConversation {

    private Integer id;
    private String name;
    private Integer customCreated;
    private List<ProfileBrief> participatns;

    public CustomConversation() {
    }

    public CustomConversation(Integer id, String name, Integer customCreated) {
        this.id = id;
        this.name = name;
        this.customCreated = customCreated;
    }
    public CustomConversation(Integer id, String name, Integer customCreated, List<ProfileBrief> participatns) {
        this.id = id;
        this.name = name;
        this.customCreated = customCreated;
        this.participatns = participatns;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustomCreated() {
        return customCreated;
    }

    public void setCustomCreated(Integer customCreated) {
        this.customCreated = customCreated;
    }

    public List<ProfileBrief> getParticipatns() {
        return participatns;
    }

    public void setParticipatns(List<ProfileBrief> participatns) {
        this.participatns = participatns;
    }
}
