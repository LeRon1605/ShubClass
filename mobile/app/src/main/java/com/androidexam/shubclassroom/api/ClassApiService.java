package com.androidexam.shubclassroom.api;

import com.androidexam.shubclassroom.model.Class;
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.student.StudentDTO;
import com.androidexam.shubclassroom.viewmodel.ClassItemViewModel;
import com.androidexam.shubclassroom.viewmodel.ClassMemberItemViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClassApiService {
    @POST("classes")
    Call<Class> createClass(@Header("authorization") String token, @Body ClassCreateDto classCreateDto);
    @GET("classes")
    Call<List<ClassItemViewModel>> getListClass(@Header("authorization") String token);
    @DELETE("classes/{id}")
    Call<MessageResponse> deleteClass(@Header("authorization") String token, @Path("id") String idClass);
    @GET("{id}/students")
    Call<ArrayList<StudentDTO>> getAllStudentsInClass(@Header("authorization") String token, @Path("id") String id, @Query("type") String type);
    @GET("classes/search")
    Call<List<ClassItemViewModel>> searchClass(@Header("authorization") String token, @Query("id") String id);
}
