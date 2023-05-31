package com.androidexam.shubclassroom.viewmodel.class_detail.teacher;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.view.teacher.HomeTeacherActivity;
import com.androidexam.shubclassroom.viewmodel.class_detail.BaseClassDetailViewModel;

public class ClassDetailTeacherViewModel extends BaseClassDetailViewModel {
    private ClassCreateDto classCreateDto;
    private FragmentManager fm;
    public ClassDetailTeacherViewModel(Context context, INavigation navigation, ClassCreateDto classCreateDto) {
        super(context, navigation);
        this.classCreateDto = classCreateDto;
    }
    public ClassDetailTeacherViewModel(Context context, INavigation navigation, ClassCreateDto classCreateDto, FragmentManager fm) {
        super(context, navigation);
        this.fm = fm;
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
    public void onClickArrowBackActivity() {
        context.startActivity(new Intent(context, HomeTeacherActivity.class));
    }
    public void onClickArrowBack() {
        if(fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }
    }
    public void onClickExerciseButton() {
        navigateTo(ClassDetailFragment.TeacherExam);
    }
}
