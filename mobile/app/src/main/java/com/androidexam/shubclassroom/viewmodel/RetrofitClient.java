package com.androidexam.shubclassroom.viewmodel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit;
    static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    public static Retrofit getRetrofitInstance() {
        if(retrofit == null) {
            ApiService apiService = new Retrofit.Builder()
                    .baseUrl("http://139.162.62.215/api")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(ApiService.class);
        }
        return retrofit;
    }
}
