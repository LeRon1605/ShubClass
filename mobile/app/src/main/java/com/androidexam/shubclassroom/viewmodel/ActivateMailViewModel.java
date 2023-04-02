package com.androidexam.shubclassroom.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ActivateMailViewModel {

    private MutableLiveData<Integer> mNavigate = new MutableLiveData<>();

    public LiveData<Integer> getNavigate() {
        return mNavigate;
    }

    public void setNavigate(int nextComponentId) {
        mNavigate.setValue(nextComponentId);
    }

}
