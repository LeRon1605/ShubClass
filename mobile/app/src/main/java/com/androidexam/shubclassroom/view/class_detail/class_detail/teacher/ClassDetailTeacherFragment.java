package com.androidexam.shubclassroom.view.class_detail.class_detail.teacher;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentTeacherClassDetailBinding;
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.viewmodel.class_detail.teacher.ClassDetailTeacherViewModel;
import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.squareup.picasso.Picasso;

public class ClassDetailTeacherFragment extends Fragment {
    private INavigation navigation;
    private FragmentTeacherClassDetailBinding binding;
    private ClassCreateDto classCreateDto;
    private ClassDetailTeacherViewModel viewModel;
    private String token;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ClassDetailTeacherFragment(INavigation navigation, ClassCreateDto classCreateDto) {
        this.navigation = navigation;
        this.classCreateDto = classCreateDto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_teacher_class_detail, container, false);
        viewModel = new ClassDetailTeacherViewModel(getContext(),navigation , classCreateDto);
        binding.setViewModel(viewModel);

        token = SharedPreferencesManager.getInstance(getContext()).getAccessToken();

        JWT jwt = new JWT(token);
        Claim claim = jwt.getClaim("avatar");
        Claim claim1 = jwt.getClaim("name");

        String avatar = claim.asString();
        String name = claim1.asString();

        Picasso.get().load(avatar).into(binding.ciwAvatar);
        binding.tvName.setText(name);

        View viewRoot = binding.getRoot();
        return viewRoot;
    }
}