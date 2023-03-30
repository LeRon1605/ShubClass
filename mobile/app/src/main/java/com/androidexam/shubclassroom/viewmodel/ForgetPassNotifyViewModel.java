package com.androidexam.shubclassroom.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForgetPassNotifyViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToActivity = new MutableLiveData<>();

    public LiveData<Boolean> getNavigateToActivity() {
        return navigateToActivity;
    }

    public void onBackLoginClicked() {
        navigateToActivity.setValue(true);
    }
}
