package com.androidexam.shubclassroom.viewmodel.class_detail.teacher;

import android.content.Context;
import android.util.Log;

import com.androidexam.shubclassroom.adapter.ExamTeacherItemAdapter;
import com.androidexam.shubclassroom.model.ClassDetail;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.class_detail.BaseClassDetailViewModel;

public class ExamViewModel extends BaseClassDetailViewModel {
    private ExamDto examDto;
    private String txtSearch;
    public static ExamTeacherItemAdapter adapter;
    public ExamViewModel(Context context, INavigation navigation, ExamDto examDto) {
        super(context, navigation);
        this.examDto = examDto;
    }
    public ExamViewModel(Context context, INavigation navigation) {
        super(context, navigation);
    }
    public ExamDto getExamDto() {
        return examDto;
    }

    public void setExamDto(ExamDto examDto) {
        this.examDto = examDto;
    }

    public static ExamTeacherItemAdapter getAdapter() {
        return adapter;
    }

    public static void setAdapter(ExamTeacherItemAdapter adapter) {
        ExamViewModel.adapter = adapter;
    }

    public String getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(String txtSearch) {
        this.txtSearch = txtSearch;
    }

    public void onClickBtnSearch() {
        adapter.filterList(txtSearch);
    }
    public void onClickSeeResult() {
        navigation.navigate(ClassDetailFragment.ShowExamResult.getValue());
    }
}
