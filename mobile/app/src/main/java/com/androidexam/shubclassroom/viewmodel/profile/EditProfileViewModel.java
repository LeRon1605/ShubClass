package com.androidexam.shubclassroom.viewmodel.profile;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.AuthApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.profile.ProfileIn4;
import com.androidexam.shubclassroom.model.profile.ProfileIn4Update;
import com.androidexam.shubclassroom.shared.FragmentIndex;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.student.HomeStudentActivity;
import com.androidexam.shubclassroom.view.teacher.HomeTeacherActivity;

import retrofit2.Call;

public class EditProfileViewModel {
    private ProfileIn4 profileIn4;
    private int role;
    private Context context;
    private AuthApiService apiService;
    private String token;
    public EditProfileViewModel(ProfileIn4 profileIn4, int role, Context context) {
        this.profileIn4 = profileIn4;
        this.role = role;
        this.context = context;
        apiService = RetrofitClient.getRetrofitInstance().create(AuthApiService.class);
        token = SharedPreferencesManager.getInstance(context).getAccessToken();
    }

    public ProfileIn4 getProfileIn4() {
        return profileIn4;
    }

    public void setProfileIn4(ProfileIn4 profileIn4) {
        this.profileIn4 = profileIn4;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    public void onClickUpdate() {
        if(role == FragmentIndex.Student.getValue()) {
            if(profileIn4.isValid()) {
                ProfileIn4Update profileIn4Update = new ProfileIn4Update(profileIn4.getName(),
                        profileIn4.getDateOfBirth(),
                        profileIn4.getSchool(),
                        Integer.parseInt(profileIn4.getGrade()),
                        profileIn4.getPhoneNumber(),
                        profileIn4.getAddress(),
                        profileIn4.isGender()
                );
                Call<MessageResponse> call = apiService.updateProfile("Bear " + token, profileIn4Update);
                call.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
                    @Override
                    public void handleSuccess(MessageResponse responseObject) {
                        Toast.makeText(context, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, HomeStudentActivity.class));
                    }

                    @Override
                    public void handleFailure(MessageResponse errorResponse) {
                        Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        } else {
            if(profileIn4.isValid()) {
                ProfileIn4Update profileIn4Update = new ProfileIn4Update(profileIn4.getName(),
                        profileIn4.getDateOfBirth(),
                        profileIn4.getSchool(),
                        1,
                        profileIn4.getPhoneNumber(),
                        profileIn4.getAddress(),
                        profileIn4.isGender()
                );
                Call<MessageResponse> call = apiService.updateProfile("Bear " + token, profileIn4Update);
                call.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
                    @Override
                    public void handleSuccess(MessageResponse responseObject) {
                        Toast.makeText(context, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, HomeTeacherActivity.class));
                    }

                    @Override
                    public void handleFailure(MessageResponse errorResponse) {
                        Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void onClickClose() {
        Intent intent;
        if(role == FragmentIndex.Teacher.getValue()) {
            intent = new Intent(context, HomeTeacherActivity.class);
        } else {
            intent = new Intent(context, HomeStudentActivity.class);
        }
        context.startActivity(intent);
    }
}
