package com.androidexam.shubclassroom.view.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.adapter.ItemAdapter;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RegisterApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.Class;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.viewmodel.ClassItemViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListClassFragment extends Fragment {
    private RecyclerView rvClass;
    private ArrayList<ClassItemViewModel> listClass;
    private ItemAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_list_class, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvClass = view.findViewById(R.id.lv_class);
        listClass = new ArrayList<>();
        adapter = new ItemAdapter(listClass);
        rvClass.setAdapter(adapter);
        rvClass.setLayoutManager(new GridLayoutManager(getContext(), 1));
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        ClassApiService apiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
        Call<List<ClassItemViewModel>> call = apiService.getListClass("Brear " + token);
        call.enqueue(new Callback<List<ClassItemViewModel>>() {
            @Override
            public void onResponse(Call<List<ClassItemViewModel>> call, Response<List<ClassItemViewModel>> response) {
                List<ClassItemViewModel> listClassApi = response.body();
                for(ClassItemViewModel i : listClassApi) {
                    listClass.add(i);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<ClassItemViewModel>> call, Throwable t) {

            }
        });

    }
}