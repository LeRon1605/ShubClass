package com.androidexam.shubclassroom.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.androidexam.shubclassroom.view.AlertStudentFragment;
import com.androidexam.shubclassroom.view.ClassStudentFragment;
import com.androidexam.shubclassroom.view.ProfileStudentFragment;
import com.androidexam.shubclassroom.view.ScheduleStudentFragment;
import com.androidexam.shubclassroom.view.StudentHomeFragment;

public class ViewPageAdapter extends FragmentStateAdapter {


    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new StudentHomeFragment();
            case 1:
                return new ClassStudentFragment();
            case 2:
                return new ScheduleStudentFragment();
            case 3:
                return new AlertStudentFragment();
            case 4:
                return new ProfileStudentFragment();
            default:
                return new StudentHomeFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 5 ;
    }
}
