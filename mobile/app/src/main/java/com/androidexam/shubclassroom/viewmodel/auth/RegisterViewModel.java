package com.androidexam.shubclassroom.viewmodel.auth;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import androidx.databinding.Bindable;

import com.androidexam.shubclassroom.BR;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.AuthApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.auth.AccountCreateDto;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.shared.INavigation;

import retrofit2.Call;

public class RegisterViewModel extends BaseAuthViewModel {
    private String confirmPassword;
    private AccountCreateDto accountCreateDto;
    private AuthApiService authApiService;

    public RegisterViewModel(Context context, INavigation navigation) {
        super(context, navigation);
        accountCreateDto = new AccountCreateDto();

        authApiService = RetrofitClient.getRetrofitInstance().create(AuthApiService.class);
    }

    public AccountCreateDto getAccountCreateDto() {
        return this.accountCreateDto;
    }

    public void setAccountCreateDto(AccountCreateDto accountCreateDto) {
        this.accountCreateDto = accountCreateDto;
    }

    @Bindable
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        notifyPropertyChanged(BR.confirmPassword);
    }

    public void onClickRegister() {
        if (!accountCreateDto.isValid()) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(accountCreateDto.getEmail()).matches()) {
            Toast.makeText(context, "Email chưa đúng định dạng.", Toast.LENGTH_SHORT).show();
        }
        else if(!accountCreateDto.getPassword().equals(getConfirmPassword())) {
            Toast.makeText(context, "Mật khẩu xác nhận không chính xác.", Toast.LENGTH_SHORT).show();
        } else {
            Call<MessageResponse> call = authApiService.postRegister(accountCreateDto.toBody());
            call.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
                @Override
                public void handleSuccess(MessageResponse responseObject) {
                    Toast.makeText(context, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                    accountCreateDto = new AccountCreateDto();
                    confirmPassword = "";
                    notifyChange();
                }

                @Override
                public void handleFailure(MessageResponse errorResponse) {
                    Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
