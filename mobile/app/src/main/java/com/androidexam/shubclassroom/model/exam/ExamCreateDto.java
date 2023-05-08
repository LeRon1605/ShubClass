package com.androidexam.shubclassroom.model.exam;

public class ExamCreateDto {
    private String type;
    private String name;
    private String startTime;
    private String endTime;
    private String details;
    private String classId;

    public ExamCreateDto() {

    }

    public ExamCreateDto(String classId) {
        this.classId = classId;
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
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public ExamCreateDto toBody() {
        ExamCreateDto examCreateDto = new ExamCreateDto();
        examCreateDto.name = this.name;
        examCreateDto.type = this.type.equals("Bài tập") ? "Exercise" : "Test";
        examCreateDto.startTime = this.startTime;
        examCreateDto.endTime = this.endTime;
        examCreateDto.classId = this.classId;
        examCreateDto.details = this.details;
        return examCreateDto;
    }

    public boolean isValid() {
        return !name.isEmpty() && !type.isEmpty() && !details.isEmpty() && !classId.isEmpty() && !startTime.isEmpty() && !endTime.isEmpty();
    }
}
