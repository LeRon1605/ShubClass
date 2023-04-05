package com.androidexam.shubclassroom.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ClassTeacherViewModel extends BaseObservable {
    private Context context;
    private MutableLiveData<Integer> mNavigate = new MutableLiveData<>();

    public ClassTeacherViewModel(Context context) {
        this.context = context;
    }
    public LiveData<Integer> getNavigate() {
        return mNavigate;
    }
    public void setNavigate(int nextComponentId) {
        mNavigate.setValue(nextComponentId);
    }

}
