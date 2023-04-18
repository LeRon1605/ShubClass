package com.androidexam.shubclassroom.viewmodel;

import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.androidexam.shubclassroom.BR;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.RegisterApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.AccountCreateDto;
import com.androidexam.shubclassroom.model.MessageResponse;

import retrofit2.Call;

public class RegisterViewModel extends BaseObservable {
    private String name;
    private String grade;
    private String schoolName;
    private String dateOfBirth;
    private String provinceName;
    private String email;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private String role;
    private String gender;
    private Context context;

    public RegisterViewModel(Context context) {
        this.context = context;
        name = "Ron";
        grade = "12";
        schoolName = "DUT";
        dateOfBirth = "2022-03-13";
        provinceName = "Hue";
        email = "ronl9519@gmail.com";
        password = "ronle75";
        confirmPassword = "ronle75";
        phoneNumber = "0905857760";
        role = "Student";
        gender = "true";
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        notifyPropertyChanged(BR.confirmPassword);
    }

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }

    @Bindable
    public String getClassName() {
        return grade;
    }

    public void setClassName(String className) {
        this.grade = className;
        notifyPropertyChanged(BR.className);
    }

    @Bindable
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
        notifyPropertyChanged(BR.schoolName);
    }

    @Bindable
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        notifyPropertyChanged(BR.dateOfBirth);
    }

    @Bindable
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
        notifyPropertyChanged(BR.provinceName);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        notifyPropertyChanged(BR.role);
    }

    @Bindable
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
        notifyPropertyChanged(BR.grade);
    }

    @Bindable
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }
    public void onClickRegister() {
        if (getName() == null ||
                getSchoolName() == null ||
                getClassName() == null ||
                getGender() == null ||
                getDateOfBirth() == null ||
                getProvinceName() == null ||
                getRole() == null ||
                getEmail() == null ||
                getPhoneNumber() == null ||
                getPassword() == null ||
                getConfirmPassword() == null
        ) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else if(getName().isEmpty() ||
                getSchoolName().isEmpty() ||
                getClassName().isEmpty() ||
                getGender().isEmpty() ||
                getDateOfBirth().isEmpty() ||
                getProvinceName().isEmpty() ||
                getRole().isEmpty() ||
                getEmail().isEmpty() ||
                getPhoneNumber().isEmpty() ||
                getPassword().isEmpty() ||
                getConfirmPassword().isEmpty()) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(getEmail().toString()).matches()) {
            Log.d("TAG", "onClickRegister: " + getEmail());
            Toast.makeText(context, "Email chưa đúng định dạng", Toast.LENGTH_SHORT).show();
        }
        else if(!getPassword().equals(getConfirmPassword())) {
            Toast.makeText(context, "Password ERR", Toast.LENGTH_SHORT).show();
        }
        else {
            Boolean gender = getGender().equalsIgnoreCase("nam") ? true : false;
            String role = getRole().equalsIgnoreCase("học sinh") ? "Student" : "Teacher";
            AccountCreateDto account = new AccountCreateDto(getEmail(), getPassword(), getName(), getDateOfBirth(),
                    getSchoolName(), Integer.parseInt(getGrade()), getPhoneNumber(), "Quang Nam", gender, role);
            Log.d("TAG", account.getEmail() + "," + account.getPassword() + "," + account.getName() + "," + account.getDateOfBirth() + "," + account.getPhoneNumber() + "," + account.getGrade() + "," + account.getPassword() + ","
                    + account.getAddress() + "," + account.getGender() + "," + account.getRole() + "," + account.getSchool());

            RegisterApiService apiService = RetrofitClient.getRetrofitInstance().create(RegisterApiService.class);
            Call<MessageResponse> call = apiService.postRegister(account);
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

    }
}
