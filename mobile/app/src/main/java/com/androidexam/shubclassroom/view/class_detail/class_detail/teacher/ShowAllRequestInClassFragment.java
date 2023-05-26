package com.androidexam.shubclassroom.view.class_detail.class_detail.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.adapter.StudentRequestItemAdapter;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.RequestApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.request.RequestIn4;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.class_detail.teacher.RequestViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ShowAllRequestInClassFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<RequestViewModel> viewModelList;
    private StudentRequestItemAdapter adapter;
    private INavigation navigation;
    private String idClass;

    public ShowAllRequestInClassFragment(INavigation navigation, String idClass) {
        this.navigation = navigation;
        this.idClass = idClass;
        viewModelList = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_all_request_in_class, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rcv_request);
        adapter = new StudentRequestItemAdapter(viewModelList, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);

        RequestApiService apiService = RetrofitClient.getRetrofitInstance().create(RequestApiService.class);
        Call<List<RequestIn4>> call  = apiService.getRequestOfClass("Bear " + token, idClass);
        call.enqueue(new ApiCallback<List<RequestIn4>, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(List<RequestIn4> responseObject) {
                Log.d("TAG", responseObject.size() + "");
                for(RequestIn4 i : responseObject) {
                    viewModelList.add(new RequestViewModel(navigation, i, getContext(), idClass));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(getContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}