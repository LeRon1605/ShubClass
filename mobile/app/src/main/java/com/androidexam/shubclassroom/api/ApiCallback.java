package com.androidexam.shubclassroom.api;


import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T, Error> implements Callback<T> {
    private Class<Error> errorType;

    public ApiCallback(Class<Error> errorType) {
        this.errorType = errorType;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            handleSuccess(response.body());
        } else {
            Gson gson = new Gson();
            Error errorResponse = gson.fromJson(response.errorBody().charStream(), errorType);
            handleFailure(errorResponse);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }

    public abstract void handleSuccess(T responseObject);
    public abstract void handleFailure(Error errorResponse);
}