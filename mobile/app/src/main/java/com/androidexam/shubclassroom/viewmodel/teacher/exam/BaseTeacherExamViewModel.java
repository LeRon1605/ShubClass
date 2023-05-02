package com.androidexam.shubclassroom.viewmodel.teacher.exam;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.androidexam.shubclassroom.shared.TeacherExamFragment;
import com.androidexam.shubclassroom.shared.INavigation;

public class BaseTeacherExamViewModel extends BaseObservable {
    protected Context context;
    protected INavigation navigation;
    protected String classId;
    public BaseTeacherExamViewModel(Context context, INavigation navigation, String classId) {
        this.context = context;
        this.navigation = navigation;
        this.classId = classId;
    }

    public void navigateTo(TeacherExamFragment fragment) {
        navigation.navigate(fragment.getValue());
    }
}
