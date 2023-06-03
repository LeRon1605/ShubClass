package com.androidexam.shubclassroom.view.student;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.AuthApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.databinding.FragmentStudentProfileBinding;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.profile.ProfileIn4;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.auth.AuthActivity;
import com.androidexam.shubclassroom.viewmodel.profile.ProfileStudentViewModel;

import retrofit2.Call;

public class ProfileStudentFragment extends Fragment {
    private FragmentStudentProfileBinding binding;
    private AuthApiService apiService;
    private ProfileStudentViewModel viewModel;
    private String token;
    public ProfileStudentFragment() {
        apiService = RetrofitClient.getRetrofitInstance().create(AuthApiService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_profile, container, false);
        token = SharedPreferencesManager.getInstance(getContext()).getAccessToken();
        Call<ProfileIn4> call = apiService.getProfileIn4("Bear " + token);
        call.enqueue(new ApiCallback<ProfileIn4, MessageResponse>(MessageResponse.class) {

            @Override
            public void handleSuccess(ProfileIn4 responseObject) {
                viewModel = new ProfileStudentViewModel(getContext(), responseObject);
                binding.setViewModel(viewModel);
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(getContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }


}