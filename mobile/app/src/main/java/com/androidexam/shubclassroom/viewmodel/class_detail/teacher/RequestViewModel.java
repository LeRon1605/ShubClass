package com.androidexam.shubclassroom.viewmodel.class_detail.teacher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.RequestApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.request.RequestIn4;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.class_detail.BaseClassDetailViewModel;

import retrofit2.Call;

public class RequestViewModel extends BaseClassDetailViewModel {
    private RequestIn4 requestIn4;
    private RequestApiService apiService;
    private String idClass;
    private String token;

    public RequestViewModel(INavigation navigation, RequestIn4 requestIn4, Context context, String idClass) {
        super(context, navigation);
        this.requestIn4 = requestIn4;
        this.idClass = idClass;
        apiService = RetrofitClient.getRetrofitInstance().create(RequestApiService.class);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
        this.token = sharedPreferences.getString("token", null);
    }

    public void onClickAcceptRequest() {
        Call<MessageResponse> call = apiService.acceptRequest("Bear " + token, idClass, requestIn4.getStudent().getId());
        call.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(MessageResponse responseObject) {
                Toast.makeText(context, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                navigateTo(ClassDetailFragment.ShowAllRequestOfClass);
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickRejectRequest() {
        Call<MessageResponse> call = apiService.acceptRequest("Bear " + token, idClass, requestIn4.getStudent().getId());
        call.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(MessageResponse responseObject) {
                Toast.makeText(context, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                navigateTo(ClassDetailFragment.ShowAllRequestOfClass);
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public RequestIn4 getRequestIn4() {
        return requestIn4;
    }

    public void setRequestIn4(RequestIn4 requestIn4) {
        this.requestIn4 = requestIn4;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
