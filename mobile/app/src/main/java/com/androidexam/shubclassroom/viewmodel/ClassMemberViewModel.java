package com.androidexam.shubclassroom.viewmodel;

import android.content.Context;
import android.widget.Toast;

import com.androidexam.shubclassroom.adapter.ClassMemberItemAdapter;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.student.StudentDTO;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;

import java.util.ArrayList;

import retrofit2.Call;

public class ClassMemberViewModel extends BaseClassMemberViewModel {
    private ClassMemberItemAdapter adapter;
    private ClassApiService classApiService;

    public ClassMemberViewModel(Context context, INavigation navigation, String classId) {
        super(context, navigation, classId);

        adapter = new ClassMemberItemAdapter(navigation);
        classApiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
        Call<ArrayList<StudentDTO>> call = classApiService.getAllStudentsInClass(SharedPreferencesManager.getInstance(context).getAccessToken(), classId, "3");

        call.enqueue(new ApiCallback<ArrayList<StudentDTO>, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(ArrayList<StudentDTO> responseObject) {
                adapter.setStudents(responseObject);
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ClassMemberItemAdapter getAdapter() {
        return adapter;
    }

    public void onBackClicked() {

    }
}
