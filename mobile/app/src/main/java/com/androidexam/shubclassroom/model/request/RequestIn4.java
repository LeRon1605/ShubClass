package com.androidexam.shubclassroom.model.request;

public class RequestIn4 {
    private String state;
    private StudentRequest student;

    public RequestIn4(String state, StudentRequest student) {
        this.state = state;
        this.student = student;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public StudentRequest getStudent() {
        return student;
    }

    public void setStudent(StudentRequest student) {
        this.student = student;
    }
}
