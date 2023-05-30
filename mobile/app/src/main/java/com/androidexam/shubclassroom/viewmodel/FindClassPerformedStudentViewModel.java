package com.androidexam.shubclassroom.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.androidexam.shubclassroom.BR;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.ClassDetail;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class FindClassPerformedStudentViewModel extends BaseObservable {
    private String txtSearch;
    private Context context;
    private ClassApiService apiService;
    private List<ClassItemViewModel> listClass;
    private ClassItemViewModel classItemViewModel;
    public FindClassPerformedStudentViewModel(Context context) {
        this.context = context;
        this.txtSearch = "";
        apiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
        listClass  = new ArrayList<>();
    }

    public String getTxtSearch() {
        return txtSearch;
    }
    @Bindable
    public void setTxtSearch(String txtSearch) {
        this.txtSearch = txtSearch;
        notifyPropertyChanged(BR.txtSearch);
    }
    public void onClickButtonSearch() {
        listClass.clear();
        ClassItemViewModel.itemSearchAdapter.updateItemList(listClass);
        String token = SharedPreferencesManager.getInstance(context).getAccessToken();
        Call<List<ClassDetail>> call = apiService.searchClass(token, getTxtSearch());
        call.enqueue(new ApiCallback<List<ClassDetail>, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(List<ClassDetail> responseObject) {
                if(responseObject.size() < 1) {
                    Toast.makeText(context, "Không tìm thấy lớp hoặc lớp bạn đã tham gia!", Toast.LENGTH_SHORT).show();
                } else {
                    for(ClassDetail i : responseObject) {
                        classItemViewModel = new ClassItemViewModel(context, i);
                        listClass.add(classItemViewModel);
                    }
                    ClassItemViewModel.itemSearchAdapter.updateItemList(listClass);
                }
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
