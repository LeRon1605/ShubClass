package com.androidexam.shubclassroom.view.class_detail.class_detail.student;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.databinding.FragmentStudentClassDetailBinding;
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.SummaryIn4Student;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.class_detail.student.ClassDetailStudentViewModel;

import retrofit2.Call;

public class ClassDetailStudentFragment extends Fragment {
    private INavigation navigation;
    private FragmentStudentClassDetailBinding binding;
    private String idClass;
    private String nameClass;
    private SummaryIn4Student summaryIn4Student;
    private ClassApiService apiService;
    private String token;
    public ClassDetailStudentFragment(INavigation navigation, String idClass, String nameClass) {
        this.navigation = navigation;
        this.idClass = idClass;
        this.nameClass = nameClass;
        apiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_class_detail , container, false);
        Call<SummaryIn4Student> call = apiService.getSummaryIn4Student("Bear " + token, idClass);
        call.enqueue(new ApiCallback<SummaryIn4Student, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(SummaryIn4Student responseObject) {
                summaryIn4Student = responseObject;
                binding.setViewModel(new ClassDetailStudentViewModel(getContext(), navigation, idClass, nameClass, summaryIn4Student));
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(getContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}