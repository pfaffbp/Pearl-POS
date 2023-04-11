package com.kenzie.appserver.service.model;

import com.kenzie.appserver.repositories.model.UserRecord;

import java.util.Objects;

public class User {

    private String password;
    private String email;

    public User() {}

    public User(UserRecord record) {
        this.password = record.getPassword();
        this.email = record.getEmail();
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
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

    public UserRecord toUserRecord() {
        return new UserRecord(email, password);
    }
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }


}
