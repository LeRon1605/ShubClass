package com.androidexam.shubclassroom.view.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.adapter.ViewPageStudentAdapter;
import com.androidexam.shubclassroom.databinding.ActivityStudentHomeBinding;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import android.os.Bundle;
import android.view.View;

public class HomeStudentActivity extends AppCompatActivity {
    private ActivityStudentHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.viewpaper.setAdapter(new ViewPageStudentAdapter(this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tablayout, binding.viewpaper, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setIcon(getResources().getDrawable(R.drawable.ic_class_active));
                    break;
                case 1:
                    tab.setIcon(getResources().getDrawable(R.drawable.ic_proflie_active));
                    break;
            }
        });
        tabLayoutMediator.attach();
    }
}