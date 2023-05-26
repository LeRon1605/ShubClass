package com.androidexam.shubclassroom.api;

import com.androidexam.shubclassroom.model.exam.ExamCreateDto;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.model.exam_result.ExamResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExamApiService {
    @POST("exams")
    Call<ExamDto> createExam(@Header("authorization") String token, @Body ExamCreateDto examCreateDto);
    @GET("exams/{id}/result")
    Call<List<ExamResult>> getExamResult(@Header("authorization") String token, @Path("id") String examId);
}
