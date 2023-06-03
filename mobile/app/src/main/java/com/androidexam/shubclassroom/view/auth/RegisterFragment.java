package com.androidexam.shubclassroom.view.auth;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentRegisterBinding;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.utilities.setDate;
import com.androidexam.shubclassroom.viewmodel.auth.RegisterViewModel;

public class RegisterFragment extends Fragment {
    private String[] vietnamCities = {"Hà Nội", "Hồ Chí Minh", "Đà Nẵng", "Hải Phòng", "Cần Thơ", "An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu", "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Cao Bằng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Tĩnh", "Hải Dương", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"};
    private String[] roles = {"Học sinh", "Giáo Viên"};
    private String[] genders = {"Nam", "Nữ"};

    ArrayAdapter<String> adapterItemsCity;
    ArrayAdapter<String> adapterItemsRole;
    ArrayAdapter<String> adapterItemsGender;

    private INavigation navigation;
    private FragmentRegisterBinding binding;
    private RegisterViewModel viewModel;

    public RegisterFragment(INavigation navigation) {
        this.navigation = navigation;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_register, container, false);
        viewModel = new RegisterViewModel(getContext(), navigation);
        binding.setRegisterViewModel(viewModel);

        setDate fromDate = new setDate(binding.edtDate, getContext());

        adapterItemsCity = new ArrayAdapter<String>(getContext(), R.layout.list_item, vietnamCities);
        binding.edtProvince.setAdapter(adapterItemsCity);

        adapterItemsRole = new ArrayAdapter<String>(getContext(), R.layout.list_item, roles);
        binding.edtRole.setAdapter(adapterItemsRole);

        adapterItemsGender = new ArrayAdapter<String>(getContext(), R.layout.list_item, genders);
        binding.edtGender.setAdapter(adapterItemsGender);


        View viewRoot = binding.getRoot();
        return viewRoot;
    }
}