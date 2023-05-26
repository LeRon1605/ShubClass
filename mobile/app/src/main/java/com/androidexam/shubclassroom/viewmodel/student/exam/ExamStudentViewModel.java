package com.androidexam.shubclassroom.viewmodel.student.exam;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidexam.shubclassroom.adapter.ItemExamStudentAdapter;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ExamApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.INavigation;

import java.util.ArrayList;

import retrofit2.Call;


public class ExamStudentViewModel extends BaseStudentExamViewModel {

    private String stateExam;

    private ItemExamStudentAdapter adapter;
    private ExamApiService examApiService;

    private ArrayList<ExamDto> listReceive;

    public ExamStudentViewModel(Context context, INavigation navigation, String classId) {
        super(context, navigation, classId);


        adapter = new ItemExamStudentAdapter(navigation);
        examApiService = RetrofitClient.getRetrofitInstance().create(ExamApiService.class);
//        String token = SharedPreferencesManager.getInstance(context).getAccessToken();
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJuYW1lIjoiTMOqIFF14buRYyBSw7RuIiwiYXZhdGFyIjoiZW1wdHkiLCJzdGF0ZSI6MSwicm9sZSI6IlN0dWRlbnQiLCJpYXQiOjE2ODUxMDY0ODAsImV4cCI6MTY4NTM2NTY4MH0.z6wNFPBWfwgRS6qtdF9WJ6Q6zmPsow7Cm_aMWzZNci0";
        Log.d("TOKEN", token);

        Call<ArrayList<ExamDto>> call = examApiService.getExams(token, classId);
        call.enqueue(new ApiCallback<ArrayList<ExamDto>, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(ArrayList<ExamDto> responseObject) {;
                listReceive = responseObject;
                adapter.setExams(listReceive);
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public ItemExamStudentAdapter getAdapter()
    {
        return adapter;
    }

    public ArrayList<ExamDto> filterExamByState(int state)
    {
        ArrayList<ExamDto> filterList = new ArrayList<>();
        if(state == 3)
        {
            filterList = listReceive;
        }
        else
        {
            for (ExamDto i : listReceive)
            {
                if (i.getState() == state)
                {
                    filterList.add(i);
                }
            }
        }
        return filterList;
    }

    public void onChipClicked(int chip)
    {
        adapter.setExams(filterExamByState(chip));
    }

    public void onBackClicked() {
        navigation.navigate(ClassDetailFragment.StudentClassDetail.getValue());
    }
}
