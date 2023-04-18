package com.androidexam.shubclassroom.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit retrofit;
    public static Retrofit getRetrofitInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
<<<<<<< HEAD
                    .baseUrl("http://localhost:80/api/")
=======
                    .baseUrl("https://shubclass-api.onrender.com/api/")
>>>>>>> e777cfd191cb2069b1b0f21cbc51d28361ac567e
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}