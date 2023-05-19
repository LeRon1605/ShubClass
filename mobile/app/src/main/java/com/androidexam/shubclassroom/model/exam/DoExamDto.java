package com.androidexam.shubclassroom.model.exam;

public class DoExamDto {

    private String examDetailId;
    private String answer;

    public DoExamDto(String examDetailId, String answer) {
        this.examDetailId = examDetailId;
        this.answer = answer;
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
