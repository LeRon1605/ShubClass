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
import com.androidexam.shubclassroom.view.class_detail.class_detail.student.ExamStudentFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.student.ShowStudentInClassStudentFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ClassDetailTeacherFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ExamResultsTeacherFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ExamTeacherFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ShowAllRequestInClassFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ShowStudentInClassFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.student.ClassDetailStudentFragment;

public class ClassDetailActivity extends AppCompatActivity implements INavigation {
    private ClassCreateDto classCreateDto;
    private String idClass;
    private String nameClass;
    private String nameStudent;
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
                navigate(ClassDetailFragment.TeacherClassDetail.getValue());
            } else {
                nameStudent = bundle.getString("nameStudent");
                idClass = bundle.getString("idClass");
                Log.d("TAG", "idClassClassDetailActivity: " + idClass);
                nameClass = bundle.getString("nameClass");
                int id = bundle.getInt("fragment");
                if (id != 0) {
                    navigate(id);
                } else {
                    navigate(ClassDetailFragment.StudentClassDetail.getValue());
                }
            }
        }
    }

    @Override
    public void navigate(int id) {
        FragmentTransaction transaction  = getSupportFragmentManager().beginTransaction();
        if(id == ClassDetailFragment.TeacherClassDetail.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ClassDetailTeacherFragment(this, classCreateDto));
        } else if(id == ClassDetailFragment.StudentClassDetail.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ClassDetailStudentFragment(this, idClass,nameClass, nameStudent));
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
        } else if (id == ClassDetailFragment.StudentExam.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ExamStudentFragment(this, idClass));
        } else if (id == ClassDetailFragment.ShowStudentInClassByStudent.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ShowStudentInClassStudentFragment(this, idClass));
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }
}