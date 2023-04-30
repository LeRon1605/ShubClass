package com.androidexam.shubclassroom.view.teacher;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentTeacherHomeBinding;
import com.androidexam.shubclassroom.viewmodel.ClassTeacherViewModel;

public class HomeTeacherFragment extends Fragment {
    private FragmentTeacherHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_teacher_home, container, false);
        ClassTeacherViewModel classTeacherViewModel = new ClassTeacherViewModel(getContext());
        binding.setClassTeacherViewModel(classTeacherViewModel);
        return binding.getRoot();
    }
}