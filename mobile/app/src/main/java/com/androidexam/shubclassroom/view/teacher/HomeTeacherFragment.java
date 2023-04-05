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
        classTeacherViewModel.getNavigate().observe(getViewLifecycleOwner(), nextComponentId -> {
            Fragment fragment = null;
            switch (nextComponentId) {
                case 1:
                    fragment = new CreateClassFragment();
                    break;
            }

            if(fragment != null) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fr_main, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                binding.frMain.setVisibility(View.GONE);
            }
        });
        return binding.getRoot();
    }
}