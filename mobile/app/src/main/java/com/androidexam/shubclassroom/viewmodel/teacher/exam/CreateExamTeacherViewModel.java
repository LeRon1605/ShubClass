package com.androidexam.shubclassroom.viewmodel.teacher.exam;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ExamApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.exam.ExamCreateDto;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;

import retrofit2.Call;

public class CreateExamTeacherViewModel extends BaseTeacherExamViewModel {
    public ExamCreateDto examCreateDto;
    public String startTime, startDate;
    public String endTime, endDate;
    public String filePath;
    private ExamApiService examApiService;

    private MutableLiveData<Boolean> btnUploadClickObservable;

    public CreateExamTeacherViewModel(Context context, INavigation navigation, String classId) {
        super(context, navigation, classId);

        filePath = "Vui lòng chọn file câu hỏi";
        examCreateDto = new ExamCreateDto(classId);
        examApiService = RetrofitClient.getRetrofitInstance().create(ExamApiService.class);
    }

    public void setDetail(String details, String filePath) {
        examCreateDto.setDetails(details);
//        filePath = "mewo";
    }

    public LiveData<Boolean> getBtnUploadClickObservable() {
        if (this.btnUploadClickObservable == null) {
            this.btnUploadClickObservable = new MutableLiveData<>(false);
        }
        return this.btnUploadClickObservable;
    }

    public void onUploadFileSelect() {
        this.btnUploadClickObservable.setValue(true);
    }

    public void onCreateClicked() {
        examCreateDto.setStartTime(startDate + "T" + startTime + "Z");
        examCreateDto.setEndTime(endDate + "T" + endTime + "Z");

        if (examCreateDto.getEndTime().length() != 20 || examCreateDto.getStartTime().length() != 20) {
            Toast.makeText(context, "Vui lòng nhập chính xác thông tin thời gian", Toast.LENGTH_SHORT).show();
        } else if (!examCreateDto.isValid()) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            Call<ExamDto> call = examApiService.createExam(SharedPreferencesManager.getInstance(context).getAccessToken(), examCreateDto.toBody());
            call.enqueue(new ApiCallback<ExamDto, MessageResponse>(MessageResponse.class) {
                @Override
                public void handleSuccess(ExamDto responseObject) {
                    Toast.makeText(context, "Tạo bài tập thành công", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void handleFailure(MessageResponse errorResponse) {
                    Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
