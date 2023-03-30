package com.androidexam.shubclassroom.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentForgetPassBinding;
import com.androidexam.shubclassroom.viewmodel.ForgetPassViewModel;

public class ForgetPassFragment extends Fragment {

    private FragmentForgetPassBinding binding;
    private ForgetPassViewModel forgetPassViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.fragment_forget_pass, container, false);
        View viewRoot = binding.getRoot();
        return viewRoot;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {

    }
}