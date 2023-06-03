package com.androidexam.shubclassroom.view.class_detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.model.ClassDetail;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.FragmentIndex;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.class_detail.class_detail.student.ExamStudentFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.student.ShowStudentInClassStudentFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ClassDetailTeacherFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ExamResultsTeacherFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ExamTeacherFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ShowAllRequestInClassFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.teacher.ShowStudentInClassFragment;
import com.androidexam.shubclassroom.view.class_detail.class_detail.student.ClassDetailStudentFragment;
import com.androidexam.shubclassroom.view.teacher.exam.CreateExamTeacherFragment;
import com.androidexam.shubclassroom.view.teacher.exam.ListExamTeacherFragment;

import retrofit2.Call;

public class ClassDetailActivity extends AppCompatActivity implements INavigation {
    private ClassCreateDto classCreateDto;
    private String idClass;
    private String nameClass;
    private String nameStudent;
    private String examId;
    private ClassApiService classApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);

        Intent intent = getIntent();
        idClass = intent.getStringExtra("classId");

        classApiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);

        Call<ClassDetail> call = classApiService.getClassDetail(SharedPreferencesManager.getInstance(this).getAccessToken(), idClass);
        call.enqueue(new ApiCallback<ClassDetail, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(ClassDetail responseObject) {
                classCreateDto = new ClassCreateDto(responseObject.getId(), responseObject.getName(), responseObject.getName(), responseObject.getSubjectName(), responseObject.getNumberOfStudent());
                nameClass = responseObject.getName();

                examId = intent.getStringExtra("examId");
                if (examId != null && !examId.isEmpty()) {
                    navigate(ClassDetailFragment.ShowExamResult.getValue());
                    return;
                }

                Bundle bundle = intent.getBundleExtra("myBundle");
                if(bundle != null) {
                    int fragmentIndex = bundle.getInt("FragmentIndex");
                    if(fragmentIndex == FragmentIndex.Teacher.getValue()) {
                        navigate(ClassDetailFragment.TeacherClassDetail.getValue());
                    } else {
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
            public void handleFailure(MessageResponse errorResponse) {

            }
        });
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
            transaction.replace(R.id.fr_classdetail, new ExamResultsTeacherFragment(this, examId));
        } else if (id == ClassDetailFragment.StudentExam.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ExamStudentFragment(this, idClass));
        } else if (id == ClassDetailFragment.ShowStudentInClassByStudent.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ShowStudentInClassStudentFragment(this, idClass));
        } else if (id == ClassDetailFragment.TeacherExam.getValue()) {
            transaction.replace(R.id.fr_classdetail, new ListExamTeacherFragment(this, classCreateDto.getId()));
        } else if (id == ClassDetailFragment.TeacherCreateExam.getValue()) {
            transaction.replace(R.id.fr_classdetail, new CreateExamTeacherFragment(this, classCreateDto.getId()));
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }
}