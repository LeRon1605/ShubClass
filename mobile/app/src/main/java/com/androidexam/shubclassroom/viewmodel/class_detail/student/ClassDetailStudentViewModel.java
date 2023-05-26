package com.androidexam.shubclassroom.viewmodel.class_detail.student;

import android.content.Context;
import android.content.Intent;

import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.model.SummaryIn4Student;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.view.student.HomeStudentActivity;
import com.androidexam.shubclassroom.viewmodel.class_detail.BaseClassDetailViewModel;

public class ClassDetailStudentViewModel extends BaseClassDetailViewModel {
    private String nameStudent;
    private String idClass;
    private String nameClass;
    private SummaryIn4Student summaryIn4Student;
    public ClassDetailStudentViewModel(Context context, INavigation navigation, String idClass, String nameClass, SummaryIn4Student summaryIn4Student, String nameStudent) {
        super(context, navigation);
        this.idClass = idClass;
        this.nameClass = nameClass;
        this.summaryIn4Student = summaryIn4Student;
        this.nameStudent = nameStudent;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public SummaryIn4Student getSummaryIn4Student() {
        return summaryIn4Student;
    }

    public void setSummaryIn4Student(SummaryIn4Student summaryIn4Student) {
        this.summaryIn4Student = summaryIn4Student;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }
    public void onClickArrowBack() {
        context.startActivity(new Intent(context, HomeStudentActivity.class));
    }
}
