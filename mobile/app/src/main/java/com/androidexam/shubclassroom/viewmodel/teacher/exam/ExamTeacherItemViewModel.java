package com.androidexam.shubclassroom.viewmodel.teacher.exam;

import androidx.databinding.BaseObservable;

import com.androidexam.shubclassroom.model.exam.ExamDto;

public class ExamTeacherItemViewModel extends BaseObservable {
    private ExamDto examDto;

    public void setExamDto(ExamDto examDto) {
        this.examDto = examDto;
    }

    public ExamDto getExamDto() {
        return this.examDto;
    }
}
