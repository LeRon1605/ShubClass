package com.androidexam.shubclassroom.viewmodel.class_detail.student;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.SummaryIn4Student;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.model.student.StudentExitClass;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.student.HomeStudentActivity;
import com.androidexam.shubclassroom.viewmodel.class_detail.BaseClassDetailViewModel;

import retrofit2.Call;

public class ClassDetailStudentViewModel extends BaseClassDetailViewModel {
    private String nameStudent;
    private String idClass;
    private String nameClass;
    private SummaryIn4Student summaryIn4Student;
    private ClassApiService apiService;
    private String token;
    private FragmentManager fm;
    public ClassDetailStudentViewModel(Context context, INavigation navigation, String idClass, String nameClass, SummaryIn4Student summaryIn4Student, String nameStudent) {
        super(context, navigation);
        this.idClass = idClass;
        this.nameClass = nameClass;
        this.summaryIn4Student = summaryIn4Student;
        this.nameStudent = nameStudent;
        token = SharedPreferencesManager.getInstance(context).getAccessToken();
        apiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
    }
    public ClassDetailStudentViewModel(Context context, INavigation navigation, FragmentManager fm) {
        super(context, navigation);
        this.fm = fm;
    }
    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public SummaryIn4Student getSummaryIn4Student() {
        return summaryIn4Student;
    }

    public void setSummaryIn4Student(SummaryIn4Student summaryIn4Student) {
        this.summaryIn4Student = summaryIn4Student;
    }

    public void onStudentExamClicked() {
        navigation.navigate(ClassDetailFragment.StudentExam.getValue());
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }
    public void onClickArrowBack() {
        context.startActivity(new Intent(context, HomeStudentActivity.class));
    }
    public void onClickExitClass() {
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Xác nhận!")
                .setMessage("Bạn chắc chắn muốn thoát lớp?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<StudentExitClass> call = apiService.exitClass("Bear " + token, idClass);
                        call.enqueue(new ApiCallback<StudentExitClass, MessageResponse>(MessageResponse.class) {
                            @Override
                            public void handleSuccess(StudentExitClass responseObject) {
                                Toast.makeText(context, "Rời lớp thành công!", Toast.LENGTH_SHORT).show();
                                context.startActivity(new Intent(context, HomeStudentActivity.class));
                            }

                            @Override
                            public void handleFailure(MessageResponse errorResponse) {
                                Toast.makeText(context, "Có lỗi, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    public void onClickArrowBackShowStudent() {
        if(fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();;
        }
    }
    public void onClickShowStudentInClass() {
        navigateTo(ClassDetailFragment.ShowStudentInClassByStudent);
    }
}
