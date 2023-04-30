package com.androidexam.shubclassroom.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidexam.shubclassroom.view.teacher.CreateClassActivity;

public class ClassTeacherViewModel extends BaseObservable {
    private Context context;

    public ClassTeacherViewModel(Context context) {
        this.context = context;
    }
    public void onlickCreateClass() {
        Intent intent = new Intent(context, CreateClassActivity.class);
        context.startActivity(intent);
    }
}
