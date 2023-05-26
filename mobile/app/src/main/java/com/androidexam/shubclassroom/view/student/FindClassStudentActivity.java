package com.androidexam.shubclassroom.view.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ActivityStudentFindClassBinding;
import com.androidexam.shubclassroom.viewmodel.FindClassPerformedStudentViewModel;

public class FindClassStudentActivity extends AppCompatActivity {
    private ActivityStudentFindClassBinding binding;
    private FindClassPerformedStudentViewModel findClassPerformedStudentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findClassPerformedStudentViewModel = new FindClassPerformedStudentViewModel(getApplicationContext());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_find_class);
        binding.setFindClassModel(findClassPerformedStudentViewModel);
        setContentView(binding.getRoot());
    }
}