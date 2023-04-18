package com.androidexam.shubclassroom.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ActivityLoginBinding;
import com.androidexam.shubclassroom.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity{

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        View view = binding.getRoot();
        setContentView(view);

        loginViewModel = new LoginViewModel(getApplicationContext());
        binding.setLoginViewModel(loginViewModel);

        loginViewModel.getNavigate().observe(this, nextComponentId ->
        {
            Fragment fragment = null;
            Activity activity = null;
            switch (nextComponentId)
            {
                case 1:
                    fragment = new ForgetPassFragment();
                    break;
                case 2:
                    activity = new RegisterActivity();
                    break;
                case 3:
                    fragment = new ActivateEmailFragment();
                default:
                    break;
            }

            if(fragment != null)
            {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_main, fragment)
                        .commit();
                binding.llLogin.setVisibility(View.GONE);
            }
            if (activity != null)
            {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }



}