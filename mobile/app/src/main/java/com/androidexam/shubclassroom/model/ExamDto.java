package com.androidexam.shubclassroom.model;

import java.time.LocalDateTime;

public class ExamDto {
    private String id;
    private int state;
    private String type;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String classId;

    public ExamDto(String id, int state, String type, String name, LocalDateTime startTime, LocalDateTime endTime, String classId) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classId = classId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
