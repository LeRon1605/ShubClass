package com.androidexam.shubclassroom.model.exam;

public class SessionExamDto {
    private String id;
    private String userId;
    private String examId;
    private String startAt;
    private String endAt;
    private String[] userAnswers;

    public SessionExamDto(String id, String userId, String examId, String startAt, String endAt, String[] userAnswers) {
        this.id = id;
        this.userId = userId;
        this.examId = examId;
        this.startAt = startAt;
        this.endAt = endAt;
        this.userAnswers = userAnswers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String[] getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(String[] userAnswers) {
        this.userAnswers = userAnswers;
    }
}

