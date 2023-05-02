package com.androidexam.shubclassroom.view.teacher.exam;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentListTeacherExamBinding;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.teacher.exam.ListExamTeacherViewModel;

public class ListExamTeacherFragment extends Fragment {
    private INavigation navigation;
    private ListExamTeacherViewModel viewModel;
    private FragmentListTeacherExamBinding binding;
    private String classId;

    public ListExamTeacherFragment(INavigation navigation, String classId) {
        this.navigation = navigation;
        this.classId = classId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_list_teacher_exam, container, false);

        viewModel = new ListExamTeacherViewModel(getContext(), navigation, classId);

        binding.setListExamTeacherViewModel(viewModel);

        binding.rvListExamTeacher.setAdapter(viewModel.getAdapter());
        binding.rvListExamTeacher.setLayoutManager(new LinearLayoutManager(getContext()));

        View viewRoot = binding.getRoot();
        return viewRoot;
    }
}