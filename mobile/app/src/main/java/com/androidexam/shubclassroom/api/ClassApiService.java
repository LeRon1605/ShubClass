package com.androidexam.shubclassroom.api;

import com.androidexam.shubclassroom.model.Class;
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.viewmodel.ClassItemViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ClassApiService {
    @POST("classes")
    Call<Class> createClass(@Header("authorization") String token, @Body ClassCreateDto classCreateDto);
    @GET("classes")
    Call<List<ClassItemViewModel>> getListClass(@Header("authorization") String token);
}
