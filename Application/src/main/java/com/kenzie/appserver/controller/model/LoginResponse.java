package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {


    @JsonProperty("id")
    private String message;

    @JsonProperty("success")
    private boolean success;


    @JsonProperty("token")
    private String token;

    public LoginResponse(String message, boolean success, String token) {
        this.message = message;
        this.success = success;
        this.token = token;
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
