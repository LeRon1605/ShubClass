package com.androidexam.shubclassroom.viewmodel.class_detail;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.androidexam.shubclassroom.shared.AuthFragment;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.INavigation;

import io.reactivex.rxjava3.internal.operators.parallel.ParallelRunOn;

public class BaseClassDetailViewModel extends BaseObservable {
    protected Context context;
    protected INavigation navigation;

    public BaseClassDetailViewModel(Context context, INavigation navigation) {
        this.context = context;
        this.navigation = navigation;
    }
    public void navigateTo(ClassDetailFragment fragment) {
        navigation.navigate(fragment.getValue());
    }
}
