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
>>>>>>> f6ddea4ea7f887956a60d03bca3dbcc9b629f082
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}