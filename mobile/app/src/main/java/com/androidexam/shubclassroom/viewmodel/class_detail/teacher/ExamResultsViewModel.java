package com.androidexam.shubclassroom.viewmodel.class_detail.teacher;

import android.content.Context;

import com.androidexam.shubclassroom.model.exam_result.ExamResult;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.class_detail.BaseClassDetailViewModel;

public class ExamResultsViewModel extends BaseClassDetailViewModel {
    private ExamResult viewModel;
    public ExamResultsViewModel(Context context, INavigation navigation, ExamResult examResult) {
        super(context, navigation);
        this.viewModel = examResult;
    }

    public ExamResult getViewModel() {
        return viewModel;
    }

    public void setViewModel(ExamResult viewModel) {
        this.viewModel = viewModel;
    }
}
