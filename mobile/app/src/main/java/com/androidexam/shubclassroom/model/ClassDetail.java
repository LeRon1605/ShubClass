package com.androidexam.shubclassroom.model;

public class ClassDetail {
    private String id;
    private String name;
    private String description;
    private String subjectName;
    private int numberOfStudent;
    private String teacherId;
    private String createAt;
    private String updateAt;

    public ClassDetail(String id, String name, String description, String subjectName, int numberOfStudent, String teacherId, String createAt, String updateAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subjectName = subjectName;
        this.numberOfStudent = numberOfStudent;
        this.teacherId = teacherId;
        this.createAt = createAt;
        this.updateAt = updateAt;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
