package com.androidexam.shubclassroom.api;

import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.QuestionDto;
import com.androidexam.shubclassroom.model.exam.DoExamDto;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.model.exam.SessionExamDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ExamApiService {

    @GET("classes/{id}/exams")
    Call<ArrayList<ExamDto>> getExams(@Header("authorization") String token, @Path("id") String classId);

    @GET("exams/{id}/questions")
    Call<ArrayList<QuestionDto>> getQuestions(@Header("authorization") String token, @Path("id") String examId);

    @POST("user-exams/{id}/user-answers")
    Call<MessageResponse> postAnswer(@Header("authorization") String token, @Path("id") String userExamId, @Body DoExamDto doExamDto);

    @POST("user-exams/{id}/submit")
    Call<MessageResponse> postSubmit(@Header("authorization") String token, @Path("id") String examId);

    @POST("user-exams/{id}")
    Call<SessionExamDto> postSession(@Header("authorization") String token, @Path("id") String examId);

}
