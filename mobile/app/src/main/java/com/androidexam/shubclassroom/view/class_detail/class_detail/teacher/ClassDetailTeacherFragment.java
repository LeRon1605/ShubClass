package com.androidexam.shubclassroom.view.class_detail.class_detail.teacher;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentTeacherClassDetailBinding;
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.class_detail.teacher.ClassDetailTeacherViewModel;

public class ClassDetailTeacherFragment extends Fragment {
    private INavigation navigation;
    private FragmentTeacherClassDetailBinding binding;
    private ClassCreateDto classCreateDto;
    private ClassDetailTeacherViewModel viewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ClassDetailTeacherFragment(INavigation navigation, ClassCreateDto classCreateDto) {
        this.navigation = navigation;
        this.classCreateDto = classCreateDto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_teacher_class_detail, container, false);
        viewModel = new ClassDetailTeacherViewModel(getContext(),navigation , classCreateDto);
        binding.setViewModel(viewModel);
        View viewRoot = binding.getRoot();
        return viewRoot;
    }
}