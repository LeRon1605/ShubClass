package com.androidexam.shubclassroom.api;

import com.androidexam.shubclassroom.model.AccountLoginDto;
import com.androidexam.shubclassroom.model.MessageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPIService {
    @POST("accounts/login")
    Call<MessageResponse> login(@Body AccountLoginDto accountLoginDto);

    @POST("accounts/active")
    Call<MessageResponse> active(@Body String email);
}

