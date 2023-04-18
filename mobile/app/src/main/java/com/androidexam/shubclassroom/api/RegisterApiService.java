package com.androidexam.shubclassroom.api;

import com.androidexam.shubclassroom.model.AccountCreateDto;
import com.androidexam.shubclassroom.model.MessageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterApiService {
    @POST("accounts")
    Call<MessageResponse> postRegister(@Body AccountCreateDto account);
}
