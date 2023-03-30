//package com.androidexam.shubclassroom.adapter;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentActivity;
//import androidx.viewpager2.adapter.FragmentStateAdapter;
//
//public class ViewPageAdapter extends FragmentStateAdapter {
//
//
//    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
//        super(fragmentActivity);
//    }
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        switch (position) {
//            case 0:
//                return new HomeFragment();
//            case 1:
//                return new ClassFragment();
//            case 2:
//                return new CalendarFragment();
//            case 3:
//                return new AlertFragment();
//            case 4:
//                return new ProfileFragment();
//            default:
//                return new HomeFragment();
//        }
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return 5    ;
//    }
//}
