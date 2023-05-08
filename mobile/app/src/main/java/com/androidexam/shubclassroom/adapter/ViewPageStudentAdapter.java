package com.androidexam.shubclassroom.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.androidexam.shubclassroom.view.student.AlertStudentFragment;
import com.androidexam.shubclassroom.view.student.ClassStudentFragment;
import com.androidexam.shubclassroom.view.student.ProfileStudentFragment;
import com.androidexam.shubclassroom.view.student.ScheduleStudentFragment;
import com.androidexam.shubclassroom.view.student.HomeStudentFragment;

public class ViewPageStudentAdapter extends FragmentStateAdapter {


    public ViewPageStudentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeStudentFragment();
            case 1:
                return new ClassStudentFragment();
            case 2:
                return new ScheduleStudentFragment();
            case 3:
                return new AlertStudentFragment();
            case 4:
                return new ProfileStudentFragment();
            default:
                return new HomeStudentFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 5 ;
    }
}
