package com.example.dinnerapp.model.entity;

public class Credencials {
    private String username;
    private String password;

    public Credencials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Credencials() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
