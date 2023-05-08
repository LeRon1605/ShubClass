package com.androidexam.shubclassroom.model.exam;

import com.androidexam.shubclassroom.utilities.DateHelper;

import java.util.Date;

public class ExamDto {
    private String id;
    private int state;
    private String type;
    private String name;
    private String startTime;
    private String endTime;
    private String classId;

    public ExamDto(String id, int state, String type, String name, String startTime, String endTime, String classId) {
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
    public String getStateString() {
        return state == 0 ? "Chưa mở" : (state == 1 ? "Đang diễn ra" : "Đã kết thúc");
    }
}
