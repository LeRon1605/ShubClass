package com.androidexam.shubclassroom.view.student;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentStudentExamBinding;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.ExamStudentViewModel;

import java.util.ArrayList;

public class ExamStudentFragment extends Fragment {

    private INavigation navigation;
    private FragmentStudentExamBinding binding;
    private ExamStudentViewModel viewModel;

    private RecyclerView rvExam;

    public ExamStudentFragment(INavigation navigation)
    {
        this.navigation = navigation;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_student_exam, container, false);
        viewModel = new ExamStudentViewModel(getContext(), navigation);

        binding.setExam(viewModel);
        View view = binding.getRoot();
        return view;
    }
}