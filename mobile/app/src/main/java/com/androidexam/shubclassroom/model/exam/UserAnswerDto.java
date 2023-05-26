package com.androidexam.shubclassroom.model.exam;

public class UserAnswerDto {
    private String userExamId;
    private String examDetailId;
    private String answer;

    public String getUserExamId() {
        return userExamId;
    }

    public void setUserExamId(String userExamId) {
        this.userExamId = userExamId;
    }

    public String getExamDetailId() {
        return examDetailId;
    }

    public void setExamDetailId(String examDetailId) {
        this.examDetailId = examDetailId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
