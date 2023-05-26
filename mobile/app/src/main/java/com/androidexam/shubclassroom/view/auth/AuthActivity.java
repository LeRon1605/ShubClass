package com.androidexam.shubclassroom.view.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.shared.AuthFragment;
import com.androidexam.shubclassroom.shared.INavigation;

public class AuthActivity extends AppCompatActivity implements INavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        navigate(AuthFragment.Login.getValue());
    }

    @Override
    public void navigate(int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (id == AuthFragment.Login.getValue()) {
            transaction.replace(R.id.fr_auth, new LoginFragment(this));
        } else if (id == AuthFragment.Register.getValue()) {
            transaction.replace(R.id.fr_auth, new RegisterFragment(this));
        } else if (id == AuthFragment.ForgetPassword.getValue()) {
            transaction.replace(R.id.fr_auth, new ForgetPassFragment(this));
        } else if (id == AuthFragment.Notify.getValue()) {
            transaction.replace(R.id.fr_auth, new ForgetPassNotifyFragment(this));
        } else if (id == AuthFragment.ActivateAccount.getValue()) {
            transaction.replace(R.id.fr_auth, new ActivateEmailFragment(this));
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }
}