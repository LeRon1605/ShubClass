package com.androidexam.shubclassroom.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.androidexam.shubclassroom.view.student.AlertStudentFragment;
import com.androidexam.shubclassroom.view.student.ProfileStudentFragment;
import com.androidexam.shubclassroom.view.student.ScheduleStudentFragment;
import com.androidexam.shubclassroom.view.student.HomeStudentFragment;
import com.androidexam.shubclassroom.view.teacher.AlertTeacherFragment;
import com.androidexam.shubclassroom.view.teacher.HomeTeacherFragment;
import com.androidexam.shubclassroom.view.teacher.ProfileTeacherFragment;
import com.androidexam.shubclassroom.view.teacher.ScheduleTeacherFragment;

public class ViewPageTeacherAdapter extends FragmentStateAdapter {


    public ViewPageTeacherAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeTeacherFragment();
            case 1:
                return new ProfileTeacherFragment();
            default:
                return new HomeTeacherFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
