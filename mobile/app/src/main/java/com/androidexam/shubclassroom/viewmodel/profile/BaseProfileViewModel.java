package com.androidexam.shubclassroom.viewmodel.profile;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.INavigation;

public class BaseProfileViewModel extends BaseObservable {
    protected Context context;

    public BaseProfileViewModel(Context context) {
        this.context = context;
    }
}
