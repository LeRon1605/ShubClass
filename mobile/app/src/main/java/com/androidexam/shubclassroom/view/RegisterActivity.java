package com.androidexam.shubclassroom.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ActivityRegisterBinding;
import com.androidexam.shubclassroom.utilities.DateInputMask;
import com.androidexam.shubclassroom.utilities.DropDown;
import com.androidexam.shubclassroom.utilities.setDate;
import com.androidexam.shubclassroom.viewmodel.RegisterViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private String[] vietnamCities = {"Hà Nội", "Hồ Chí Minh", "Đà Nẵng", "Hải Phòng", "Cần Thơ", "An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu", "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Cao Bằng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Tĩnh", "Hải Dương", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"};
    private String[] roles = {"Học sinh", "Giáo Viên"};
    private String[] genders = {"Nam", "Nữ"};
    ArrayAdapter<String> adapterItemsCity;
    ArrayAdapter<String> adapterItemsRole;
    ArrayAdapter<String> adapterItemsGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        RegisterViewModel registerViewModel = new RegisterViewModel(getApplicationContext());
        binding.setRegisterViewModel(registerViewModel);

        setDate fromDate = new setDate(binding.edtDate, this);

        adapterItemsCity = new ArrayAdapter<String>(this, R.layout.list_item, vietnamCities);
        binding.edtProvince.setAdapter(adapterItemsCity);

        adapterItemsRole = new ArrayAdapter<String>(this, R.layout.list_item, roles);
        binding.edtRole.setAdapter(adapterItemsRole);

        adapterItemsGender = new ArrayAdapter<String>(this, R.layout.list_item, genders);
        binding.edtGender.setAdapter(adapterItemsGender);
    }
}