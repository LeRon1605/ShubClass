package com.androidexam.shubclassroom.api;

import com.androidexam.shubclassroom.model.exam.ExamCreateDto;
import com.androidexam.shubclassroom.model.exam.ExamDto;

import java.util.ArrayList;

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

    @GET("classes/{id}/exams")
    Call<ArrayList<ExamDto>> getExams(@Header("authorization") String token, @Path("id") String id, @Query("type") String type);
}
