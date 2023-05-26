package com.androidexam.shubclassroom.model.exam_result;

import android.util.Log;

import com.androidexam.shubclassroom.utilities.DateHelper;

public class ExamResult {
    private Exam exam;
    private User user;
    private double points;
    private String startAt;
    private String endAt;

    public ExamResult(Exam exam, User user, double points, String startAt, String endAt) {
        this.exam = exam;
        this.user = user;
        this.points = points;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getStartAt() {
        return DateHelper.parseOsiToString(startAt);
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return DateHelper.parseOsiToString(endAt);
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }
}
