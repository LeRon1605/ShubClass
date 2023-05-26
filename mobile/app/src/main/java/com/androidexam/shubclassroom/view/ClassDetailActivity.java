package com.androidexam.shubclassroom.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.view.student.exam.ExamStudentFragment;

public class ClassDetailActivity extends AppCompatActivity implements INavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_class_detail);

        int id = getIntent().getIntExtra("fragment", 1);

        navigate(id);
    }

    @Override
    public void navigate(int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (id == ClassDetailFragment.StudentExam.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ExamStudentFragment(this, "1"));
        }
        transaction.commit();
    }
}