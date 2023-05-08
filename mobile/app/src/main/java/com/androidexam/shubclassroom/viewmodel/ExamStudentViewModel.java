package com.androidexam.shubclassroom.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.BaseObservable;

import com.androidexam.shubclassroom.shared.INavigation;


public class ExamStudentViewModel extends BaseObservable {
    private Context context;
    private INavigation navigation;

    public ExamStudentViewModel(Context context, INavigation navigation) {
        this.context = context;
        this.navigation = navigation;
    }
    public void onBackClicked() {

    }

}
