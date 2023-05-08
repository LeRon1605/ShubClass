package com.androidexam.shubclassroom.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.androidexam.shubclassroom.shared.INavigation;

public class BaseClassMemberViewModel extends BaseObservable {
    protected Context context;
    protected INavigation navigation;
    protected String classId;

    public BaseClassMemberViewModel(Context context, INavigation navigation, String classId) {
        this.context = context;
        this.navigation = navigation;
        this.classId = classId;
    }

//    public void navigateTo()
}
