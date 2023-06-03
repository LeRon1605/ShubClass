package com.androidexam.shubclassroom.api;

import com.androidexam.shubclassroom.model.auth.AccountActivateDto;
import com.androidexam.shubclassroom.model.auth.AccountCreateDto;
import com.androidexam.shubclassroom.model.auth.AccountLoginDto;
import com.androidexam.shubclassroom.model.auth.AuthCredential;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.auth.ForgetPasswordDto;
import com.androidexam.shubclassroom.model.profile.ProfileIn4;
import com.androidexam.shubclassroom.model.profile.ProfileIn4Update;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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
    @GET("accounts/me")
    Call<ProfileIn4> getProfileIn4(@Header("authorization") String token);
    @PUT("accounts")
    Call<MessageResponse> updateProfile(@Header("authorization") String token,  @Body ProfileIn4Update profile);
}
