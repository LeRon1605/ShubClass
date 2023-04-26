package com.androidexam.shubclassroom.viewmodel.auth;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidexam.shubclassroom.shared.AuthFragment;
import com.androidexam.shubclassroom.shared.INavigation;

public class ForgetPassNotifyViewModel extends BaseAuthViewModel {
    public ForgetPassNotifyViewModel(Context context, INavigation navigation) {
        super(context, navigation);
    }

    public void onBackLoginClicked() {
        navigateTo(AuthFragment.Login);
    }
}
