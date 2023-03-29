package com.androidexam.shubclassroom.api;

import com.androidexam.shubclassroom.model.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface RegisterApiService {
    @POST("accounts")
    Call<Account> postRegister(@Field("email") String email,
                               @Field("password") String password,
                               @Field("name") String name,
                               @Field("dateOfBirth") String dateOfBirth,
                               @Field("school") String school,
                               @Field("grade") String grade,
                               @Field("phoneNumber") String phoneNumber,
                               @Field("address") String address,
                               @Field("role") String role
                                );
    @POST("accounts")
    Call<Account> postRegister2(@Body Account account);
}