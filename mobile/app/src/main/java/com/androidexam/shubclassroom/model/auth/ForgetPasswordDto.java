package com.androidexam.shubclassroom.model.auth;

public class ForgetPasswordDto {
    private String email;

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValid() {
        return !this.email.isEmpty();
    }
}
