package com.androidexam.shubclassroom.viewmodel.auth;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.Bindable;

import com.androidexam.shubclassroom.BR;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.AuthApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.auth.ForgetPasswordDto;
import com.androidexam.shubclassroom.shared.AuthFragment;
import com.androidexam.shubclassroom.shared.INavigation;

import retrofit2.Call;

public class ForgetPassViewModel extends BaseAuthViewModel {
    private AuthApiService authApiService;
    public ForgetPasswordDto forgetPasswordDto;

    public ForgetPassViewModel(Context context, INavigation navigation) {
        super(context, navigation);

        forgetPasswordDto = new ForgetPasswordDto();

        authApiService = RetrofitClient.getRetrofitInstance().create(AuthApiService.class);
    }

    public void onContinueClicked() {
        if (!this.forgetPasswordDto.isValid()) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
        } else {
            Call<MessageResponse> activeEmailCall = authApiService.requestForgetPassword(forgetPasswordDto);
            activeEmailCall.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
                @Override
                public void handleSuccess(MessageResponse responseObject) {
                    navigateTo(AuthFragment.Notify);
                }

                @Override
                public void handleFailure(MessageResponse errorResponse) {
                    Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
