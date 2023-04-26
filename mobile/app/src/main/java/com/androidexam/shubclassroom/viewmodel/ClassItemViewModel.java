package com.androidexam.shubclassroom.viewmodel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.androidexam.shubclassroom.BR;
import com.androidexam.shubclassroom.adapter.ItemAdapter;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.Class;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.teacher.BottomSheetClassTeacherFragment;
import com.androidexam.shubclassroom.view.teacher.CreateClassActivity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.PropertyResourceBundle;

import retrofit2.Call;

public class ClassItemViewModel extends BaseObservable implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("subjectName")
    private String subjectName;
    @SerializedName("numberOfStudent")
    private int numberOfStudent;
    @SerializedName("teacherId")
    private String teacherId;
    @SerializedName("createAt")
    private String createAt;
    @SerializedName("updateAt")
    private String updateAt;
    private BottomSheetClassTeacherFragment bottomSheetClassTeacherFragment;
    private Context context;
    private String textSearch;
    public static ItemAdapter adapter;

    public ClassItemViewModel(String id, String name, String description, String subjectName, int numberOfStudent, String teacherId, String createAt, String updateAt, Context context) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subjectName = subjectName;
        this.numberOfStudent = numberOfStudent;
        this.teacherId = teacherId;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.context = context;
    }

    public ClassItemViewModel(Context context) {
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public BottomSheetClassTeacherFragment getBottomSheetClassTeacherFragment() {
        return bottomSheetClassTeacherFragment;
    }

    public void setBottomSheetClassTeacherFragment(BottomSheetClassTeacherFragment bottomSheetClassTeacherFragment) {
        this.bottomSheetClassTeacherFragment = bottomSheetClassTeacherFragment;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void onClickCloseBottomSheet() {
        this.bottomSheetClassTeacherFragment.dismiss();
    }

    public void onClickDeleteClass() {
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Xác nhận!")
                .setMessage("Bạn chắc chắn muốn xoá?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
                        String token = sharedPreferences.getString("token", null);
                        ClassApiService apiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
                        Call<MessageResponse> call = apiService.deleteClass("Bear " + token, getId());
                        call.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
                            @Override
                            public void handleSuccess(MessageResponse responseObject) {
                                Toast.makeText(context, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void handleFailure(MessageResponse errorResponse) {
                                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    public void onclickCreateClass() {
        Intent intent = new Intent(context, CreateClassActivity.class);
        context.startActivity(intent);
    }
    public String getTextSearch() {
        return textSearch;
    }
    @Bindable
    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
        notifyPropertyChanged(BR.textSearch);
    }
    public void searchItem() {
        adapter.filterList(getTextSearch());
    }
}
