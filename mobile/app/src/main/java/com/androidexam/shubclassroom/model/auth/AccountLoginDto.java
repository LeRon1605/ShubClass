package com.androidexam.shubclassroom.model.auth;

public class AccountLoginDto {
    private String email;
    private String password;

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
    public boolean isValid() {
        return !email.isEmpty() && !password.isEmpty();
    }
}
