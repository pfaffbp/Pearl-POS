package com.kenzie.appserver.controller.model;

public class UserCreateRequest {

    private String username;
    private String password;
    private String email;

    public UserCreateRequest() {}

    public UserCreateRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserCreateRequest(String s, String test3, String password3, String s1) {
        this.username = s;
        this.password = password3;
        this.email = s1;
        this.email = test3;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
