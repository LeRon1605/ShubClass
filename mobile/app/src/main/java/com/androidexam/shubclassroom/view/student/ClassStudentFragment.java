package com.androidexam.shubclassroom.view.student;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentClassStudentBinding;
import com.androidexam.shubclassroom.viewmodel.ClassItemViewModel;

public class ClassStudentFragment extends Fragment {
    private FragmentClassStudentBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_class_student, container, false);
        ClassItemViewModel classItemViewModel = new ClassItemViewModel(getContext());
        binding.setClassStudentItem(classItemViewModel);
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                classItemViewModel.setTextSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return binding.getRoot();
    }
}