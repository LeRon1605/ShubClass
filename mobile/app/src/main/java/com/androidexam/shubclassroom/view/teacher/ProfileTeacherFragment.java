package com.androidexam.shubclassroom.view.teacher;

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
import com.androidexam.shubclassroom.databinding.FragmentTeacherProfileBinding;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.profile.ProfileIn4;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.viewmodel.profile.ProfileTeacherViewModel;

import retrofit2.Call;

public class ProfileTeacherFragment extends Fragment {
    private FragmentTeacherProfileBinding binding;
    private AuthApiService apiService;
    private ProfileTeacherViewModel viewModel;
    private String token;

    public ProfileTeacherFragment() {
        apiService = RetrofitClient.getRetrofitInstance().create(AuthApiService.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_teacher_profile, container, false);
        token = SharedPreferencesManager.getInstance(getContext()).getAccessToken();
        Call<ProfileIn4> call = apiService.getProfileIn4("Bear " + token);
        call.enqueue(new ApiCallback<ProfileIn4, MessageResponse>(MessageResponse.class) {

            @Override
            public void handleSuccess(ProfileIn4 responseObject) {
                viewModel = new ProfileTeacherViewModel(getContext(), responseObject);
                binding.setViewModel(viewModel);
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(getContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}