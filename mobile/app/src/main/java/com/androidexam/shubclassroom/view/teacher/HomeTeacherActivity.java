package com.androidexam.shubclassroom.view.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.adapter.ViewPageStudentAdapter;
import com.androidexam.shubclassroom.adapter.ViewPageTeacherAdapter;
import com.androidexam.shubclassroom.databinding.ActivityStudentHomeBinding;
import com.androidexam.shubclassroom.databinding.ActivityTeacherHomeBinding;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeTeacherActivity extends AppCompatActivity {
    private ActivityTeacherHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.viewpaper.setAdapter(new ViewPageTeacherAdapter(this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tablayout, binding.viewpaper, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setIcon(getResources().getDrawable(R.drawable.ic_class_active));
                        break;
                    case 1:
                        tab.setIcon(getResources().getDrawable(R.drawable.ic_calendar_active));
                        break;
                    case 2:
                        tab.setIcon(getResources().getDrawable(R.drawable.ic_alert_active));
                        BadgeDrawable badgeDrawable = tab.getOrCreateBadge();
                        badgeDrawable.setBackgroundColor(getResources().getColor(R.color.red));
                        badgeDrawable.setVisible(true);
                        badgeDrawable.setNumber(1);
                        break;
                    case 3:
                        tab.setIcon(getResources().getDrawable(R.drawable.ic_proflie_active));
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
    }
}