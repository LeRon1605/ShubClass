package com.androidexam.shubclassroom.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.ClassDetail;
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.teacher.HomeTeacherActivity;

import retrofit2.Call;

public class CreateClassViewModel extends BaseObservable {
    private Context context;
    ClassCreateDto classCreateDto;
    private ClassApiService apiService;

    public CreateClassViewModel(Context context) {
        this.context = context;
        classCreateDto = new ClassCreateDto();
        apiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
    }

    public ClassCreateDto getClassCreateDto() {
        return classCreateDto;
    }

    public void setClassCreateDto(ClassCreateDto classCreateDto) {
        this.classCreateDto = classCreateDto;
    }

    public void onButtonCreateClick() {
        if (classCreateDto.getName() == null || classCreateDto.getId() == null || classCreateDto.getDescription() == null
                || classCreateDto.getSubjectName() == null || classCreateDto.getNumberOfStudent() == null) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else if (classCreateDto.getName() == "" || classCreateDto.getId() == "" || classCreateDto.getDescription() == ""
                || classCreateDto.getSubjectName() == "" || classCreateDto.getNumberOfStudent() == "") {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else {
            String token = SharedPreferencesManager.getInstance(context).getAccessToken();
            Call<ClassDetail> call = apiService.createClass("Brear " + token, classCreateDto);
            call.enqueue(new ApiCallback<ClassDetail, MessageResponse>(MessageResponse.class) {
                @Override
                public void handleSuccess(ClassDetail responseObject) {
                    Toast.makeText(context, "Tạo lớp thành công!", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, HomeTeacherActivity.class));
                }

                @Override
                public void handleFailure(MessageResponse errorResponse) {
                    Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void onClickArrowBack() {
        Intent intent = new Intent(context, HomeTeacherActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
