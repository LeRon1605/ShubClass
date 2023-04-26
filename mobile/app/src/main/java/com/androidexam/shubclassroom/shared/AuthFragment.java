package com.androidexam.shubclassroom.shared;

public enum AuthFragment {
    Login(0), Register(1), ForgetPassword(2), Notify(3), ActivateAccount(4);

    private final int value;

    private AuthFragment(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
