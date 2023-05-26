package com.androidexam.shubclassroom.shared;

public enum ClassDetailFragment {
    StudentClassDetail(0), StudentExam(1), StudentMember(2), TeacherClassDetail(3), TeacherExam(4), TeacherMember(5), ShowStudentOfClass(6), ShowAllRequestOfClass(7);
    private final int value;

    private ClassDetailFragment(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
