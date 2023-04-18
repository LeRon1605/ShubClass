package com.androidexam.shubclassroom.model;

public class Account {
    private String id;
    private String name;
    private String avatar;
    private int state;
    private String role;

    public Account(String id, String name, String avatar, int state, String role) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.state = state;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
