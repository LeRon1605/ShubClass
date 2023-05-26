package com.androidexam.shubclassroom.api;

import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.request.RequestIn4;
import com.androidexam.shubclassroom.viewmodel.class_detail.teacher.RequestViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RequestApiService {
    @POST("classes/{id}/requests")
    Call<MessageResponse> requestJoinClass (@Header("authorization") String token, @Path("id") String id);
    @GET("classes/{id}/requests")
    Call<List<RequestIn4>> getRequestOfClass(@Header("authorization") String token, @Path("id") String id);
    @PUT("classes/{id}/requests/{studentId}")
    Call<MessageResponse> acceptRequest(@Header("authorization") String token, @Path("id") String id, @Path("studentId") String studentId);
    @DELETE("classes/{id}/requests/{studentId}")
    Call<MessageResponse> rejectRequest(@Header("authorization") String token, @Path("id") String id, @Path("studentId") String studentId);
}
