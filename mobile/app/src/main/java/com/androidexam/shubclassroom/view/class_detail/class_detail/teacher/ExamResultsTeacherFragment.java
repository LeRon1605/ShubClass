package com.androidexam.shubclassroom.view.class_detail.class_detail.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.adapter.ExamResultItemAdapter;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ExamApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.exam_result.ExamResult;
import com.androidexam.shubclassroom.shared.INavigation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ExamResultsTeacherFragment extends Fragment {
    private ExamApiService apiService;
    private String examId;
    private INavigation navigation;
    private RecyclerView rcvExamResult;
    private ExamResultItemAdapter adapter;
    private List<ExamResult> examResultList;
    private String token;
    public ExamResultsTeacherFragment(INavigation navigation, String examId) {
        this.examId = examId;
        this.navigation = navigation;
        apiService = RetrofitClient.getRetrofitInstance().create(ExamApiService.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exam_results_teacher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        rcvExamResult = view.findViewById(R.id.rcv_exam_result);
        examResultList = new ArrayList<>();
        adapter = new ExamResultItemAdapter(getContext(), navigation );
        rcvExamResult.setAdapter(adapter);
        rcvExamResult.setLayoutManager(new GridLayoutManager(getContext(), 1));
        Call<List<ExamResult>> call = apiService.getExamResult("Bear " + token, examId);
        call.enqueue(new ApiCallback<List<ExamResult>, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(List<ExamResult> responseObject) {
                Log.d("TAG", responseObject.get(0).getPoints() + "");
                examResultList = responseObject;
                adapter.setExamResultList(examResultList);
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(getContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}