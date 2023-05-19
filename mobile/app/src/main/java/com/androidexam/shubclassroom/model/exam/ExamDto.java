package com.androidexam.shubclassroom.model.exam;

import com.androidexam.shubclassroom.utilities.DateHelper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExamDto implements Serializable {
    private String id;
    private String name;
    private String type;
    private int state;
    private String classId;
    private String startTime;
    private String endTime;
    private String createAt;
    private String updateAt;
    private boolean isDone;

    public ExamDto(String id, String name, String type, int state, String classId, String startTime, String endTime, String createAt, String updateAt, boolean isDone) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.state = state;
        this.classId = classId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.isDone = isDone;
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

    public String getStartTime() {
        return DateHelper.parseOsiToString(this.startTime);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return DateHelper.parseOsiToString(this.endTime);
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
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
