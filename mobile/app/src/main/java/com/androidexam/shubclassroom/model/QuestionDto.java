package com.androidexam.shubclassroom.model;

public class QuestionDto {

    private String id;
    private String question;
    private String[] answers;

    public QuestionDto(String id, String question, String[] answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
