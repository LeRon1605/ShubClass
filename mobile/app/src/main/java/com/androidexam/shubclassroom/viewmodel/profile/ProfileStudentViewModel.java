package com.androidexam.shubclassroom.viewmodel.profile;

import android.content.Context;

import com.androidexam.shubclassroom.model.profile.ProfileIn4;
import com.androidexam.shubclassroom.shared.INavigation;

public class ProfileStudentViewModel extends BaseProfileViewModel{
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
}
