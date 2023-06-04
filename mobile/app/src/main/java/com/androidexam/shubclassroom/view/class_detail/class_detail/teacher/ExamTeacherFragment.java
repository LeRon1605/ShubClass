package com.androidexam.shubclassroom.view.class_detail.class_detail.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.adapter.ExamTeacherItemAdapter;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.databinding.FragmentExamTeacherBinding;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.viewmodel.class_detail.teacher.ExamViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ExamTeacherFragment extends Fragment {
    private RecyclerView recyclerView;
    private ExamTeacherItemAdapter adapter;
    private List<ExamDto> examDtoList;
    private INavigation navigation;
    private FragmentExamTeacherBinding binding;
    private String token;
    private ClassApiService apiService;
    private String classId;
    public ExamTeacherFragment(INavigation navigation, String classId) {
        this.navigation = navigation;
        this.classId = classId;
        apiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exam_teacher, container, false);
        FragmentManager fm = getParentFragmentManager();
        binding.setViewModel(new ExamViewModel(getContext(), navigation, fm));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        token = SharedPreferencesManager.getInstance(getContext()).getAccessToken();
        recyclerView = view.findViewById(R.id.rcv_exam);
        examDtoList = new ArrayList<>();
        adapter = new ExamTeacherItemAdapter(navigation, getContext());
        ExamViewModel.adapter = adapter;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        Call<List<ExamDto>> call = apiService.getExamsInClass(token, classId);
        call.enqueue(new ApiCallback<List<ExamDto>, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(List<ExamDto> responseObject) {
                adapter.setExams(responseObject);
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(getContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}