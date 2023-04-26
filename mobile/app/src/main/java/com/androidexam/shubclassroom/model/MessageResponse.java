package com.androidexam.shubclassroom.model;

public class MessageResponse {
    private String message;
<<<<<<< HEAD
=======
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
>>>>>>> feature(android)/home_view_student

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}