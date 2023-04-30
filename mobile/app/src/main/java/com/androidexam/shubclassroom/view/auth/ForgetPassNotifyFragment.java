package com.androidexam.shubclassroom.view.auth;

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
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.auth.ForgetPassNotifyViewModel;

public class ForgetPassNotifyFragment extends Fragment {
    private INavigation navigation;
    private FragmentForgetPassNotifyBinding binding;
    private ForgetPassNotifyViewModel viewModel;

    public ForgetPassNotifyFragment(INavigation navigation) {
        this.navigation = navigation;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.fragment_forget_pass_notify, container, false);
        viewModel = new ForgetPassNotifyViewModel(getContext(), navigation);

        binding.setForgetPassNotifyViewModel(viewModel);
        View viewRoot = binding.getRoot();

        return viewRoot;


    }
}