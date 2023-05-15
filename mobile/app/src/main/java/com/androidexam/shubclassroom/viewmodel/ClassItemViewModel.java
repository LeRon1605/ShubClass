package com.androidexam.shubclassroom.viewmodel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.androidexam.shubclassroom.BR;
import com.androidexam.shubclassroom.adapter.ItemAdapter;
import com.androidexam.shubclassroom.adapter.ItemSearchAdapter;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RequestApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.ClassDetail;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.view.student.BottomSheetClassStudentFragment;
import com.androidexam.shubclassroom.view.student.FindClassStudentActivity;
import com.androidexam.shubclassroom.view.student.HomeStudentActivity;
import com.androidexam.shubclassroom.view.teacher.BottomSheetClassTeacherFragment;
import com.androidexam.shubclassroom.view.teacher.CreateClassActivity;

import java.io.Serializable;

import retrofit2.Call;

public class ClassItemViewModel extends BaseObservable implements Serializable {
//    @SerializedName("id")
//    private String id;
//    @SerializedName("name")
//    private String name;
//    @SerializedName("description")
//    private String description;
//    @SerializedName("subjectName")
//    private String subjectName;
//    @SerializedName("numberOfStudent")
//    private int numberOfStudent;
//    @SerializedName("teacherId")
//    private String teacherId;
//    @SerializedName("createAt")
//    private String createAt;
//    @SerializedName("updateAt")
//    private String updateAt;
    private ClassDetail classModel;
    private ClassApiService classApiService;
    private RequestApiService requestApiService;
    private BottomSheetClassTeacherFragment bottomSheetClassTeacherFragment;
    private BottomSheetClassStudentFragment bottomSheetClassStudentFragment;
    private Context context;
    private String textSearch;
    private String token;
    public static ItemAdapter adapter;
    public static ItemSearchAdapter itemSearchAdapter;

//    public ClassItemViewModel(Context context) {
////        this.id = id;
////        this.name = name;
////        this.description = description;
////        this.subjectName = subjectName;
////        this.numberOfStudent = numberOfStudent;
////        this.teacherId = teacherId;
////        this.createAt = createAt;
////        this.updateAt = updateAt;
//        this.context = context;
//    }

    public ClassItemViewModel(Context context, ClassDetail classModel) {
        this.context = context;
        classApiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
        requestApiService = RetrofitClient.getRetrofitInstance().create(RequestApiService.class);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        this.classModel = classModel;
    }

    public ClassItemViewModel(Context context) {
        this.context = context;
    }

    public ClassDetail getClassModel() {
        return classModel;
    }

    public void setClassModel(ClassDetail classModel) {
        this.classModel = classModel;
    }

    //    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getSubjectName() {
//        return subjectName;
//    }
//
//    public void setSubjectName(String subjectName) {
//        this.subjectName = subjectName;
//    }
//
//    public int getNumberOfStudent() {
//        return numberOfStudent;
//    }
//
//    public void setNumberOfStudent(int numberOfStudent) {
//        this.numberOfStudent = numberOfStudent;
//    }
//
//    public String getTeacherId() {
//        return teacherId;
//    }
//
//    public void setTeacherId(String teacherId) {
//        this.teacherId = teacherId;
//    }
//
//    public String getCreateAt() {
//        return createAt;
//    }
//
//    public void setCreateAt(String createAt) {
//        this.createAt = createAt;
//    }
//
//    public String getUpdateAt() {
//        return updateAt;
//    }

    public BottomSheetClassTeacherFragment getBottomSheetClassTeacherFragment() {
        return bottomSheetClassTeacherFragment;
    }

    public void setBottomSheetClassTeacherFragment(BottomSheetClassTeacherFragment bottomSheetClassTeacherFragment) {
        this.bottomSheetClassTeacherFragment = bottomSheetClassTeacherFragment;
    }

    public BottomSheetClassStudentFragment getBottomSheetClassStudentFragmentFragment() {
        return bottomSheetClassStudentFragment;
    }

    public void setBottomSheetClassStudentFragment(BottomSheetClassStudentFragment bottomSheetClassStudentFragmentFragment) {
        this.bottomSheetClassStudentFragment = bottomSheetClassStudentFragmentFragment;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void onClickCloseBottomSheetTeacher() {
        this.bottomSheetClassTeacherFragment.dismiss();
    }
    public void onClickCloseBottomSheetStudent() {
        this.bottomSheetClassStudentFragment.dismiss();
    }

    public void onClickDeleteClass() {
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Xác nhận!")
                .setMessage("Bạn chắc chắn muốn xoá?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<MessageResponse> call = classApiService.deleteClass("Bear " + token, classModel.getId());
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
    public void onClickRedirectSearchClassStudent() {
        Intent intent = new Intent(context, FindClassStudentActivity.class);
        context.startActivity(intent);
    }
    public void onClickRequestClass() {
        Call<MessageResponse> call = requestApiService.requestJoinClass("Bear " + token, classModel.getId());
        call.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(MessageResponse responseObject) {
                Toast.makeText(context, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, HomeStudentActivity.class
                ));
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
