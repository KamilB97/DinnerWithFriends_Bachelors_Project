package com.example.dinnerapp.model.entity;

public class DietaryPreference {
    private Integer id;
    private String name;

    public DietaryPreference() {
    }

    public DietaryPreference(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
