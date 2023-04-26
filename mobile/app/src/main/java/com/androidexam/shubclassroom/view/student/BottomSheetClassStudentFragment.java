package com.androidexam.shubclassroom.view.student;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentBottomSheetClassStudentBinding;
import com.androidexam.shubclassroom.viewmodel.ClassItemViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetClassStudentFragment extends BottomSheetDialogFragment {
    private ClassItemViewModel classItemViewModel;
    private FragmentBottomSheetClassStudentBinding binding;
    public BottomSheetClassStudentFragment(ClassItemViewModel classItemViewModel) {
        this.classItemViewModel = classItemViewModel;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet_class_student, container, false);
        binding.setClassItem(classItemViewModel);
        return binding.getRoot();
    }
}