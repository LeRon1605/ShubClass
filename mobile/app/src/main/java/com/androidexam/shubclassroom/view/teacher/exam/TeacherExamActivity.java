package com.androidexam.shubclassroom.view.teacher.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.shared.TeacherExamFragment;

public class TeacherExamActivity extends AppCompatActivity implements INavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_exam);

        navigate(TeacherExamFragment.ListExamTeacher.getValue());
    }

    @Override
    public void navigate(int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (id == TeacherExamFragment.CreateExam.getValue()) {
            transaction.replace(R.id.fr_teacher_exam, new CreateExamTeacherFragment(this, "1"));
//        } else if (id == TeacherExamFragment.ListExamTeacher.getValue()) {
//            transaction.replace(R.id.fr_teacher_exam, new ListExamTeacherFragment(this, "1"));
//        }
            transaction.commit();
        }
    }
}