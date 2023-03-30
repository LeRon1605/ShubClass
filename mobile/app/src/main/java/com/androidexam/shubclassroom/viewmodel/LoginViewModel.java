package com.androidexam.shubclassroom.viewmodel;

import android.content.Context;
import android.text.TextUtils;
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
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;

import retrofit2.Call;

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

        if (TextUtils.isEmpty(getEmail()) || TextUtils.isEmpty(getPassword())) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            AccountLoginDto  accountLoginDto = new AccountLoginDto(getEmail(), getPassword());
            loginAPIService = RetrofitClient.getRetrofitInstance().create(LoginAPIService.class);

            Call<MessageResponse> callLogin = loginAPIService.login(accountLoginDto);
            Call<MessageResponse> callActive = loginAPIService.active(getEmail());

            callLogin.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
                @Override
                public void handleSuccess(MessageResponse responseObject) {
                    String jwtToken = responseObject.getMessage();
                    preferencesManager.setAccessToken(jwtToken);
//                    Toast.makeText(context, "Right Account", Toast.LENGTH_LONG).show();
                    rightAccount = true;
                }

                @Override
                public void handleFailure(MessageResponse errorResponse) {
                    Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_LONG).show();
                    rightAccount = false;
                }
            });

            callActive.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
                @Override
                public void handleSuccess(MessageResponse responseObject) {
                    isActivated = false;
                }

                @Override
                public void handleFailure(MessageResponse errorResponse) {
                    isActivated = true;
                }
            });

        }
        if(rightAccount == true && isActivated == true)
        {

        }
        if(rightAccount == true && isActivated == false)
        {

        }

    }
}
