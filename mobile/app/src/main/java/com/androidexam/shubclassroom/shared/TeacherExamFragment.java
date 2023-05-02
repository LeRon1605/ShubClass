package com.androidexam.shubclassroom.shared;

public enum TeacherExamFragment {
    ListExamTeacher(0), CreateExam(1);

    private final int value;

    private TeacherExamFragment(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
