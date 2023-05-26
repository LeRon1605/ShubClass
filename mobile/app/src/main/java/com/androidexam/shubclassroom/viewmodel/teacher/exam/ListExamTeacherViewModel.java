package com.androidexam.shubclassroom.viewmodel.teacher.exam;

import android.content.Context;
import android.widget.Toast;

import com.androidexam.shubclassroom.adapter.ExamTeacherItemAdapter;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ExamApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;

import java.util.ArrayList;

import retrofit2.Call;

public class ListExamTeacherViewModel extends BaseTeacherExamViewModel{
    private ExamTeacherItemAdapter adapter;
    private ExamApiService examApiService;

    public ListExamTeacherViewModel(Context context, INavigation navigation, String classId) {
        super(context, navigation, classId);

        adapter = new ExamTeacherItemAdapter(navigation, context);
        examApiService = RetrofitClient.getRetrofitInstance().create(ExamApiService.class);
        Call<ArrayList<ExamDto>> call = examApiService.getExams(SharedPreferencesManager.getInstance(context).getAccessToken(), classId);

        call.enqueue(new ApiCallback<ArrayList<ExamDto>, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(ArrayList<ExamDto> responseObject) {
                adapter.setExams(responseObject);
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public ExamTeacherItemAdapter getAdapter() {
        return adapter;
    }

    public void onCreateExamClicked() {
        navigation.navigate(ClassDetailFragment.TeacherCreateExam.getValue());
    }
}
