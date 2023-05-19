package com.androidexam.shubclassroom.viewmodel.student.exam;

import android.content.Context;

import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.view.student.exam.ExamStudentFragment;

public class BaseStudentExamViewModel {

    protected Context context;
    protected INavigation navigation;
    protected String classId;

    public BaseStudentExamViewModel(Context context, INavigation navigation, String classId) {
        this.context = context;
        this.navigation = navigation;
        this.classId = classId;
    }

//    public void navigateTo(ExamStudentFragment fragment)
//    {
//        navigation.navigate(fragment.getValue());
//    }
}
