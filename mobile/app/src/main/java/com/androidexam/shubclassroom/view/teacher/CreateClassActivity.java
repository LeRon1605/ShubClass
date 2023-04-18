package com.androidexam.shubclassroom.view.teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ActivityCreateClassBinding;
import com.androidexam.shubclassroom.viewmodel.CreateClassViewModel;

public class CreateClassActivity extends AppCompatActivity {
    ActivityCreateClassBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_class);
        CreateClassViewModel createClassViewModel = new CreateClassViewModel(getApplicationContext());
        binding.setCreateClassViewModel(createClassViewModel);
    }
}