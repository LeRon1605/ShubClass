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
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;

import java.util.ArrayList;

import retrofit2.Call;


public class ExamStudentViewModel extends BaseStudentExamViewModel {

    private ItemExamStudentAdapter adapter;
    private ExamApiService examApiService;

    private ArrayList<ExamDto> listReceive;
    private String token;
    private String classId;

    public ExamStudentViewModel(Context context, INavigation navigation, String classId) {
        super(context, navigation, classId);
        this.classId = classId;

        adapter = new ItemExamStudentAdapter(navigation);
        examApiService = RetrofitClient.getRetrofitInstance().create(ExamApiService.class);
        String token = SharedPreferencesManager.getInstance(context).getAccessToken();
        getListReceive(3);
    }

    public void getListReceive(int chip)
    {
        Call<ArrayList<ExamDto>> call = examApiService.getExams(token, classId);
        call.enqueue(new ApiCallback<ArrayList<ExamDto>, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(ArrayList<ExamDto> responseObject) {;
                listReceive = responseObject;
                adapter.setExams(filterExamByState(chip));
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
                    Log.d("DEBUG", i.getName());
                    Log.d("DEBUG", i.getStartTime());
                }
            }
        }
        return filterList;
    }

    public void onBackClicked() {
        navigation.navigate(ClassDetailFragment.StudentClassDetail.getValue());
    }
}
