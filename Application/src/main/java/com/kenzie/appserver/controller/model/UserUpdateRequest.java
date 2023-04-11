package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUpdateRequest {

    @JsonProperty("email")

    private final String email;

    @JsonProperty("password")

    private final String password;

    public UserUpdateRequest(String email, String password) {
        this.email = email;


        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
