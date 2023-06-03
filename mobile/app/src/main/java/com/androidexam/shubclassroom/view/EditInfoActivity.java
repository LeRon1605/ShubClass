package com.androidexam.shubclassroom.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ActivityEditInfoBinding;
import com.androidexam.shubclassroom.model.profile.ProfileIn4;
import com.androidexam.shubclassroom.shared.FragmentIndex;
import com.androidexam.shubclassroom.utilities.setDate;
import com.androidexam.shubclassroom.viewmodel.profile.EditProfileViewModel;

public class EditInfoActivity extends AppCompatActivity {

    ActivityEditInfoBinding binding;
    private String[] vietnamCities = {"Hà Nội", "Hồ Chí Minh", "Đà Nẵng", "Hải Phòng", "Cần Thơ", "An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu", "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Cao Bằng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Tĩnh", "Hải Dương", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"};
    ArrayAdapter<String> adapterItemsCity;
    private ProfileIn4 profileIn4;
    private int role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterItemsCity = new ArrayAdapter<>(EditInfoActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, vietnamCities);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_info);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if(bundle != null) {
            profileIn4 = (ProfileIn4) bundle.getSerializable("ProfileIn4");
            role = bundle.getInt("role");
        }
        binding.setViewModel(new EditProfileViewModel(profileIn4, role, this));
        setDate date = new setDate(binding.etDate, EditInfoActivity.this);
        //reset AutoComplete
        binding.edtProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtProvince.setAdapter(adapterItemsCity);
                binding.edtProvince.showDropDown();
            }
        });
        if(role == FragmentIndex.Teacher.getValue()) {
            binding.layoutEtGrade.setVisibility(View.INVISIBLE);
        }
    }
}