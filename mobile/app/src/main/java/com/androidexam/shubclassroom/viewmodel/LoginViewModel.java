package com.androidexam.shubclassroom.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidexam.shubclassroom.BR;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.LoginAPIService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.AccountLoginDto;
import com.androidexam.shubclassroom.model.AuthCredential;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.student.HomeStudentActivity;
import com.androidexam.shubclassroom.view.teacher.HomeTeacherActivity;
import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends BaseObservable {

    private LoginAPIService loginAPIService;
    private SharedPreferencesManager preferencesManager;
    private Context context;

    private String email;
    private String password;

    private Boolean rightAccount;
    private Boolean isActivated;

    private MutableLiveData<Integer> mNavigate = new MutableLiveData<>();
    private MutableLiveData<AccountLoginDto> accountLoginDtoMutableLiveData;

    public LoginViewModel(Context context)
    {
        this.context = context;
        email = "hp_dut@gmail.com";
        password = "123qwe";
    }


    LiveData<AccountLoginDto> getAccount() {
        if (accountLoginDtoMutableLiveData == null) {
            accountLoginDtoMutableLiveData = new MutableLiveData<>();
        }

        return accountLoginDtoMutableLiveData;
    }

    @Bindable
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public LiveData<Integer> getNavigate() {
        return mNavigate;
    }

    public void setNavigate(int nextComponentId) {
        mNavigate.setValue(nextComponentId);
    }


    public void onLoginClicked()
    {
        rightAccount = false;
        isActivated = false;
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(context);
        if (TextUtils.isEmpty(getEmail()) || TextUtils.isEmpty(getPassword())) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            AccountLoginDto accountLoginDto = new AccountLoginDto(getEmail(), getPassword());
            Log.d("TAG", "onLoginClicked: " + accountLoginDto.getEmail() + " " + accountLoginDto.getPassword());
            loginAPIService = RetrofitClient.getRetrofitInstance().create(LoginAPIService.class);
            Call<MessageResponse> callLogin = loginAPIService.login(accountLoginDto);
            callLogin.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
                @Override
                public void handleSuccess(MessageResponse responseObject) {
                    Log.d("TAG", responseObject.getAccess_token());
                    String token = responseObject.getAccess_token();
                    JWT jwt = new JWT(token);
                    Claim subscriptionMetaData = jwt.getClaim("name");
                    String name = subscriptionMetaData.asString();

                    subscriptionMetaData = jwt.getClaim("state");
                    int state = subscriptionMetaData.asInt();
                    Log.d("TAG", "handleSuccess: " + state);
                    if(state == 0) {
                        setNavigate(3);
                    }
                    else {
//                        SharedPreferencesManager sharedPreferencesManager1 = SharedPreferencesManager.getInstance(context);
//                        sharedPreferencesManager.setAccessToken("token");
                        SharedPreferences sharedPreferences = context.getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token",token);
                        editor.apply();
                        subscriptionMetaData = jwt.getClaim("role");
                        String role = subscriptionMetaData.asString();
                        Log.d("TAG", role);
                        Intent intent;
                        if (role.equals("Teacher")) {
                            intent = new Intent(context, HomeTeacherActivity.class);
                        }
                        else  {
                            intent = new Intent(context, HomeStudentActivity.class);
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
                @Override
                public void handleFailure(MessageResponse errorResponse) {
                    Log.d("TAG", errorResponse.getMessage());
//                    Toast.makeText(context, errorResponse.getMessage() , Toast.LENGTH_SHORT).show();
                }
            });
////            Call<MessageResponse> callActive = loginAPIService.active(getEmail());
//
//            callLogin.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
//                @Override
//                public void handleSuccess(MessageResponse responseObject) {
//                    Toast.makeText(context, responseObject.getMessage(), Toast.LENGTH_LONG).show();
//                    rightAccount = true;
//                }
//
//                @Override
//                public void handleFailure(MessageResponse errorResponse) {
//                    Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_LONG).show();
//                    rightAccount = false;
//                }
//            });

//            callActive.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
//                @Override
//                public void handleSuccess(MessageResponse responseObject) {
//                    isActivated = false;
//                }
//
//                @Override
//                public void handleFailure(MessageResponse errorResponse) {
//                    isActivated = true;
//                }
//            });

        }
//        if(rightAccount == true && isActivated == true)
//        {
//
//        }
//        if(rightAccount == true && isActivated == false)
//        {
//
//        }

    }
}
