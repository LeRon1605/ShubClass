package com.androidexam.shubclassroom.model;

public class Class {
    private String id;
    private String name;
    private String description;
    private String subjectName;
    private int numberOfStudent;

    public Class(String id, String name, String description, String subjectName, int numberOfStudent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subjectName = subjectName;
        this.numberOfStudent = numberOfStudent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }
}
