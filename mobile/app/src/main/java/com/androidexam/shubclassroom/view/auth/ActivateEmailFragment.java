package com.androidexam.shubclassroom.view.auth;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentActivateEmailBinding;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.auth.ActivateMailViewModel;

public class ActivateEmailFragment extends Fragment {
    private INavigation navigation;
    private FragmentActivateEmailBinding binding;
    private ActivateMailViewModel viewModel;
    public ActivateEmailFragment(INavigation navigation) {
        this.navigation = navigation;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_activate_email, container, false);
        viewModel = new ActivateMailViewModel(getContext(), navigation);
        binding.setActivateEmailViewModel(viewModel);

        View viewRoot = binding.getRoot();
        return viewRoot;
    }
}