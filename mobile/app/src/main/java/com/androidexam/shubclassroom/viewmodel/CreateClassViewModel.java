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
    private String id;
    private String name;
    private String description;
    private String subjectName;
    private String numberOfStudent;

    public CreateClassViewModel(Context context) {
        this.context = context;
    }
    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }
    @Bindable
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        notifyPropertyChanged(BR.subjectName);
    }
    @Bindable
    public String getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(String numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
        notifyPropertyChanged(BR.numberOfStudent);
    }
    public void onButtonCreateClick() {
        if (getName() == null || getId() == null || getDescription() == null
                || getSubjectName() == null || getNumberOfStudent() == null) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else if (getName() == "" || getId() == "" || getDescription() == ""
                || getSubjectName() == "" || getNumberOfStudent() == "") {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else {
            SharedPreferences sharedPreferences = context.getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("token", null);
            ClassCreateDto classCreateDto = new ClassCreateDto(getId(), getName(), getDescription(), getSubjectName(), Integer.parseInt(getNumberOfStudent()));
            ClassApiService apiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
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
