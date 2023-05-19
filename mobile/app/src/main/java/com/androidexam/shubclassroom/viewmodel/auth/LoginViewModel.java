package com.androidexam.shubclassroom.viewmodel.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.AuthApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.auth.AccountLoginDto;
import com.androidexam.shubclassroom.model.auth.AuthCredential;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.shared.AuthFragment;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.ClassDetailActivity;
import com.androidexam.shubclassroom.view.student.HomeStudentActivity;
import com.androidexam.shubclassroom.view.teacher.HomeTeacherActivity;
import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;

import retrofit2.Call;

public class LoginViewModel extends BaseAuthViewModel {
    private AuthApiService authApiService;
    private SharedPreferences sharedPreferencesManager;

    private AccountLoginDto accountLoginDto;

    public LoginViewModel(Context context, INavigation navigation) {
        super(context, navigation);
        authApiService = RetrofitClient.getRetrofitInstance().create(AuthApiService.class);
        sharedPreferencesManager = PreferenceManager.getDefaultSharedPreferences(context);

        if (!sharedPreferencesManager.getString("token", "").isEmpty()) {
            redirectByRole(sharedPreferencesManager.getString("role", ""));
        }
        accountLoginDto = new AccountLoginDto();
    }

    public AccountLoginDto getAccountLoginDto() {
        return this.accountLoginDto;
    }

    public void setAccountLoginDto(AccountLoginDto accountLoginDto) {
        this.accountLoginDto = accountLoginDto;
    }


    public void onLoginClicked() {
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(context);
        if (!accountLoginDto.isValid()) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
        } else {
            Call<AuthCredential> callLogin = authApiService.login(accountLoginDto);
            callLogin.enqueue(new ApiCallback<AuthCredential, MessageResponse>(MessageResponse.class) {
                @Override
                public void handleSuccess(AuthCredential responseObject) {
                    String token = responseObject.getAccess_token();
                    JWT jwt = new JWT(token);
                    Claim subscriptionMetaData = jwt.getClaim("state");
                    if (subscriptionMetaData.asInt() == 0) {
                        navigateTo(AuthFragment.ActivateAccount);
                    } else {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        subscriptionMetaData = jwt.getClaim("role");
                        String role = subscriptionMetaData.asString();

                        editor.putString("token", token);
                        editor.putString("role", role);
                        editor.apply();
                        redirectByRole(role);
                    }
                }

                @Override
                public void handleFailure(MessageResponse errorResponse) {
                    Log.d("TAG", errorResponse.getMessage());
                    Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void redirectByRole(String role) {
        if (role.isEmpty()) return;
        Intent intent;
        if (role.equals("Teacher")) {
            intent = new Intent(context, HomeTeacherActivity.class);
        } else {
            intent = new Intent(context, HomeStudentActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
