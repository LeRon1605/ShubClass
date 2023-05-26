package com.androidexam.shubclassroom.model.exam;

public class SessionExamDto {
    private String id;
    private String examId;
    private String startAt;
    private String endAt;
    private UserAnswerDto[] userAnswers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public UserAnswerDto[] getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(UserAnswerDto[] userAnswers) {
        this.userAnswers = userAnswers;
    }
}

