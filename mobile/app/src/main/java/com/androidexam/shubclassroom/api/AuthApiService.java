package com.androidexam.shubclassroom.api;

import com.androidexam.shubclassroom.model.auth.AccountActivateDto;
import com.androidexam.shubclassroom.model.auth.AccountCreateDto;
import com.androidexam.shubclassroom.model.auth.AccountLoginDto;
import com.androidexam.shubclassroom.model.auth.AuthCredential;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.auth.ForgetPasswordDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthApiService {
    @POST("accounts/forget-password")
    Call<MessageResponse> requestForgetPassword(@Body ForgetPasswordDto forgetPasswordDto);

    @POST("accounts/login")
    Call<AuthCredential> login(@Body AccountLoginDto accountLoginDto);

    @POST("accounts")
    Call<MessageResponse> postRegister(@Body AccountCreateDto accountCreateDto);

    @POST("accounts/active")
    Call<MessageResponse> active(@Body AccountActivateDto accountActivateDto);

    // dump endpoint for testing only
    @GET("accounts/me")
    Call<MessageResponse> validate(@Header("authorization") String token);
}