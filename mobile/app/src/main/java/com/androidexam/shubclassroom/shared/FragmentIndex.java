package com.androidexam.shubclassroom.shared;

public enum FragmentIndex {
    Teacher(1), Student(0);

    private final int value;

    FragmentIndex(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
