package com.androidexam.shubclassroom.viewmodel;

import com.androidexam.shubclassroom.model.student.StudentDTO;

public class ClassMemberItemViewModel {
    private StudentDTO studentDTO;

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public StudentDTO getStudentDTO() {
        return this.studentDTO;
    }
}
