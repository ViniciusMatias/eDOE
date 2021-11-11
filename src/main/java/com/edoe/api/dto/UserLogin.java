package com.edoe.api.dto;

import com.edoe.api.entity.User;

public class UserLogin {

    private String email;
    private String password;


    public UserLogin(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public UserLogin() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
