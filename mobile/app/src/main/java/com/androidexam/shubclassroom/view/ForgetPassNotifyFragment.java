package com.androidexam.shubclassroom.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentForgetPassNotifyBinding;
import com.androidexam.shubclassroom.viewmodel.ForgetPassNotifyViewModel;

public class ForgetPassNotifyFragment extends Fragment {

    private FragmentForgetPassNotifyBinding binding;
    private ForgetPassNotifyViewModel myViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.fragment_forget_pass_notify, container, false);
        View viewRoot = binding.getRoot();

        myViewModel = new ViewModelProvider(this).get(ForgetPassNotifyViewModel.class);

        // observe navigateToActivity LiveData object
        myViewModel.getNavigateToActivity().observe(getViewLifecycleOwner(), navigate -> {
            if (navigate) {
                // start the Activity here
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return viewRoot;


    }
}