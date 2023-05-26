package com.androidexam.shubclassroom.shared;

public enum ClassDetailFragment {
    // student
    StudentClassDetail(0), StudentExam(1), StudentMember(2),

    // teacher
    TeacherClassDetail(3), TeacherExam(4), TeacherMember(5),

    // same
    ShowStudentOfClass(6), ShowAllRequestOfClass(7), ShowAllExamInClass(8), ShowExamResult(9);
    private final int value;

    private ClassDetailFragment(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
