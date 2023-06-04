package com.androidexam.shubclassroom.viewmodel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.androidexam.shubclassroom.BR;
import com.androidexam.shubclassroom.adapter.ItemAdapter;
import com.androidexam.shubclassroom.adapter.ItemSearchAdapter;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RequestApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.model.ClassDetail;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.student.StudentExitClass;
import com.androidexam.shubclassroom.shared.FragmentIndex;
import com.androidexam.shubclassroom.utilities.DecodeToken;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.class_detail.ClassDetailActivity;
import com.androidexam.shubclassroom.view.student.BottomSheetClassStudentFragment;
import com.androidexam.shubclassroom.view.student.FindClassStudentActivity;
import com.androidexam.shubclassroom.view.student.HomeStudentActivity;
import com.androidexam.shubclassroom.view.teacher.BottomSheetClassTeacherFragment;
import com.androidexam.shubclassroom.view.teacher.CreateClassActivity;

import java.io.Serializable;

import retrofit2.Call;

public class ClassItemViewModel extends BaseObservable implements Serializable {
    private ClassDetail classModel;
    private ClassApiService classApiService;
    private RequestApiService requestApiService;
    private BottomSheetClassTeacherFragment bottomSheetClassTeacherFragment;
    private BottomSheetClassStudentFragment bottomSheetClassStudentFragment;
    private Context context;
    private String textSearch;
    private String token;
    public static ItemAdapter adapter;
    public static ItemSearchAdapter itemSearchAdapter;

    public ClassItemViewModel(Context context, ClassDetail classModel) {
        this.context = context;
        classApiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
        requestApiService = RetrofitClient.getRetrofitInstance().create(RequestApiService.class);
        token = SharedPreferencesManager.getInstance(context).getAccessToken();
        this.classModel = classModel;
    }

    public ClassItemViewModel(Context context) {
        this.context = context;
    }

    public ClassDetail getClassModel() {
        return classModel;
    }

    public void setClassModel(ClassDetail classModel) {
        this.classModel = classModel;
    }

    public BottomSheetClassTeacherFragment getBottomSheetClassTeacherFragment() {
        return bottomSheetClassTeacherFragment;
    }

    public void setBottomSheetClassTeacherFragment(BottomSheetClassTeacherFragment bottomSheetClassTeacherFragment) {
        this.bottomSheetClassTeacherFragment = bottomSheetClassTeacherFragment;
    }

    public BottomSheetClassStudentFragment getBottomSheetClassStudentFragmentFragment() {
        return bottomSheetClassStudentFragment;
    }

    public void setBottomSheetClassStudentFragment(BottomSheetClassStudentFragment bottomSheetClassStudentFragmentFragment) {
        this.bottomSheetClassStudentFragment = bottomSheetClassStudentFragmentFragment;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void onClickCloseBottomSheetTeacher() {
        this.bottomSheetClassTeacherFragment.dismiss();
    }

    public void onClickCloseBottomSheetStudent() {
        this.bottomSheetClassStudentFragment.dismiss();
    }

    public void onClickDeleteClass() {
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Xác nhận!")
                .setMessage("Bạn chắc chắn muốn xoá?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<MessageResponse> call = classApiService.deleteClass("Bear " + token, classModel.getId());
                        call.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
                            @Override
                            public void handleSuccess(MessageResponse responseObject) {
                                Toast.makeText(context, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void handleFailure(MessageResponse errorResponse) {
                                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void onClickExitClass() {
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Xác nhận!")
                .setMessage("Bạn chắc chắn muốn thoát lớp?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<StudentExitClass> call = classApiService.exitClass("Bear " + token, classModel.getId());
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

    public void onclickCreateClass() {
        Intent intent = new Intent(context, CreateClassActivity.class);
        context.startActivity(intent);
    }

    public String getTextSearch() {
        return textSearch;
    }

    @Bindable
    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
        notifyPropertyChanged(BR.textSearch);
    }

    public void searchItem() {
        adapter.filterList(getTextSearch());
    }

    public void onClickRedirectSearchClassStudent() {
        Intent intent = new Intent(context, FindClassStudentActivity.class);
        context.startActivity(intent);
    }

    public void onClickRequestClass() {
        Call<MessageResponse> call = requestApiService.requestJoinClass("Bear " + token, classModel.getId());
        call.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(MessageResponse responseObject) {
                Toast.makeText(context, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, HomeStudentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void redirectClassDetailTeacher() {
        Bundle bundle = new Bundle();
        ClassCreateDto classCreateDto = new ClassCreateDto(
                classModel.getId(),
                classModel.getName(),
                classModel.getDescription(),
                classModel.getSubjectName(),
                classModel.getNumberOfStudent()
        );
        bundle.putSerializable("Class", (Serializable) classCreateDto);
        bundle.putInt("FragmentIndex", FragmentIndex.Teacher.getValue());
        Intent intent = new Intent(context, ClassDetailActivity.class);
        intent.putExtra("myBundle", bundle);
        intent.putExtra("classId", classCreateDto.getId());
        context.startActivity(intent, bundle);
    }

    public void redirectClassDetailStudent() {
        Bundle bundle = new Bundle();
        String nameStudent = "";
        try {
            DecodeToken.decoded(token);
            nameStudent = DecodeToken.getStringValueObjectByKey("name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        bundle.putString("nameStudent", nameStudent);
        bundle.putString("idClass", classModel.getId());
        bundle.putString("nameClass", classModel.getName());
        bundle.putInt("FragmentIndex", FragmentIndex.Student.getValue());
        Intent intent = new Intent(context, ClassDetailActivity.class);
        intent.putExtra("myBundle", bundle);
        context.startActivity(intent, bundle);
    }
}
