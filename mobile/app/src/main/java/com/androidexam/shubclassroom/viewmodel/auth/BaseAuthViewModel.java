package com.androidexam.shubclassroom.viewmodel.auth;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.androidexam.shubclassroom.shared.AuthFragment;
import com.androidexam.shubclassroom.shared.INavigation;

public class BaseAuthViewModel extends BaseObservable {
    protected Context context;
    protected INavigation navigation;
    public BaseAuthViewModel(Context context, INavigation navigation) {
        this.context = context;
        this.navigation = navigation;
    }

    public void navigateTo(AuthFragment fragment) {
        navigation.navigate(fragment.getValue());
    }
}
