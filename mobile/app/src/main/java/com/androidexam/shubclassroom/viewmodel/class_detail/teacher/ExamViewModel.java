package com.androidexam.shubclassroom.viewmodel.class_detail.teacher;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.androidexam.shubclassroom.adapter.ExamTeacherItemAdapter;
import com.androidexam.shubclassroom.model.ClassDetail;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.view.class_detail.ClassDetailActivity;
import com.androidexam.shubclassroom.viewmodel.class_detail.BaseClassDetailViewModel;

public class ExamViewModel extends BaseClassDetailViewModel {
    private ExamDto examDto;
    private String txtSearch;
    private FragmentManager fm;
    public static ExamTeacherItemAdapter adapter;
    public ExamViewModel(Context context, INavigation navigation, ExamDto examDto) {
        super(context, navigation);
        this.examDto = examDto;
    }
    public ExamViewModel(Context context, INavigation navigation, FragmentManager fm) {
        super(context, navigation);
        this.fm = fm;
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
        Intent intent = new Intent(context, ClassDetailActivity.class);
        intent.putExtra("examId", examDto.getId());
        intent.putExtra("classId", examDto.getClassId());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public void showErr() {
        Toast.makeText(context, "Bài kiểm tra chưa kết thúc hoặc chưa mở", Toast.LENGTH_SHORT).show();
    }
    public void onClickArrowBack() {
        navigateTo(ClassDetailFragment.ShowAllExamInClass);
    }
}
