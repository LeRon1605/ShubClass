package com.androidexam.shubclassroom.viewmodel.profile;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.androidexam.shubclassroom.model.profile.ProfileIn4;
import com.androidexam.shubclassroom.shared.FragmentIndex;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.view.EditInfoActivity;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.auth.AuthActivity;


public class ProfileStudentViewModel extends BaseProfileViewModel {
    private ProfileIn4 profileIn4;

    public ProfileStudentViewModel(Context context, ProfileIn4 profileIn4) {
        super(context);
        this.profileIn4 = profileIn4;
    }

    public ProfileIn4 getProfileIn4() {
        return profileIn4;
    }

    public void setProfileIn4(ProfileIn4 profileIn4) {
        this.profileIn4 = profileIn4;
    }

    public void onClickEditProfile() {
        Intent intent = new Intent(context, EditInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ProfileIn4", profileIn4);
        bundle.putInt("role", FragmentIndex.Student.getValue());
        intent.putExtra("bundle", bundle);
    }

    public void logout() {
        SharedPreferencesManager.getInstance(context).clear();
        Intent intent = new Intent(context, AuthActivity.class);
        context.startActivity(intent);
    }
}