package com.androidexam.shubclassroom.view.student;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.adapter.ItemSearchAdapter;
import com.androidexam.shubclassroom.viewmodel.ClassItemViewModel;

import java.util.ArrayList;

public class ListClassFoundStudentFragment extends Fragment {
    private RecyclerView rvClass;
    private ArrayList<ClassItemViewModel> listClass;
    private ItemSearchAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_class_found_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listClass = new ArrayList<>();
        rvClass = view.findViewById(R.id.lv_class_search);
        adapter = new ItemSearchAdapter(listClass);
        rvClass.setAdapter(adapter);
        rvClass.setLayoutManager(new GridLayoutManager(getContext(), 1));
        ClassItemViewModel.itemSearchAdapter = adapter;
    }
}