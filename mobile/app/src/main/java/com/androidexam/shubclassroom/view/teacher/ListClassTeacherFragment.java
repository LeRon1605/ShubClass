package com.androidexam.shubclassroom.view.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.adapter.ItemAdapter;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.shared.FragmentIndex;
import com.androidexam.shubclassroom.viewmodel.ClassItemViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListClassTeacherFragment extends Fragment {
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
//        listClass.add(new ClassItemViewModel(
//                "1", ".NET", "Lap trinh .Net", "Lap Trinh", 20, "TT1", "1-1-2022", "1-2-2022", getContext()
//        ));
//        listClass.add(new ClassItemViewModel(
//                "2", ".JAVA", "Lap trinh Java", "Lap Trinh", 20, "TT2", "1-1-2022", "1-2-2022", getContext()
//        ));
//        listClass.add(new ClassItemViewModel(
//                "3", "NodeJS", "Lap trinh JS", "Lap Trinh", 20, "TT3", "1-1-2022", "1-2-2022", getContext()
//        ));
        adapter = new ItemAdapter(listClass, FragmentIndex.Teacher.getValue(), getContext());
        rvClass.setAdapter(adapter);
        rvClass.setLayoutManager(new GridLayoutManager(getContext(), 1));
        ClassItemViewModel.adapter = adapter;
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        ClassApiService apiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
        Call<List<ClassItemViewModel>> call = apiService.getListClass("Brear " + token);
        call.enqueue(new ApiCallback<List<ClassItemViewModel>, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(List<ClassItemViewModel> responseObject) {
                for(ClassItemViewModel i : responseObject) {
                    i.setContext(getContext());
                    listClass.add(i);
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