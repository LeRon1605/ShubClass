package com.androidexam.shubclassroom.viewmodel.class_detail.teacher;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;

import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.class_detail.BaseClassDetailViewModel;

public class ClassDetailTeacherViewModel extends BaseClassDetailViewModel {
    private ClassCreateDto classCreateDto;

    public ClassDetailTeacherViewModel(Context context, INavigation navigation, ClassCreateDto classCreateDto) {
        super(context, navigation);
        this.classCreateDto = classCreateDto;
    }

    public ClassCreateDto getClassCreateDto() {
        return classCreateDto;
    }

    public void setClassCreateDto(ClassCreateDto classCreateDto) {
        this.classCreateDto = classCreateDto;
    }
    public void onClickMemberButton() {
        navigateTo(ClassDetailFragment.ShowStudentOfClass);
    }
    public void onClickShowAllRequest() {
        navigateTo(ClassDetailFragment.ShowAllRequestOfClass);
    }
    public void onClickSeePointChart() {
        navigateTo(ClassDetailFragment.ShowAllExamInClass);
    }
}
