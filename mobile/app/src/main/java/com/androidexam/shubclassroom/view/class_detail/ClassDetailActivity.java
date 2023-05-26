package com.androidexam.shubclassroom.view.class_detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.FragmentIndex;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ClassDetailTeacherFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ExamResultsTeacherFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ExamTeacherFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ShowAllRequestInClassFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ShowStudentInClassFragment;
import com.androidexam.shubclassroom.view.student.ClassDetailStudentFragment;

public class ClassDetailActivity extends AppCompatActivity implements INavigation {
    private ClassCreateDto classCreateDto;
    private String idClass;
    private String nameClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);
        Bundle bundle  = getIntent().getBundleExtra("myBundle");
        if(bundle != null) {
            int fragmentIndex = bundle.getInt("FragmentIndex");
            Log.d("TAG", "FragmentIndex: " + fragmentIndex);
            if(fragmentIndex == FragmentIndex.Teacher.getValue()) {
                classCreateDto = (ClassCreateDto) bundle.getSerializable("Class");
//                ClassDetailTeacherViewModel viewModel  = new ClassDetailTeacherViewModel(getApplicationContext(), classCreateDto);
                navigate(ClassDetailFragment.TeacherClassDetail.getValue());
            } else {
                idClass = bundle.getString("idClass");
                nameClass = bundle.getString("nameClass");
                navigate(ClassDetailFragment.StudentClassDetail.getValue());
            }
        }
    }

    @Override
    public void navigate(int id) {
        FragmentTransaction transaction  = getSupportFragmentManager().beginTransaction();
        if(id == ClassDetailFragment.TeacherClassDetail.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ClassDetailTeacherFragment(this, classCreateDto));
        } else if(id == ClassDetailFragment.StudentClassDetail.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ClassDetailStudentFragment(this, idClass,nameClass));
        }
        else if(id == ClassDetailFragment.ShowStudentOfClass.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ShowStudentInClassFragment(this, classCreateDto));
        }
        else if(id == ClassDetailFragment.ShowAllRequestOfClass.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ShowAllRequestInClassFragment(this, classCreateDto.getId()));
        } else if(id == ClassDetailFragment.ShowAllExamInClass.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ExamTeacherFragment(this, classCreateDto.getId()));
        } else if(id == ClassDetailFragment.ShowExamResult.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ExamResultsTeacherFragment(this, classCreateDto.getId()));
        }
        transaction.commit();
    }
}