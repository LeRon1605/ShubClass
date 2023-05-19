package com.androidexam.shubclassroom.viewmodel.student.exam;

import androidx.databinding.BaseObservable;

import com.androidexam.shubclassroom.model.exam.ExamDto;

public class ExamStudentItemViewModel extends BaseObservable {
    private ExamDto examDto;
    public void setExamDto(ExamDto examDto){
        this.examDto = examDto;
    }
    public ExamDto getExamDto()
    {
        return this.examDto;
    }

}
