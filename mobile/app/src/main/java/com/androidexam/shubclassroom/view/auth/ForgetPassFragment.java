package com.androidexam.shubclassroom.view.auth;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentForgetPassBinding;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.auth.ForgetPassViewModel;

public class ForgetPassFragment extends Fragment {
    private INavigation navigation;
    private FragmentForgetPassBinding binding;
    private ForgetPassViewModel forgetPassViewModel;

    public ForgetPassFragment(INavigation navigation) {
        this.navigation = navigation;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.fragment_forget_pass, container, false);
        forgetPassViewModel = new ForgetPassViewModel(getContext(), navigation);
        binding.setForgetPassViewModel(forgetPassViewModel);

        View viewRoot = binding.getRoot();
        return viewRoot;

    }
}