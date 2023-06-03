package com.androidexam.shubclassroom.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.androidexam.shubclassroom.view.student.ClassStudentFragment;
import com.androidexam.shubclassroom.view.student.ProfileStudentFragment;

public class ViewPageStudentAdapter extends FragmentStateAdapter {


    public ViewPageStudentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ClassStudentFragment();
            case 1:
                return new ProfileStudentFragment();
            default:
                return new ClassStudentFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 2 ;
    }
}
