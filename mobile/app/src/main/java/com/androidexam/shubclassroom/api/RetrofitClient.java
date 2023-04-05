package com.androidexam.shubclassroom.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit retrofit;
    public static Retrofit getRetrofitInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://shubclass-api.onrender.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}