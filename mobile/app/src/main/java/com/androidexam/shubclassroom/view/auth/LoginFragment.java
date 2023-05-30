package com.androidexam.shubclassroom.view.auth;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentLoginBinding;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.auth.LoginViewModel;

public class LoginFragment extends Fragment {
    private final INavigation navigation;
    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    public LoginFragment(INavigation navigation) {
        this.navigation = navigation;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_login, container, false);
        viewModel = new LoginViewModel(getContext(), navigation);
        binding.setLoginViewModel(viewModel);

        View viewRoot = binding.getRoot();
        return viewRoot;
    }
}