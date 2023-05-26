package com.androidexam.shubclassroom.model;

public class SummaryIn4Student {
    private double average;
    private int completedExam;
    private int numberOfExams;

    public SummaryIn4Student(double average, int completedExam, int numberOfExams) {
        this.average = average;
        this.completedExam = completedExam;
        this.numberOfExams = numberOfExams;
    }

    public String getAverage() {
        return String.valueOf(average);
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getCompletedExam() {
        return String.valueOf(completedExam);
    }

    public void setCompletedExam(int completedExam) {
        this.completedExam = completedExam;
    }

    public String getNumberOfExams() {
        return String.valueOf(numberOfExams);
    }

    public void setNumberOfExams(int numberOfExams) {
        this.numberOfExams = numberOfExams;
    }
}
