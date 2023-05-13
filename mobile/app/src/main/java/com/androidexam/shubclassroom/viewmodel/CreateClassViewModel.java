package com.androidexam.shubclassroom.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.androidexam.shubclassroom.BR;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.Class;
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.teacher.HomeTeacherActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateClassViewModel extends BaseObservable {
    private Context context;
    ClassCreateDto classCreateDto;
    private ClassApiService apiService;

    public CreateClassViewModel(Context context) {
        this.context = context;
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
            SharedPreferences sharedPreferences = context.getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("token", null);
//            ClassCreateDto classCreateDto = new ClassCreateDto(classCreateDto.getId(), classCreateDto.getName(), classCreateDto.getDescription(), classCreateDto.getSubjectName(), Integer.parseInt(classCreateDto.getNumberOfStudent()));
            Call<Class> call = apiService.createClass("Brear " + token, classCreateDto);
//            call.enqueue(new ApiCallback<Class, Class>(Class.class) {
//                @Override
//                public void handleSuccess(Class responseObject) {
//                    Toast.makeText(context, "Tạo lớp thành công!", Toast.LENGTH_SHORT).show();
//                    context.startActivity(new Intent(context, HomeTeacherActivity.class));
//                }
//
//                @Override
//                public void handleFailure(Class errorResponse) {
//                    Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
//                }
//            });
            call.enqueue(new ApiCallback<Class, MessageResponse>(MessageResponse.class) {
                @Override
                public void handleSuccess(Class responseObject) {
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
}
