package com.androidexam.shubclassroom.viewmodel;

import android.text.TextUtils;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidexam.shubclassroom.BR;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.LoginAPIService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.AccountLoginDto;
import com.androidexam.shubclassroom.model.MessageResponse;

import retrofit2.Call;

public class ForgetPassViewModel extends BaseObservable {

    private String email;
    private MutableLiveData<Integer> mNavigate = new MutableLiveData<>();

    @Bindable
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public LiveData<Integer> getNavigate() {
        return mNavigate;
    }

    public void setNavigate(int nextComponentId) {
        mNavigate.setValue(nextComponentId);
    }

    public void onContinueClicked() {

//    verify email

    }
}
